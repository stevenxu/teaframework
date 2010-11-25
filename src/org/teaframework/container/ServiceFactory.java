/**
 * @(#)ServiceFactory.java
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

import org.picocontainer.PicoException;
import org.picocontainer.PicoRegistrationException;
import org.teaframework.exception.ServiceConfigurationException;

/**
 * <p>
 * The ServiceFactory is a singleton wrapper on the service container instance.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.16 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class ServiceFactory {

    private static ServiceFactory serviceFactory;    

    private ServiceContainer serviceContainer = ServiceContainer.getInstance();

    private ManageableServiceContainer manageableServiceContainer = ManageableServiceContainer
            .getInstance();

    /**
     * Singleton instance.
     */
    public static synchronized ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    /**
     * Default Constructor.
     */
    public ServiceFactory() {
    }

    /**
     * Lookup service by serviceKey
     * 
     * @param componentKey
     * @return Service Object
     */
    public synchronized Object getService(Object serviceKey)
            throws PicoException, ServiceConfigurationException {
        return serviceContainer.getService(serviceKey);
    }

    /**
     * Lookup service by serviceType
     * 
     * @param componentKey
     * @return Service Object
     */
    public synchronized Object getService(Class serviceType)
            throws PicoException {
        return serviceContainer.getServiceOfType(serviceType);
    }

    /**
     * Unregister service.
     * 
     * @param service
     */
    public void unRegisterService(Object service) {
        serviceContainer.unregisterComponent(service);
    }

    /**
     * Unregister service instance.
     * 
     * @param serviceInstance
     */
    public void unRegisterServiceInstance(Object serviceInstance) {
        serviceContainer.unregisterComponentByInstance(serviceInstance);
    }

    /**
     * Register service implementation.
     * 
     * @param service
     */
    public void registerService(Class serviceImpl)
            throws PicoRegistrationException {
        serviceContainer.registerServiceImplementation(serviceImpl);
    }

    /**
     * Register service with service key.
     * 
     * @param serviceKey
     * @param serviceImpl
     */
    public void registerService(Object serviceKey, Class serviceImpl)
            throws PicoRegistrationException {
        serviceContainer.registerServiceImplementation(serviceKey, serviceImpl);
    }

    /**
     * Register service instance.
     * 
     * @param serviceInstance
     */
    public void registerServiceInstance(Object serviceInstance)
            throws PicoRegistrationException {
        serviceContainer.registerServiceInstance(serviceInstance);
    }

}