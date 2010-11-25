/**
 * @(#)ServiceLoader.java
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.teaframework.exception.ServiceConfigurationException;
import org.teaframework.schema.config.Configuration;
import org.teaframework.schema.config.ConfigurationEntry;
import org.teaframework.schema.config.Interceptor;
import org.teaframework.schema.service.Service;
import org.teaframework.schema.service.ServiceEntry;
import org.teaframework.util.resource.ResourceHelper;

/**
 * <p>
 * Service Configration load proxy.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.4 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class ServiceLoader {

    private ServiceContainer serviceContainer = ServiceContainer.getInstance();

    private static ServiceLoader serviceLoader;

    private boolean serviceLoaded = false;

    private Collection serviceSchemaColl = new LinkedList();

    private Collection interceptorColl = new ArrayList();

    private Collection serviceEntryColl = new ArrayList();

    private InterceptorManager interceptorManager = new InterceptorManagerImpl();

    private final Log logger = LogFactory.getLog(getClass());

    /**
     * Singleton instance
     * 
     * @return
     */
    public static ServiceLoader getInstance() {
        if (serviceLoader == null) {
            serviceLoader = new ServiceLoader();
        }
        return serviceLoader;
    }

    /**
     * Init services.
     * 
     * @param configFile
     * @throws ServiceConfigurationException
     */
    public synchronized void initService(String configFile)
            throws ServiceConfigurationException {
        if (!isServiceLoaded()) {
            try {
                InputStream is = ResourceHelper.getResourceAsStream(configFile);
                loadConfig(loadServiceConfigration(is));
            } catch (Throwable e) {
                throw new ServiceConfigurationException(
                        "Failed to init serivce config file tea-config.xml "
                                + e, e);
            }
            serviceContainer.start();
            serviceLoaded = true;
        }
    }

    /**
     * Init services from input stream.
     * 
     * @param is
     * @throws ServiceConfigurationException
     */
    public synchronized void initService(InputStream is)
            throws ServiceConfigurationException {
        if (!isServiceLoaded()) {
            try {
                loadConfig(loadServiceConfigration(is));
            } catch (Throwable e) {
                e.printStackTrace();
                throw new ServiceConfigurationException(
                        "Failed to init serivce config file tea-config.xml "
                                + e, e);

            }
            serviceContainer.start();
            serviceLoaded = true;
        }
    }

    /**
     * Load service configration file.
     * 
     * @return Service
     * @throws Throwable
     */
    private Configuration loadServiceConfigration(InputStream is)
            throws ServiceConfigurationException {
        InputStreamReader isr = null;
        Configuration config = null;
        try {
            isr = new InputStreamReader(is);
            config = Configuration.unmarshal(isr);
        } catch (MarshalException e) {
            throw new ServiceConfigurationException(
                    "Failed to marshal service configuration " + e, e);
        } catch (ValidationException e) {
            throw new ServiceConfigurationException(
                    "Failed to validate service configuration " + e, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return config;
    }

    /**
     * Load all configuration service.
     * 
     * @param service
     */
    public void loadConfig(Configuration config) throws Throwable {
        processConfig(config);
        loadService(serviceSchemaColl);
        interceptorManager
                .processInterceptor(interceptorColl, serviceEntryColl);
    }

    /**
     * Load service schema.
     * 
     * @param serviceSchemaColl
     * @throws ServiceConfigurationException
     */
    private void loadService(Collection serviceSchemaColl)
            throws ServiceConfigurationException {
        InputStream is = null;
        InputStreamReader isr = null;
        Iterator iter = serviceSchemaColl.iterator();
        Service service;
        try {
            while (iter.hasNext()) {
                is = ResourceHelper.getResourceAsStream((String) iter.next());
                isr = new InputStreamReader(is);
                service = Service.unmarshal(isr);
                processService(service);
            }
        } catch (Exception e) {
            throw new ServiceConfigurationException("Failed to load service "
                    + e, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Add service to service entry collection.
     * 
     * @param service
     */
    private void processService(Service service) {
        ServiceEntry[] serviceEntry = service.getServiceEntry();
        for (int i = 0; i < serviceEntry.length; i++) {
            serviceEntryColl.add(serviceEntry[i]);
        }
    }

    /**
     * Is service loaded?
     * 
     * @return boolean
     */
    public boolean isServiceLoaded() {
        return this.serviceLoaded;
    }

    /**
     * Add service schema and interceptor to collection.
     * 
     * @param config
     */
    private void processConfig(Configuration config) {
        ConfigurationEntry[] configEntry = config.getServiceConfiguration()
                .getConfigurationEntry();
        Interceptor[] interceptor = config.getSystemInterceptor()
                .getInterceptor();
        for (int i = 0; i < configEntry.length; i++) {
            logger.info("Load service schema : " + configEntry[i].getValue());
            serviceSchemaColl.add(configEntry[i].getValue());
        }

        for (int i = 0; i < interceptor.length; i++) {
            interceptorColl.add(interceptor[i].getValue());
        }
    }
}