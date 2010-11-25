/**
 * @(#)SessionFactoryServiceImpl.java
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

package org.teaframework.services.persistence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.picocontainer.Startable;
import org.teaframework.util.resource.ResourceHelper;

/**
 * <p>
 * SessionFactory Service Implementation
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class HibernateSessionFactoryServiceImpl implements HibernateSessionFactoryService,
		Startable {
	
	private final Log logger = LogFactory.getLog(getClass());

	private static String HIBERNATE_MULTI_CONFIG = "hibernate-multi.properties";

	private static String HIBERNATE_DEFAULT_CONFIG = "hibernate.default";

	private Properties props;

	private HashMap hashMap = new HashMap();

	/**
	 * @see org.teaframework.services.persistence.HibernateSessionFactoryService#getSessionFactory(java.lang.String)
	 */
	public SessionFactory getSessionFactory(String key) {
		if (key == null) {
			return (SessionFactory) hashMap.get(HIBERNATE_DEFAULT_CONFIG);
		} else {
			return (SessionFactory) hashMap.get(key);
		}
	}

	/**
	 * @see org.picocontainer.Startable#start()
	 */
	public void start() {
		loadConfig();
	}

	/**
	 * Load configuation.
	 */
	private void loadConfig() {
		try {
			props = ResourceHelper
					.getResourceAsProperties(HIBERNATE_MULTI_CONFIG);
			Set set = props.keySet();
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = props.getProperty(key);
				buildFactory(key, value);
			}
		} catch (Exception e) {
			logger.error("Hibernate multi configuration initialization failed ", e);
		}
	}

	/**
	 * @param key
	 * @param value
	 * @throws HibernateException
	 */
	private void buildFactory(String key, String value)
			throws HibernateException, IOException {
		Configuration config = new Configuration();
		config.configure(ResourceHelper.getResourceURL(value));
		SessionFactory sessionFactory = config.buildSessionFactory();
		hashMap.put(key, sessionFactory);
	}

	/**
	 * @see org.picocontainer.Startable#stop()
	 */
	public void stop() {
	}
}