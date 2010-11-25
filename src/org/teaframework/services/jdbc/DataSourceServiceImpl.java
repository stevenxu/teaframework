/**
 * @(#) DataSourceServiceImpl.java
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

package org.teaframework.services.jdbc;

import javax.sql.DataSource;

import org.teaframework.exception.DataSourceConfigurationException;
import org.teaframework.exception.ServiceLocatorException;
import org.teaframework.services.ejb.ServiceLocator;

import org.apache.commons.lang.StringUtils;

/**
 * Database datasource serivce implementation
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.5 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class DataSourceServiceImpl implements DataSourceService {

    private ServiceLocator serviceLocator;

    /**
     * @param serviceLocator
     */
    public DataSourceServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    /**
     * @see org.teaframework.services.jdbc.DataSourceService#getDataSource(java.lang.String)
     */
    public DataSource getDataSource(String dataSourceName)
            throws DataSourceConfigurationException, ServiceLocatorException {
        if (StringUtils.isEmpty(dataSourceName)) {
            return DataSourceFactory.getDataSource();
        } else {
            return serviceLocator.getDataSource(dataSourceName);
        }
    }   
    

}