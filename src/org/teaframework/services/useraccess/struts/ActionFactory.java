/**
 * @(#)ActionFactory.java
 * 
 * Tea Service-Oriented Java/J2EE Application Framework
 * 
 * Copyright(c) Tea Framework Team
 *  
 * Licensed under the GNU LGPL, Version 2.1 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at  
 * 
 * http://www.gnu.org/copyleft/lesser.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 * 
 * For more information, please visit:
 * http://sourceforge.net/projects/teaframework
 */

package org.teaframework.services.useraccess.struts;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.picocontainer.PicoInitializationException;
import org.picocontainer.PicoIntrospectionException;
import org.teaframework.container.ServiceFactory;

/**
 * Uses PicoContainer to produce Actions and inject dependencies into them. 
 * If you have your own <code>RequestProcessor</code> implementation, you can use an
 * <code>ActionFactory</code> in your <code>RequestProcessor.processActionCreate</code> 
 * method to Picofy your Actions.
 * 
 * @author Stephen Molitor
 * @author Mauro Talevi
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:44 $
 * @version Revision: 1.0
 */

public class ActionFactory {
	
	private ServiceFactory sf = ServiceFactory.getInstance();
	
	private Map classCache = new HashMap();
	
	/**
	 * Gets the <code>Action</code> specified by the mapping type from a
	 * PicoContainer. The action will be instantiated if necessary, and its
	 * dependencies will be injected. The action will be instantiated via a
	 * special PicoContainer that just contains actions. If this container
	 * already exists in the request attribute, this method will use it. If no
	 * such container exists, this method will create a new Pico container and
	 * place it in the request. The parent container will either be the request
	 * container, or if that container can not be found the session container,
	 * or if that container can not be found, the application container. If no
	 * parent container can be found, a <code>PicoInitializationException</code>
	 * will be thrown. The action path specified in the mapping is used as the
	 * component key for the action.
	 * 
	 * @param request
	 *            the Http servlet request.
	 * @param mapping
	 *            the Struts mapping object, whose type property tells us what
	 *            Action class is required.
	 * @param servlet
	 *            the Struts <code>ActionServlet</code>.
	 * @return the <code>Action</code> instance.
	 * @throws PicoIntrospectionException
	 *             if the mapping type does not specify a valid action.
	 * @throws PicoInitializationException
	 *             if no request, session, or application scoped Pico container
	 *             can be found.
	 *  
	 */
	public Action processAction(HttpServletRequest request,
			ActionMapping mapping, ActionServlet servlet)
			throws PicoIntrospectionException, PicoInitializationException {
		Object actionKey = mapping.getPath();
		Class actionType = getActionClass(mapping.getType());

		Action action = (Action) sf.getService(actionKey);
		if (action == null) {
			sf.registerService(actionKey, actionType);
			action = (Action) sf.getService(actionKey);
		}

		action.setServlet(servlet);
		return action;
	}

	/**
	 * Load class.
	 * 
	 * @param className
	 * @return the class
	 * @throws PicoIntrospectionException
	 */
	private Class getActionClass(String className) throws PicoIntrospectionException {
        try {
            return loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new PicoIntrospectionException("Action class '" + className + "' not found", e);
        }
    }
    
	/**
	 * Load class with given name.
	 * 
	 * @param className
	 * @return the class
	 * @throws ClassNotFoundException
	 */
    private Class loadClass(String className) throws ClassNotFoundException {
        if (classCache.containsKey(className)) {
            return (Class) classCache.get(className);
        } else {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class result = classLoader.loadClass(className);
            classCache.put(className, result);
            return result;
        }
    }    

}
