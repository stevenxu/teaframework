/**
 * @(#) DataSourceFactory.java
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

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.sql.DataSource;

import org.teaframework.exception.DataSourceConfigurationException;
import org.teaframework.util.resource.ResourceHelper;

/**
 * <p>
 * Factory for creating database connection instances, with discovery and
 * configuration features
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.6 $ $Date: 2005/05/22 06:49:48 $
 * @version Revision: 1.0
 */

public abstract class DataSourceFactory {

    /**
     * Protected constructor that is not available for public use.
     */
    protected DataSourceFactory() {
    }

    /**
     * The previously constructed <code>LogFactory</code> instances, keyed by
     * the <code>ClassLoader</code> with which it was created.
     */
    protected static Hashtable factories = new Hashtable();

    /**
     * Get singlton database connection instance.
     */
    public abstract DataSource getInstance()
            throws DataSourceConfigurationException, SQLException;

    /**
     * Return the configuration attribute with the specified name (if any), or
     * <code>null</code> if there is no such attribute.
     * 
     * @param name Name of the attribute to return
     */
    public abstract Object getAttribute(String name);

    /**
     * Return an array containing the names of all currently defined
     * configuration attributes. If there are no such attributes, a zero length
     * array is returned.
     */
    public abstract String[] getAttributeNames();

    /**
     * Remove any configuration attribute associated with the specified name. If
     * there is no such attribute, no action is taken.
     * 
     * @param name Name of the attribute to remove
     */
    public abstract void removeAttribute(String name);

    /**
     * Set the configuration attribute with the specified name. Calling this
     * with a <code>null</code> value is equivalent to calling
     * <code>removeAttribute(name)</code>.
     * 
     * @param name Name of the attribute to set
     * @param value Value of the attribute to set, or <code>null</code> to
     *            remove any setting for this attribute
     */
    public abstract void setAttribute(String name, Object value);

    public static DataSourceFactory getFactory()
            throws DataSourceConfigurationException {
        // Return any previously registered factory for this class loader
        DataSourceFactory factory = getCachedFactory(DataSourceFactory.class
                .getClassLoader());

        if (factory != null) {
            return factory;
        }

        // Load properties file
        Properties props = null;
        try {
            InputStream stream = ResourceHelper
                    .getResourceAsStream(DatabaseConstant.JDBC_FACTORY_PROPERTIES);

            if (stream != null) {
                props = new Properties();
                props.load(stream);
                stream.close();
            }
        } catch (IOException e) {
            throw new DataSourceConfigurationException(
                    "Load Database configuration IO exception " + e);
        } catch (SecurityException e) {
            throw new DataSourceConfigurationException(
                    "Database configuration access security exception " + e);
        }

        // Second try a properties file.
        if (factory == null && props != null) {
            String factoryClass = props
                    .getProperty(DatabaseConstant.JDBC_DATABASE_FACTORY);
            if (factoryClass != null && !factoryClass.equals("")) {
                factory = newFactory(factoryClass, DataSourceFactory.class
                        .getClassLoader());
            }
        }

        // Third, try the fallback implementation class
        if (factory == null) {
            factory = newFactory(DatabaseConstant.JDBC_DEFAULT_FACTORY,
                    DataSourceFactory.class.getClassLoader());
        }

        if (factory != null) {
            /**
             * Always cache using context class loader..
             */
            cacheFactory(DataSourceFactory.class.getClassLoader(), factory);

            if (props != null) {
                Enumeration names = props.propertyNames();
                while (names.hasMoreElements()) {
                    String name = (String) names.nextElement();
                    String value = props.getProperty(name);
                    factory.setAttribute(name, value);
                }
            }
        }

        return factory;
    }

    /**
     * Check cached factories (keyed by classLoader)
     */
    private static DataSourceFactory getCachedFactory(
            ClassLoader contextClassLoader) {
        DataSourceFactory factory = null;

        if (contextClassLoader != null) {
            factory = (DataSourceFactory) factories.get(contextClassLoader);
        }

        return factory;
    }

    private static void cacheFactory(ClassLoader classLoader,
            DataSourceFactory factory) {
        if (classLoader != null && factory != null) {
            factories.put(classLoader, factory);
        }
    }

    /**
     * Return a new instance of the specified <code>LogFactory</code>
     * implementation class, loaded by the specified class loader. If that
     * fails, try the class loader used to load this (abstract) LogFactory.
     * 
     * @param factoryClass Fully qualified name of the <code>LogFactory</code>
     *            implementation class
     * @param classLoader ClassLoader from which to load this class
     * @exception DataSourceConfigException if a suitable instance cannot be
     *                created
     */
    public static DataSourceFactory newFactory(final String factoryClass,
            final ClassLoader classLoader)
            throws DataSourceConfigurationException {
        Object result = AccessController.doPrivileged(new PrivilegedAction() {

            public Object run() {
                try {
                    if (classLoader != null) {
                        try {
                            return classLoader.loadClass(factoryClass)
                                    .newInstance();
                        } catch (ClassNotFoundException ex) {
                            if (classLoader == DataSourceFactory.class
                                    .getClassLoader()) {
                                // Nothing more to try, onwards.
                                throw ex;
                            }
                            // ignore exception, continue
                        } catch (NoClassDefFoundError e) {
                            if (classLoader == DataSourceFactory.class
                                    .getClassLoader()) {
                                throw e;
                            }

                        } catch (ClassCastException e) {

                            if (classLoader == DataSourceFactory.class
                                    .getClassLoader()) {
                                throw e;
                            }
                        }
                    }
                    return Class.forName(factoryClass).newInstance();
                } catch (Exception e) {
                    return new DataSourceConfigurationException(e);
                }
            }
        });

        if (result instanceof DataSourceConfigurationException) {
            throw (DataSourceConfigurationException) result;
        }

        return (DataSourceFactory) result;
    }

    /**
     * Retrive concreate datasource.
     * 
     * @return PoolDataSource
     * @throws DataSourceConfigurationException
     */
    public static DataSource getDataSource()
            throws DataSourceConfigurationException {
        try {
            return getFactory().getInstance();
        } catch (Exception e) {
            throw new DataSourceConfigurationException(e.getMessage(), e);
        }
    }

}