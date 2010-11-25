/**
 * @(#)AxisServiceEndPoint.java
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

package org.teaframework.services.ws.axis;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.server.ServiceLifecycle;
import javax.xml.rpc.server.ServletEndpointContext;

/**
 * <p>
 * Axis Servlet end point integration.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:03:33 $
 * @version Revision: 1.0
 */

public abstract class AxisServiceEndPoint implements ServiceLifecycle {	

	private ServletEndpointContext servletEndpointContext;

	/**
	 * Initialize this JAX-RPC servlet endpoint.
	 * @see javax.xml.rpc.server.ServiceLifecycle#init(java.lang.Object)
	 */
	public void init(Object context) throws ServiceException {
		this.servletEndpointContext = (ServletEndpointContext) context;

	}

	/**
	 * @see javax.xml.rpc.server.ServiceLifecycle#destroy()
	 */
	public void destroy() {
	}
}