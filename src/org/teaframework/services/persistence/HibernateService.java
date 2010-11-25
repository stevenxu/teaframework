/**
 * @(#)HibernateService.java
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

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.LockMode;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.type.Type;

import org.teaframework.exception.DAOException;

/**
 * <p>
 * Hibernate persistence service, Wrapper service for hibernate.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.6 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public interface HibernateService {

	/**
	 * Set SessionFactory
	 * 
	 * @param sessionFactory
	 */
	void setSessionFactory(SessionFactory sessionFactory);

	/**
	 * Create database connection and open a Session on it.
	 * 
	 * @return a Hibernate Session object
	 */
	Session openSession() throws HibernateException;

	/**
	 * commit transaction currently underway, and start new one ( as side effect
	 * hibernate session will be flushed )
	 */
	void commit() throws HibernateException;

	/**
	 * rollback active transaction if any was started. transaction will be reset
	 * 
	 * @throws HibernateException
	 *             if transaction can not be rolled back
	 */
	void rollback() throws HibernateException;

	/**
	 * normal session close. commit transaction is any
	 */
	void closeSession() throws HibernateException;

	/**
	 * reset and clean up everything. shall be used is something went wrong (
	 * for example you received hibernate exception )
	 */
	void reset();

	/**
	 * Check if this instance is associated with this Session
	 * 
	 * @param object
	 *            an instance of a persistent class
	 * @return true if the given instance is associated with this Session
	 * @throws HibernateException
	 * @throws DAOException
	 */
	boolean contains(Object object) throws HibernateException, DAOException;

	/**
	 * Remove a persistent instance from the datastore.
	 * 
	 * @param object
	 * @throws HibernateException
	 */
	void delete(Object object) throws HibernateException, DAOException;

	/**
	 * Remove a persistent instance from the datastore with lock mode.
	 * 
	 * @param object
	 * @param lockMode
	 * @throws HibernateException
	 */
	void delete(Object object, LockMode lockMode) throws HibernateException,
			DAOException;

	/**
	 * Delete all objects returned by the query. Return the number of objects
	 * deleted.
	 * 
	 * @param query
	 * @return the number of instances deleted
	 * @throws HibernateException
	 * @throws DAOException
	 */
	int delete(String query) throws HibernateException, DAOException;

	/**
	 * Delete all objects returned by the query. Return the number of objects
	 * deleted.
	 * 
	 * @param query
	 * @return the number of instances deleted
	 * @throws HibernateException
	 * @throws DAOException
	 */
	int delete(String query, Object value, Type type)
			throws HibernateException, DAOException;

	/**
	 * Delete all objects returned by the query. Return the number of objects
	 * deleted.
	 * 
	 * @param query
	 * @return the number of instances deleted
	 * @throws HibernateException
	 * @throws DAOException
	 */
	int delete(String query, Object[] values, Type[] types)
			throws HibernateException, DAOException;

	/**
	 * Remove this instance from the session cache. Changes to the instance will
	 * not be synchronized with the database. This operation cascades to
	 * associated instances if the association is mapped with cascade="all" or
	 * cascade="all-delete-orphan".
	 * 
	 * @param object
	 *            object - a persistent instance
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void evict(Object object) throws HibernateException, DAOException;

	/**
	 * Execute a query.
	 * 
	 * @param query
	 *            a query expressed in Hibernate's query language
	 * @return a distinct list of instances (or arrays of instances)
	 * @throws HibernateException
	 * @throws DAOException
	 */
	List find(String query) throws HibernateException, DAOException;

	/**
	 * Execute a query with bind parameters. Bind a value to a "?" parameter in
	 * the query string.
	 * 
	 * @param query
	 * @param value
	 * @param type
	 * @return a distinct list of instances (or arrays of instances)
	 * @throws HibernateException
	 * @throws DAOException
	 */
	List find(String query, Object value, Type type) throws HibernateException,
			DAOException;

	/**
	 * Execute a query with bind parameters. Binding an array of values to "?"
	 * parameters in the query string.
	 * 
	 * @param query
	 * @param values
	 * @param types
	 * @return a distinct list of instances
	 * @throws HibernateException
	 * @throws DAOException
	 */
	List find(String query, Object[] values, Type[] types)
			throws HibernateException, DAOException;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if there is no such persistent instance.
	 * 
	 * @param clazz
	 * @param id
	 * @return a persistent instance or null
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Object get(Class clazz, final Serializable id) throws HibernateException,
			DAOException;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if there is no such persistent instance. Obtain the
	 * specified lock mode if the instance exists.
	 * 
	 * @param clazz
	 * @param id
	 * @param lockMode
	 * @return a persistent instance or null
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Object get(Class clazz, final Serializable id, LockMode lockMode)
			throws HibernateException, DAOException;

	/**
	 * Execute a query and return the results in an iterator. If the query has
	 * multiple return values, values will be returned in an array of type
	 * Object[]. Entities returned as results are initialized on demand. The
	 * first SQL query returns identifiers only. So iterate() is usually a less
	 * efficient way to retrieve objects than find().
	 * 
	 * @param query
	 * @return an iterator
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Iterator iterate(String query) throws HibernateException, DAOException;

	/**
	 * Execute a query and return the results in an iterator. Write the given
	 * value to "?" in the query string. If the query has multiple return
	 * values, values will be returned in an array of type Object[]. Entities
	 * returned as results are initialized on demand. The first SQL query
	 * returns identifiers only. So iterate() is usually a less efficient way to
	 * retrieve objects than find().
	 * 
	 * @param query -
	 *            the query string
	 * @param value -
	 *            a value to be witten to a "?" placeholder in the query string
	 * @param type -
	 *            the hibernate type of value
	 * @return an iterator
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Iterator iterate(String query, Object value, Type type)
			throws HibernateException, DAOException;

	/**
	 * Execute a query and return the results in an iterator. Write the given
	 * values to "?" in the query string. If the query has multiple return
	 * values, values will be returned in an array of type Object[]. Entities
	 * returned as results are initialized on demand. The first SQL query
	 * returns identifiers only. So iterate() is usually a less efficient way to
	 * retrieve objects than find().
	 * 
	 * @param query
	 * @param values
	 * @param types
	 * @return
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Iterator iterate(String query, Object[] values, Type[] types)
			throws HibernateException, DAOException;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, assuming that the instance exists. You should not use this
	 * method to determine if an instance exists (use find() instead). Use this
	 * only to retrieve an instance that you assume exists, where non-existence
	 * would be an actual error.
	 * 
	 * @param clazz
	 * @param id
	 * @return the persistent instance or proxy
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Object load(Class clazz, final Serializable id) throws HibernateException,
			DAOException;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, obtaining the specified lock mode, assuming the instance
	 * exists.
	 * 
	 * @param clazz
	 * @param id
	 * @param lockMode
	 * @return the persistent instance or proxy
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Object load(Class clazz, final Serializable id, LockMode lockMode)
			throws HibernateException, DAOException;

	/**
	 * Read the persistent state associated with the given identifier into the
	 * given transient instance.
	 * 
	 * @param object
	 * @param id
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void load(Object object, final Serializable id) throws HibernateException,
			DAOException;

	/**
	 * Obtain the specified lock level upon the given object.
	 * 
	 * @param object -
	 *            a persistent or transient instance
	 * @param lockMode -
	 *            the lock level
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void lock(Object object, LockMode lockMode) throws HibernateException,
			DAOException;

	/**
	 * Update the persistent instance with the identifier of the given transient
	 * instance. If there is a persistent instance with the same identifier, an
	 * exception is thrown. If the given transient instance has a null
	 * identifier, an exception will be thrown.
	 * 
	 * @param object -
	 *            a transient instance containing updated state
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void update(Object object) throws HibernateException, DAOException;

	/**
	 * Update the persistent state associated with the given identifier. An
	 * exception is thrown if there is a persistent instance with the same
	 * identifier in the current session.
	 * 
	 * @param object -
	 *            a transient instance containing updated state
	 * @param id -
	 *            identifier of persistent instance
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void update(Object object, final Serializable id)
			throws HibernateException, DAOException;

	/**
	 * Copy the state of the given object onto the persistent object with the
	 * same identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instance. If
	 * the given instance is unsaved or does not exist in the database, save it
	 * and return it as a newly persistent instance. Otherwise, the given
	 * instance does not become associated with the session.
	 * 
	 * @param object
	 * @return an updated persistent instance
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Object saveOrUpdateCopy(Object object) throws HibernateException,
			DAOException;

	/**
	 * Copy the state of the given object onto the persistent object with the
	 * given identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instance. If
	 * there is no database row with the given identifier, save the given
	 * instance and return it as a newly persistent instance. Otherwise, the
	 * given instance does not become associated with the session.
	 * 
	 * @param object -
	 *            a persistent or transient instance with state to be copied
	 * @param id -
	 *            the identifier of the instance to copy to
	 * @return an updated persistent instance
	 * @throws HibernateException
	 * @throws DAOException
	 */
	Object saveOrUpdateCopy(Object object, final Serializable id)
			throws HibernateException, DAOException;

	/**
	 * Re-read the state of the given instance from the underlying database. It
	 * is inadvisable to use this to implement long-running sessions that span
	 * many business tasks. This method is, however, useful in certain special
	 * circumstances.
	 * 
	 * @param object
	 *            a persistent or transient instance
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void refresh(Object object) throws HibernateException, DAOException;

	/**
	 * Re-read the state of the given instance from the underlying database,
	 * with the given LockMode. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is,
	 * however, useful in certain special circumstances.
	 * 
	 * @param object
	 *            a persistent or transient instance
	 * @param lockMode
	 *            the lock mode to use
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void refresh(Object object, LockMode lockMode) throws HibernateException,
			DAOException;

	/**
	 * Persist the given transient instance, first assigning a generated
	 * identifier.
	 * 
	 * @param object - -
	 *            a transient instance of a persistent class
	 * @return the generated identifier
	 * @throws HibernateException
	 */
	Serializable save(Object object) throws HibernateException, DAOException;

	/**
	 * Persist the given transient instance, using the given identifier.
	 * 
	 * @param object -
	 *            a transient instance of a persistent class
	 * @param id -
	 *            an unused valid identifier
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void save(Object object, final Serializable id) throws HibernateException,
			DAOException;

	/**
	 * Either save() or update() the given instance, depending upon the value of
	 * its identifier property. By default the instance is always saved. This
	 * behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping.
	 * 
	 * @param object -
	 *            a transient instance containing new or updated state
	 * @throws HibernateException
	 * @throws DAOException
	 */
	void saveOrUpdate(Object object) throws HibernateException, DAOException;

}