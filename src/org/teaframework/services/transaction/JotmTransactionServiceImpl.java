/**
 * @(#)JotmTransactionServiceImpl.java
 * 
 * Tea Service-Oriented Java/J2EE Application Framework
 * 
 * Copyright(c) Tea Framework Team
 *  
 * Copyright(c) Tea Framework Team
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

package org.teaframework.services.transaction;

import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.jotm.Jotm;
import org.objectweb.transaction.jta.TMService;
import org.picocontainer.Startable;

/**
 * <p>
 * JOTM transaction service implementation.
 * </p>
 * 
 * @author <a href="mailto:agilesource@gmail.com">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:46 $
 * @version Revision: 1.0
 */

public class JotmTransactionServiceImpl implements JotmTransactionService,
		Startable {
	
	private TMService jotm;
	
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * @see org.teaframework.services.transaction.JotmTransactionService#getTransactionManager()
	 */
	public TransactionManager getTransactionManager() {
		return jotm.getTransactionManager();
	}

	/**
	 * @see org.teaframework.services.transaction.JotmTransactionService#getUserTransaction()
	 */
	public UserTransaction getUserTransaction() {
		return jotm.getUserTransaction();
	}

	/**
	 * Start jotm transaction service.
	 * 
	 * @see org.picocontainer.Startable#start()
	 */
	public void start() {
		try {
			jotm = new Jotm(true, false);
		} catch (NamingException e) {
			logger.error("Failed to start JOTM transaction service " + e.getMessage());
		}
		logger.info("Start JOTM transaction service successful.");
	}

	/**
	 * Stop jotm transaction service.
	 * 
	 * @see org.picocontainer.Startable#stop()
	 */
	public void stop() {
		jotm.stop();
		logger.info("Stop JOTM transaction service successful.");
	}
}