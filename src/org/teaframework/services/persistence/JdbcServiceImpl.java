/**
 * @(#)JdbcServiceImpl.java
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

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.teaframework.exception.DAOException;

/**
 * <p>
 * JDBC persistence service implementation.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:44 $
 * @version Revision: 1.0
 */

public class JdbcServiceImpl implements JdbcService {

	private QueryRunner queryRunner;

	/**
	 * @param dataSource
	 */
	public JdbcServiceImpl() {
		queryRunner = new QueryRunner();
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#setDataSource(javax.sql.DataSource)
	 */
	public void setDataSource(DataSource dataSource) {
		queryRunner.setDataSource(dataSource);
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#query(java.sql.Connection,
	 *      java.lang.String, java.lang.Object,
	 *      org.apache.commons.dbutils.ResultSetHandler)
	 */
	public Object query(Connection conn, String sql, Object param,
			ResultSetHandler rsh) throws DAOException {
		try {
			return queryRunner.query(conn, sql, param, rsh);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "], parameterObject [" + param + "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#query(java.sql.Connection,
	 *      java.lang.String, java.lang.Object[],
	 *      org.apache.commons.dbutils.ResultSetHandler)
	 */
	public Object query(Connection conn, String sql, Object[] params,
			ResultSetHandler rsh) throws DAOException {
		try {
			return queryRunner.query(conn, sql, params, rsh);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#query(java.sql.Connection,
	 *      java.lang.String, org.apache.commons.dbutils.ResultSetHandler)
	 */
	public Object query(Connection conn, String sql, ResultSetHandler rsh)
			throws DAOException {
		try {
			return queryRunner.query(conn, sql, rsh);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#query(java.lang.String,
	 *      java.lang.Object, org.apache.commons.dbutils.ResultSetHandler)
	 */
	public Object query(String sql, Object param, ResultSetHandler rsh)
			throws DAOException {
		try {
			return queryRunner.query(sql, param, rsh);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#query(java.lang.String,
	 *      java.lang.Object[], org.apache.commons.dbutils.ResultSetHandler)
	 */
	public Object query(String sql, Object[] params, ResultSetHandler rsh)
			throws DAOException {
		try {
			return queryRunner.query(sql, params, rsh);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#query(java.lang.String,
	 *      org.apache.commons.dbutils.ResultSetHandler)
	 */
	public Object query(String sql, ResultSetHandler rsh) throws DAOException {
		try {
			return queryRunner.query(sql, rsh);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#update(java.sql.Connection,
	 *      java.lang.String, java.lang.Object)
	 */
	public int update(Connection conn, String sql, Object param)
			throws DAOException {
		try {
			return queryRunner.update(conn, sql, param);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#update(java.sql.Connection,
	 *      java.lang.String, java.lang.Object[])
	 */
	public int update(Connection conn, String sql, Object[] params)
			throws DAOException {
		try {
			return queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#update(java.sql.Connection,
	 *      java.lang.String)
	 */
	public int update(Connection conn, String sql) throws DAOException {
		try {
			return queryRunner.update(conn, sql);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#update(java.lang.String,
	 *      java.lang.Object)
	 */
	public int update(String sql, Object param) throws DAOException {
		try {
			return queryRunner.update(sql, param);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#update(java.lang.String,
	 *      java.lang.Object[])
	 */
	public int update(String sql, Object[] params) throws DAOException {
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}

	/**
	 * @see org.teaframework.services.persistence.JdbcService#update(java.lang.String)
	 */
	public int update(String sql) throws DAOException {
		try {
			return queryRunner.update(sql);
		} catch (SQLException e) {
			throw new DAOException("Failed to query - sql [" + sql
					+ "].  Cause: " + e, e);
		}
	}
}