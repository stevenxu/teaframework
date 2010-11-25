/**
 * @(#)OSWorkFlowServiceImpl.java
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

package org.teaframework.services.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.picocontainer.Startable;
import org.teaframework.util.resource.ResourceHelper;

import com.opensymphony.workflow.Workflow;
import com.opensymphony.workflow.basic.BasicWorkflow;
import com.opensymphony.workflow.config.Configuration;
import com.opensymphony.workflow.config.DefaultConfiguration;

/**
 * <p>
 * OSWorkFlowService Implementation.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:43 $
 * @version Revision: 1.0
 */

public class OSWorkFlowServiceImpl implements OSWorkFlowService, Startable {

	private static final String OSWORKFLOW_CONFIG = "osworkflow.xml";

	private Configuration config;

	private Workflow workflow;

	private final Log logger = LogFactory.getLog(getClass());

	/**
	 *  
	 */
	public OSWorkFlowServiceImpl() {
	}

	/**
	 * Start OSWorkFlow Service.
	 * 
	 * @see org.picocontainer.Startable#start()
	 */
	public void start() {
		config = new DefaultConfiguration();
		try {
			config.load(ResourceHelper.getResourceURL(OSWORKFLOW_CONFIG));
			workflow = new BasicWorkflow("OSWorkFlow");
			workflow.setConfiguration(config);
		} catch (Exception e) {
			logger.error("Failed to start osworkflow service " + e.getMessage());
		}
		logger.info("Start osworkflow service successful.");
	}

	/**
	 * @see org.picocontainer.Startable#stop()
	 */
	public void stop() {
	}

	/**
	 * Return workflow instantce.
	 */
	public Workflow getWorkflow() {
		return this.workflow;
	}
}