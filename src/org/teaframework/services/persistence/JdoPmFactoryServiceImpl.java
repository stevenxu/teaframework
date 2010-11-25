/**
 * @(#)PersistenceManagerFactoryServiceImpl.java
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

import javax.jdo.PersistenceManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jpox.PersistenceManagerFactoryImpl;
import org.picocontainer.Startable;
import org.teaframework.util.resource.ResourceHelper;

/**
 * <p>
 * PersistenceManagerFactory Service Implementation
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class JdoPmFactoryServiceImpl implements
		JdoPmFactoryService, Startable {

	private final Log logger = LogFactory.getLog(getClass());

	private static String JDO_MULTI_CONFIG = "jdo-multi.properties";

	private static String JDO_DEFAULT_CONFIG = "jdo.default";

	private Properties props;

	private HashMap hashMap = new HashMap();

	/**
	 * @see org.teaframework.services.persistence.JdoPmFactoryService#getPersistenceManagerFactory(java.lang.String)
	 */
	public PersistenceManagerFactory getPersistenceManagerFactory(String key) {
		if (key == null) {
			return (PersistenceManagerFactory) hashMap.get(JDO_DEFAULT_CONFIG);
		} else {
			return (PersistenceManagerFactory) hashMap.get(key);
		}
	}

	/**
	 * @see org.picocontainer.Startable#start()
	 */
	public void start() {
		loadConfig();
	}

	/**
	 * @see org.picocontainer.Startable#stop()
	 */
	public void stop() {		
	}

	/**
	 * Load configuation.
	 */
	private void loadConfig() {
		try {
			props = ResourceHelper.getResourceAsProperties(JDO_MULTI_CONFIG);
			Set set = props.keySet();
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				String value = props.getProperty(key);
				buildPersistenceManagerFactory(key, value);
			}
		} catch (Exception e) {
			logger.error("JDO multi configuration initialization failed ", e);
		}
	}

	/**
	 * Build PersistenceManagerFactory.
	 * 
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	private void buildPersistenceManagerFactory(String key, String value)
			throws IOException {
		Properties prop = ResourceHelper.getResourceAsProperties(value);
		PersistenceManagerFactory pmFactory = PersistenceManagerFactoryImpl.getPersistenceManagerFactory(prop);	
		hashMap.put(key, pmFactory);
	}
	
}