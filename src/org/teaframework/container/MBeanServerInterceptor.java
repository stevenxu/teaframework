/**
 * @(#)MBeanServerInterceptor.java
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

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.teaframework.util.resource.ResourceHelper;

/**
 * MBeanServerInterceptor intercept manageable service request and regist
 * service instance to a MBeanServer and ServiceContainer.
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.15 $ $Date: 2005/05/22 06:46:49 $
 * @version Revision: 1.0
 */

public class MBeanServerInterceptor implements ServiceInterceptor {

	private final Log logger = LogFactory.getLog(getClass());

	private ManageableServiceContainer manageableServiceContainer = ManageableServiceContainer
			.getInstance();

	/**
	 * Interceptor invoke.
	 * 
	 * @param serviceEntry
	 * @throws Throwable
	 */
	public void invoke(ServiceEntry serviceEntry) throws Throwable {

		if (BooleanUtils.toBoolean(serviceEntry.getManageable())) {
			logger.info("Register Managable Service : "
					+ serviceEntry.getImplementation());
			Object object = ResourceHelper.instantiate(StringUtils
					.trim(serviceEntry.getImplementation()));
			synchronized (manageableServiceContainer) {
				manageableServiceContainer.registManagebleService(object,
						serviceEntry);
			}
		}
	}

}