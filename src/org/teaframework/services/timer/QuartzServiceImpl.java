/**
 * @(#)QuartzServiceImpl.java
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
package org.teaframework.services.timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.picocontainer.Startable;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * <p>
 * Quartz service implementation.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class QuartzServiceImpl implements QuartzService, Startable {

	private static final Log log = LogFactory.getLog(QuartzServiceImpl.class);

	private Scheduler scheduler;

	/**
	 * Retrieve scheduler.
	 * 
	 * @return scheduler.
	 */
	public Scheduler getScheduler() {
		return this.scheduler;
	}

	/**
	 * Schedule job with job detail.
	 * 
	 * @param jobDetail
	 * @param trigger
	 */
	public void schedule(JobDetail jobDetail, Trigger trigger)
			throws SchedulerException {
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * Schedule job with trigger.
	 * 
	 * @param trigger
	 * @throws SchedulerException
	 */
	public void schedule(Trigger trigger) throws SchedulerException {
		scheduler.scheduleJob(trigger);
	}

	/**
	 * Start quartz service.
	 * 
	 * @see org.picocontainer.Startable#start()
	 */
	public void start() {
		log.info("Starting quartz service");
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			log.error("Cannot start quartz scheduler.", e);
		}
	}

	/**
	 * Stopping quartz service.
	 * 
	 * @see org.picocontainer.Startable#stop()
	 */
	public void stop() {
		log.info("Stopping quartz service");
		try {
			scheduler.shutdown();
		} catch (SchedulerException se) {
			log.error("Cannot shutdown quartz scheduler.", se);
		}
	}

}