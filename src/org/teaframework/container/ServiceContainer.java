/**
 * @(#)ServiceContainer.java
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

package org.teaframework.container;

import java.util.List;

import org.picocontainer.ComponentAdapter;
import org.picocontainer.PicoContainer;
import org.picocontainer.PicoException;
import org.picocontainer.PicoRegistrationException;
import org.picocontainer.defaults.ComponentAdapterFactory;
import org.picocontainer.defaults.DefaultPicoContainer;

/**
 * <p>
 * The services container is a extension of DefaultPicoContainer.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.13 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class ServiceContainer extends DefaultPicoContainer {

    private static ServiceContainer serviceContainer;

    public static ServiceContainer getInstance() {
        if (serviceContainer == null) {
            serviceContainer = new ServiceContainer();
        }
        return serviceContainer;
    }

    /**
     * Creates a new container with a custom ComponentAdapterFactory and a
     * parent container.
     * 
     * @param componentAdapterFactory
     * @param parent
     */
    public ServiceContainer(ComponentAdapterFactory componentAdapterFactory,
            PicoContainer parent) {
        super(componentAdapterFactory, parent);
    }

    /**
     * Creates a new container with a parent container.
     * 
     * @param parent
     */
    public ServiceContainer(PicoContainer parent) {
        super(parent);
    }

    /**
     * Default Container.
     * 
     * @param parent
     */
    public ServiceContainer() {
        super();
    }

    /**
     * @see org.picocontainer.MutablePicoContainer#registerComponentImplementation(java.lang.Object)
     */
    public ComponentAdapter registerServiceImplementation(Class serviceImpl)
            throws PicoRegistrationException {
        return super.registerComponentImplementation(serviceImpl);

    }

    /**
     * @see org.picocontainer.defaults.DefaultPicoContainer#registerComponentImplementation(java.lang.Object,
     *      java.lang.Class, java.util.List)
     */
    public ComponentAdapter registerServiceImplementation(Object serviceKey,
            Class serviceImpl, List parameters)
            throws PicoRegistrationException {
        return super.registerComponentImplementation(serviceKey, serviceImpl,
                parameters);
    }

    /**
     * @see org.picocontainer.MutablePicoContainer#registerComponentImplementation(java.lang.Object,
     *      java.lang.Class)
     */
    public ComponentAdapter registerServiceImplementation(Object serviceKey,
            Class serviceImpl) throws PicoRegistrationException {
        return super.registerComponentImplementation(serviceKey, serviceImpl);
    }

    /**
     * @see org.picocontainer.MutablePicoContainer#registerComponentInstance(java.lang.Object,
     *      java.lang.Object)
     */
    public ComponentAdapter registerServiceInstance(Object serviceKey,
            Object serviceInstance) throws PicoRegistrationException {
        return super.registerComponentInstance(serviceKey, serviceInstance);
    }

    /**
     * @see org.picocontainer.MutablePicoContainer#registerComponentInstance(java.lang.Object)
     */
    public ComponentAdapter registerServiceInstance(Object serviceInstance)
            throws PicoRegistrationException {
        return super.registerComponentInstance(serviceInstance);
    }

    /**
     * @see org.picocontainer.PicoContainer#getComponentInstance(java.lang.Object)
     */
    public Object getService(Object serviceKey) throws PicoException {
        return super.getComponentInstance(serviceKey);
    }

    /**
     * @see org.picocontainer.PicoContainer#getComponentInstanceOfType(java.lang.Class)
     */
    public Object getServiceOfType(Class serviceType) {
        return super.getComponentInstanceOfType(serviceType);
    }

    /**
     * Unregister service.
     * 
     * @param service
     * @return ComponentAdapter
     */
    public ComponentAdapter unregisterService(Object service) {
        return super.unregisterComponent(service);
    }
}