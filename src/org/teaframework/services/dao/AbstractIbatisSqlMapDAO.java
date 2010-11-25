/**
 * @(#)AbstractIbatisSqlMapDAO.java
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

package org.teaframework.services.dao;

import org.teaframework.container.ServiceFactory;
import org.teaframework.services.persistence.IbatisService;
import org.teaframework.services.persistence.IbatisServiceImpl;
import org.teaframework.services.persistence.IbatisSessionFactoryService;

/**
 * <p>
 * IBatis DAO Template.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public abstract class AbstractIbatisSqlMapDAO extends AbstractDAO {

	private IbatisSessionFactoryService ibatisSessionFactoryService;

	/**
	 * Default Constructor
	 */
	public AbstractIbatisSqlMapDAO() {
		ibatisSessionFactoryService = (IbatisSessionFactoryService) ServiceFactory
				.getInstance().getService(IbatisSessionFactoryService.class);
	}

	/**
	 * @return Returns the ibatisService.
	 */
	public IbatisService getIbatisService() {
		return new IbatisServiceImpl();
	}

	/**
	 * @return Returns the ibatisSessionFactoryService.
	 */
	public IbatisSessionFactoryService getIbatisSessionFactoryService() {
		return ibatisSessionFactoryService;
	}

}