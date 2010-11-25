/**
 * @(#)DatabaseConstant.java
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

/**
 * <p>
 * DataSource configuration definition.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.6 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class DatabaseConstant {

    // Database Factory Configration

    /**
     * The name of the property used to identify the LogFactory implementation
     * class name.
     */
    public static final String JDBC_DATABASE_FACTORY = "jdbc.databaseFactory";

    /**
     * The fully qualified class name of the fallback <code>LogFactory</code>
     * implementation class to use, if no other can be found.
     */
    public static final String JDBC_DEFAULT_FACTORY = "org.huihoo.jfox.soaf.services.jdbc.C3P0DataSourceFactory";

    /**
     * The name of the properties file to search for.
     */
    public static final String JDBC_FACTORY_PROPERTIES = "tea-database.properties";

    //  Common Database Configuration

    public static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";

    public static final String JDBC_URL = "jdbc.url";

    public static final String JDBC_USERNAME = "jdbc.username";

    public static final String JDBC_PASSWORD = "jdbc.password";

    // Database Connection Pool Configuration

    public static final String JDBC_INIT_POOL_SIZE = "jdbc.initialPoolSize";

    public static final String JDBC_MIN_POOL_SIZE = "jdbc.minPoolSize";

    public static final String JDBC_MAX_POOL_SIZE = "jdbc.maxPoolSize";

    public static final String JDBC_MAX_IDLE_TIME = "jdbc.maxIdleTime";

    // C3P0 Database Connection Pool Configuration

    public static final String C3P0_JDBC_IDLE_CONN_TEST_PERIOD = "c3p0.jdbc.idleConnectionTestPeriod";

    public static final String C3P0_JDBC_MAX_STATEMENTS = "c3p0.jdbc.maxStatements";

    public static final String C3P0_JDBC_PROPERTY_CYCLE = "c3p0.jdbc.propertyCycle";

    public static final String C3P0_JDBC_ACQUIRE_INCREMENT = "c3p0.jdbc.acquireIncrement";

    public static final String C3P0_JDBC_ACQUIRE_RETRY_ATTEMPTS = "c3p0.jdbc.acquireRetryAttempts";

    public static final String C3P0_JDBC_ACQUIRE_RETRY_DELAY = "c3p0.jdbc.acquireRetryDelay";

    public static final String C3P0_JDBC_BREAK_AFTER_ACQUIRE_FAILURE = "c3p0.jdbc.breakAfterAcquireFailure";

    public static final String C3P0_JDBC_TEST_CONN_ON_CHECKOUT = "c3p0.jdbc.testConnectionOnCheckout";

    public static final String C3P0_JDBC_AUTO_COMMIT_ON_CLOSE = "c3p0.jdbc.autoCommitOnClose";

    public static final String C3P0_JDBC_FORCE_IGNORE_UNRESOLVED_TRANS = "c3p0.jdbc.forceIgnoreUnresolvedTransactions";

    public static final String C3P0_JDBC_NUM_HELPER_THREADS = "c3p0.jdbc.numHelperThreads";

    public static final String C3P0_JDBC_USES_TRADITIONAL_REFELECTIVE_PROXIES = "c3p0.jdbc.usesTraditionalReflectiveProxies";

    public static final String C3P0_JDBC_FACTORY_CLASS_LOCATION = "c3p0.jdbc.factoryClassLocation";

}