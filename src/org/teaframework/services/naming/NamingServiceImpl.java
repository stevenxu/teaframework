/**
 * @(#)NamingServiceImpl.java
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

package org.teaframework.services.naming;

import java.io.IOException;
import java.net.URL;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.carol.util.configuration.ConfigurationException;
import org.objectweb.carol.util.configuration.ConfigurationRepository;
import org.picocontainer.Startable;
import org.teaframework.util.resource.ResourceHelper;

/**
 * <p>
 * JNDI service implementation.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:27:46 $
 * @version Revision: 1.0
 */

public class NamingServiceImpl implements NamingService, Startable {

	private static final String JNDI_PROPERTIES = "carol.properties";

	private final Log logger = LogFactory.getLog(getClass());

	private URL jndiURL;

	private Context ctx;

	/**
	 * @see org.teaframework.services.naming.NamingService#bind(java.lang.String,
	 *      java.lang.Object)
	 */
	public void bind(String name, Object obj) throws NamingException {
		ctx.bind(name, obj);

	}

	/**
	 * @see org.teaframework.services.naming.NamingService#lookup(java.lang.String)
	 */
	public Object lookup(String name) throws NamingException {
		return ctx.lookup(name);
	}

	/**
	 * @see org.teaframework.services.naming.NamingService#rebind(java.lang.String,
	 *      java.lang.Object)
	 */
	public void rebind(String name, Object obj) throws NamingException {
		ctx.rebind(name, obj);
	}

	/**
	 * @see org.teaframework.services.naming.NamingService#unbind(java.lang.String)
	 */
	public void unbind(String name) throws NamingException {
		ctx.unbind(name);
	}

	/**
	 * @see org.picocontainer.Startable#start()
	 */
	public void start() {
		// initialize Carol
		try {
			jndiURL = ResourceHelper.getResourceURL(JNDI_PROPERTIES);
			ConfigurationRepository.init(jndiURL);
		} catch (IOException e) {
			logger.error("Failed to load jndi property file " + e.getMessage());
		} catch (ConfigurationException e) {
			logger.error("Failed to init carol configuraion file "
					+ e.getMessage());
		}

		// init jndi context
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			logger.error("Failed to init jndi context " + e.getMessage());
		}
		logger.info("Start Naming Service Successful");
	}

	/**
	 * @see org.picocontainer.Startable#stop()
	 */
	public void stop() {
	}
	
}