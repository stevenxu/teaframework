/**
 * @(#)ServiceLoaderPlugIn.java
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

import java.io.InputStream;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.teaframework.container.ServiceLoader;
import org.teaframework.exception.ServiceConfigurationException;

/**
 * <p>
 * Struts service loader plugin
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:44 $
 * @version Revision: 1.0
 */

public class ServiceLoaderPlugIn implements PlugIn {

	private ServiceLoader serviceLoader = ServiceLoader.getInstance();

	/**
	 * Logging output for this plug in instance.
	 */
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The web application resource path of our persistent database storage
	 * file.
	 */
	private String pathname = "/WEB-INF/tea-config.xml";

	/**
	 * Destroy service config.
	 * 
	 * @see org.apache.struts.action.PlugIn#destroy()
	 */
	public void destroy() {
	}

	/**
	 * Initialize the service config resources for this module.
	 * 
	 * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet,
	 *      org.apache.struts.config.ModuleConfig)
	 */
	public void init(ActionServlet actionServlet, ModuleConfig moduleConfig)
			throws ServletException {
		if (log.isInfoEnabled()) {
			log.info("Loading service config from '" + pathname + "'");
		}

		try {
			initServiceConfig(actionServlet);
		} catch (ServiceConfigurationException e) {
			log.error("Service configuration initialization failed", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param actionServlet
	 */
	private void initServiceConfig(ActionServlet actionServlet)
			throws ServiceConfigurationException {
		InputStream is = actionServlet.getServletContext().getResourceAsStream(
				pathname);
		serviceLoader.initService(is);
	}

}