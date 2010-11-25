/**
 * @(#)IbatisService.java
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

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.teaframework.exception.DataSourceConfigurationException;

import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PoolConfig;

/**
 * <p>
 * C3P0 DataSource Factory
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.7 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class C3P0DataSourceFactory extends DataSourceFactory {

	private final Log logger = LogFactory.getLog(getClass());

	private DataSource dataSource;

	//  The configuration attributes for this {@link DataSourceFactory}.
	private Hashtable attributes = new Hashtable();

	/**
	 * @see org.teaframework.services.jdbc.DataSourceFactory#getInstance()
	 * @return DataSource instance
	 * @throws DataSourceConfigurationException
	 */
	public DataSource getInstance() throws DataSourceConfigurationException,
			SQLException {
		String jdbcDriverClass = (String) getAttribute(DatabaseConstant.JDBC_DRIVER_CLASS_NAME);
		if (StringUtils.isEmpty(jdbcDriverClass)) {
			logger
					.warn("No JDBC Driver class was specified by tea-database.properties : "
							+ DatabaseConstant.JDBC_DRIVER_CLASS_NAME);
		} else {
			try {
				Class.forName(jdbcDriverClass);
			} catch (ClassNotFoundException e) {
				String errorMsg = "JDBC Driver class not found: "
						+ jdbcDriverClass;
				logger.fatal(errorMsg);
				throw new DataSourceConfigurationException(errorMsg, e);
			}
		}

		// Set pool configuration
		PoolConfig poolConfig = new PoolConfig();
		String initPoolSize = (String) getAttribute(DatabaseConstant.JDBC_INIT_POOL_SIZE);

		if (!NumberUtils.isNumber(initPoolSize)) {
			poolConfig.setInitialPoolSize(NumberUtils.toInt(initPoolSize));
		}

		String minPoolSize = (String) getAttribute(DatabaseConstant.JDBC_MIN_POOL_SIZE);

		if (!NumberUtils.isNumber(minPoolSize)) {
			poolConfig.setMinPoolSize(NumberUtils.toInt(minPoolSize));
		}

		String maxPoolSize = (String) getAttribute(DatabaseConstant.JDBC_MAX_POOL_SIZE);

		if (!!NumberUtils.isNumber(maxPoolSize)) {
			poolConfig.setMaxPoolSize(NumberUtils.toInt(maxPoolSize));
		}

		String maxIdleTime = (String) getAttribute(DatabaseConstant.JDBC_MAX_IDLE_TIME);

		if (!NumberUtils.isNumber(maxIdleTime)) {
			poolConfig.setMaxIdleTime(NumberUtils.toInt(maxIdleTime));
		}

		String idleConnTestPeriod = (String) getAttribute(DatabaseConstant.C3P0_JDBC_IDLE_CONN_TEST_PERIOD);

		if (!NumberUtils.isNumber(maxIdleTime)) {
			poolConfig.setIdleConnectionTestPeriod(NumberUtils
					.toInt(idleConnTestPeriod));
		}

		String maxStatements = (String) getAttribute(DatabaseConstant.C3P0_JDBC_MAX_STATEMENTS);

		if (!NumberUtils.isNumber(maxStatements)) {
			poolConfig.setMaxStatements(NumberUtils.toInt(maxStatements));
		}

		String propertyCycle = (String) getAttribute(DatabaseConstant.C3P0_JDBC_PROPERTY_CYCLE);

		if (!NumberUtils.isNumber(propertyCycle)) {
			poolConfig.setPropertyCycle(NumberUtils.toInt(propertyCycle));
		}

		String acquireIncrement = (String) getAttribute(DatabaseConstant.C3P0_JDBC_ACQUIRE_INCREMENT);

		if (!NumberUtils.isNumber(acquireIncrement)) {
			poolConfig.setAcquireIncrement(NumberUtils.toInt(acquireIncrement));
		}

		// c3p0 version is below 0.8.5 , does't support acquireRetryAttempts,
		// acquireRetryDelay, breakAfterAcquireFailure,
		// usesTraditionalReflectiveProxies

		String forceIgnoreUnresolvedTransactions = (String) getAttribute(DatabaseConstant.C3P0_JDBC_FORCE_IGNORE_UNRESOLVED_TRANS);

		if (!StringUtils.isEmpty(forceIgnoreUnresolvedTransactions)) {
			poolConfig.setForceIgnoreUnresolvedTransactions(BooleanUtils
					.toBoolean(forceIgnoreUnresolvedTransactions));
		}

		String numHelperThreads = (String) getAttribute(DatabaseConstant.C3P0_JDBC_NUM_HELPER_THREADS);

		if (!NumberUtils.isNumber(numHelperThreads)) {
			poolConfig.setNumHelperThreads(NumberUtils.toInt(numHelperThreads));
		}

		String jdbcURL = (String) getAttribute(DatabaseConstant.JDBC_URL);
		if (StringUtils.isEmpty(jdbcURL)) {
			logger.error("No JDBC url was specified by database.properties");
			throw new DataSourceConfigurationException(
					"No JDBC url was specified");
		} else {
			String jdbcUsername = (String) getAttribute(DatabaseConstant.JDBC_USERNAME);
			String jdbcPassword = (String) getAttribute(DatabaseConstant.JDBC_PASSWORD);

			DataSource unpooledDataSource = DataSources.unpooledDataSource(
					jdbcURL, jdbcUsername, jdbcPassword);
			dataSource = DataSources.pooledDataSource(unpooledDataSource,
					poolConfig);
		}
		return dataSource;
	}

	/**
	 * Return the configuration attribute with the specified name (if any), or
	 * <code>null</code> if there is no such attribute.
	 * 
	 * @param name
	 *            Name of the attribute to return
	 * @return attribute
	 */
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	/**
	 * Return an array containing the names of all currently defined
	 * configuration attributes. If there are no such attributes, a zero length
	 * array is returned.
	 * 
	 * @return attribute names
	 */
	public String[] getAttributeNames() {
		Vector names = new Vector();
		Enumeration keys = attributes.keys();
		while (keys.hasMoreElements()) {
			names.addElement(keys.nextElement());
		}
		String results[] = new String[names.size()];
		for (int i = 0; i < results.length; i++) {
			results[i] = (String) names.elementAt(i);
		}
		return (results);
	}

	/**
	 * Set the configuration attribute with the specified name. Calling this
	 * with a <code>null</code> value is equivalent to calling
	 * <code>removeAttribute(name)</code>.
	 * 
	 * @param name
	 *            Name of the attribute to set
	 * @param value
	 *            Value of the attribute to set, or <code>null</code> to
	 *            remove any setting for this attribute
	 */
	public void setAttribute(String name, Object value) {
		if (value == null) {
			attributes.remove(name);
		} else {
			attributes.put(name, value);
		}
	}

	/**
	 * Remove any configuration attribute associated with the specified name. If
	 * there is no such attribute, no action is taken.
	 * 
	 * @param name
	 *            Name of the attribute to remove
	 */
	public void removeAttribute(String name) {
		attributes.remove(name);
	}

}