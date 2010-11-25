/**
 * @(#)XADataSourceFactory.java
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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.enhydra.jdbc.standard.StandardXADataSource;
import org.teaframework.container.ServiceFactory;
import org.teaframework.services.transaction.JotmTransactionService;

/**
 * <p>
 * XADataSource Factory.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class XADataSourceFactory extends DataSourceFactory {

	/**
	 * @param arg0
	 */
	public XADataSourceFactory() {
	}

	private DataSource xads;

	private Hashtable attributes = new Hashtable();

	private JotmTransactionService jotmTS = (JotmTransactionService) ServiceFactory
			.getInstance().getService(JotmTransactionService.class);

	private final Log logger = LogFactory.getLog(getClass());

	public DataSource getInstance() {
		xads = new StandardXADataSource();
		try {
			((StandardXADataSource) xads)
					.setDriverName((String) getAttribute(DatabaseConstant.JDBC_DRIVER_CLASS_NAME));
			((StandardXADataSource) xads)
					.setUrl((String) getAttribute(DatabaseConstant.JDBC_URL));
			((StandardXADataSource) xads)
					.setUser((String) getAttribute(DatabaseConstant.JDBC_USERNAME));
			((StandardXADataSource) xads)
					.setPassword((String) getAttribute(DatabaseConstant.JDBC_PASSWORD));
			String maxConNum = (String) getAttribute(DatabaseConstant.JDBC_MAX_POOL_SIZE);
			String minConNum = (String) getAttribute(DatabaseConstant.JDBC_MIN_POOL_SIZE);
			((StandardXADataSource) xads).setMaxCon(NumberUtils
					.toInt(maxConNum));
			((StandardXADataSource) xads).setMinCon(NumberUtils
					.toInt(minConNum));
			((StandardXADataSource) xads).setTransactionManager(jotmTS
					.getTransactionManager());
		} catch (Exception e) {
			logger.error("Init XADataSource failed " + e.getMessage());
		}
		return xads;
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
	 * Remove any configuration attribute associated with the specified name. If
	 * there is no such attribute, no action is taken.
	 * 
	 * @param name
	 *            Name of the attribute to remove
	 */
	public void removeAttribute(String name) {
		attributes.remove(name);

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
}