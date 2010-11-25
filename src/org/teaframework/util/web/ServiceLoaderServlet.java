/**
 * @(#)ServiceLoaderServlet.java
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

package org.teaframework.util.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.InputStream;

import org.teaframework.container.ServiceLoader;

/**
 * <p>
 * Service loader initialization servlet.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.3 $ $Date: 2006/02/15 08:45:46 $
 * @version Revision: 1.0
 */

public class ServiceLoaderServlet extends HttpServlet {

    public static final String WEB_CONFIG = "config";

    private ServletContext servletContext;

    private ServiceLoader serviceLoader = ServiceLoader.getInstance();

	private final Log logger = LogFactory.getLog(getClass());

    /**
     * Initialize the service context.
     */
    public void init() throws ServletException {
        servletContext = getServletContext();       
		String webConfig = this.getInitParameter(WEB_CONFIG);
		logger.info("Load service config " + webConfig);
		InputStream is = servletContext.getResourceAsStream(webConfig);
        serviceLoader.initService(is);
    }

    /**
     * Http request should never be send to this servlet.
     */
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    }

    /**
     * Destory services.
     */
    public void destroy() {
    }

}