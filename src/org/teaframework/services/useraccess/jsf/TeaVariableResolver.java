/**
 * @(#)TeaVariableResolver.java
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
package org.teaframework.services.useraccess.jsf;

import javax.faces.context.FacesContext;

import org.apache.myfaces.el.VariableResolverImpl;
import org.teaframework.container.ServiceFactory;

/**
 * <p>
 * JSF and TeaFramework integration VariableResolver implementation </p
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version Revision: Date: 2006-12-3
 * @version Revision: 1.0
 * 
 */
public class TeaVariableResolver extends VariableResolverImpl {

	private ServiceFactory sf = ServiceFactory.getInstance();

	public Object resolveVariable(FacesContext context, String name) {

		// check if bean with same name exists in application context.
		if (!isContainService(name)) {
			return super.resolveVariable(context, name);
		} else {
			return sf.getService(name);
		}
	}

	private boolean isContainService(String serviceName) {
		Object obj = sf.getService(serviceName);
		if (obj == null) {
			return false;
		} else {
			return true;
		}
	}

}
