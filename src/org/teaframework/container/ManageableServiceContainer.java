/**
 * @(#)ManageableServiceContainer.java
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

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * The manageable service container is a wrapper container on the top of
 * ServiceContainer and MBeanServer. It's a manageable Inversion of Control
 * (IoC) container which service can be managed by JMX.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.8 $ $Date: 2005/01/17 13:29:59 $
 * @version Revision: 1.0
 */

public class ManageableServiceContainer {

    private static ManageableServiceContainer manageableServiceContainer;

    private ServiceContainer serviceContainer = ServiceContainer.getInstance();

    private final MBeanServer mBeanServer = MBeanServerFactory
            .createMBeanServer("jfoxsoafmx");

    public static ManageableServiceContainer getInstance() {
        if (manageableServiceContainer == null) {
            manageableServiceContainer = new ManageableServiceContainer();
        }
        return manageableServiceContainer;
    }

    /**
     * Regist managable service.
     * 
     * @param serviceInstance
     * @param serviceEntry
     * @throws Throwable
     */
    public void registManagebleService(Object serviceInstance,
            ServiceEntry serviceEntry) throws Throwable {
        if (StringUtils.isEmpty(serviceEntry.getId())) {
            mBeanServer
                    .registerMBean(serviceInstance, objectName(serviceEntry));
            serviceContainer.registerServiceInstance(serviceInstance);
        } else {
            registManagebleService(serviceEntry.getId(), serviceInstance,
                    serviceEntry);
        }
    }

    /**
     * Regist manageable service with service id.
     * 
     * @param serviceKey
     * @param serviceInstance
     * @param serviceEntry
     * @throws Throwable
     */
    private void registManagebleService(Object serviceKey,
            Object serviceInstance, ServiceEntry serviceEntry) throws Throwable {
        mBeanServer.registerMBean(serviceInstance, objectName(serviceEntry));
        serviceContainer.registerServiceInstance(serviceKey, serviceInstance);
    }

    /**
     * Get an MBeanServer instance.
     * 
     * @return MBeanServer
     */
    public MBeanServer getMBeanServer() {
        return this.mBeanServer;
    }

    /**
     * Convert object string to ObjectName.
     * 
     * @param serviceEntry
     * @return
     * @throws MalformedObjectNameException
     */
    public ObjectName objectName(ServiceEntry serviceEntry)
            throws MalformedObjectNameException {
        if (!StringUtils.isEmpty(serviceEntry.getId())) {
            return new ObjectName(mBeanServer.getDefaultDomain() + ":type="
                    + serviceEntry.getId());
        } else {
            return new ObjectName(mBeanServer.getDefaultDomain() + ":type="
                    + serviceEntry.getImplementation());
        }
    }

}