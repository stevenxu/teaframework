/**
 * @(#)IbatisSessionFactoryServiceImpl.java
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

import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.picocontainer.Startable;
import org.teaframework.exception.IbatisConfigurationException;

import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * <p>
 * Description </p
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version Revision: Date: Sep 1, 2006
 * @version Revision: 1.0
 * 
 */
public class IbatisSessionFactoryServiceImpl implements
		IbatisSessionFactoryService, Startable {

	private final Log logger = LogFactory.getLog(getClass());

	private static final String IBATIS_DAO_RESOURCE = "ibatis-dao.xml";

	private static final String IBATIS_SQLMAP_RESOURCE = "ibatis-sqlmap-config.xml";

	private DaoManager daoManager;

	private SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient() {
		return this.sqlMapClient;
	}

	/**
	 * @see org.picocontainer.Startable#start()
	 */
	public void start() {
		try {
			Reader reader = Resources.getResourceAsReader(IBATIS_DAO_RESOURCE);
			Reader sqlMapClientReader = Resources
					.getResourceAsReader(IBATIS_SQLMAP_RESOURCE);
			daoManager = DaoManagerBuilder.buildDaoManager(reader);
			sqlMapClient = SqlMapClientBuilder
					.buildSqlMapClient(sqlMapClientReader);
		} catch (Exception e) {
			throw new IbatisConfigurationException(
					"Failed to initialize Ibatis DaoConfig.  Cause: " + e);
		}
		logger.info("Start ibatis service successful.");
	}

	/**
	 * @see org.picocontainer.Startable#stop()
	 */
	public void stop() {
	}

}
