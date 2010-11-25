/**
 * @(#)JDOService.java
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

import java.util.Collection;
import java.util.Map;

import javax.jdo.JDOException;
import javax.jdo.PersistenceManagerFactory;

import org.teaframework.exception.DAOException;

/**
 * <p>
 * JDO persistence service wrapper.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public interface JdoService {

	/**
	 * Set SessionFactory
	 * 
	 * @param sessionFactory
	 */
	void setPersistenceManagerFactory(PersistenceManagerFactory pmFactory);

	/**
	 * Begin a transaction.
	 * 
	 * @throws JDOException
	 */
	void beginTransaction() throws JDOException;

	/**
	 * Commit the current transaction.
	 * 
	 * @throws JDOException
	 */
	void commit() throws JDOException;

	/**
	 * Roll back the current transaction.
	 * 
	 * @throws JDOException
	 */
	void rollback() throws JDOException;

	/**
	 * Close this PersistenceManager so that no further requests may be made on
	 * it.
	 */
	void close() throws JDOException;

	/**
	 * The ObjectId returned by this method represents the JDO identity of the
	 * instance.
	 * 
	 * @param object -
	 *            the PersistenceCapable instance
	 * @return - the ObjectId of the instance
	 * @throws DAOException
	 */
	Object getObjectById(Object oid) throws JDOException, DAOException;

	/**
	 * Looks up the instance of the given type with the given key.
	 * 
	 * @param clazz
	 * @param oid
	 * @return the corresponding persistent instance
	 * @throws DAOException
	 */
	Object getObjectById(Class clazz, Object oid) throws JDOException,
			DAOException;

	/**
	 * This method locates a persistent instance in the cache of instances
	 * managed by this PersistenceManager.
	 * 
	 * @param object
	 * @param validate
	 * @return the PersistenceCapable instance with the specified ObjectId
	 * @throws JDOException
	 * @throws DAOException
	 */
	Object getObjectById(Object oid, boolean validate) throws JDOException,
			DAOException;

	/**
	 * The ObjectId returned by this method represents the JDO identity of the
	 * instance. The ObjectId is a copy (clone) of the internal state of the
	 * instance, and changing it does not affect the JDO identity of the
	 * instance.
	 * 
	 * @param object
	 * @return the ObjectId of the instance
	 * @throws JDOException
	 * @throws DAOException
	 */
	Object getObjectId(Object pc) throws JDOException, DAOException;

	/**
	 * Return the Class that implements the JDO Identity for the specified
	 * PersistenceCapable class.
	 * 
	 * @param clazz -
	 *            cls - the PersistenceCapable Class
	 * @return the Class of the ObjectId of the parameter
	 * @throws JDOException
	 * @throws DAOException
	 */
	Class getObjectIdClass(Class clazz) throws JDOException, DAOException;

	/**
	 * Return the objects with the given oids.
	 * 
	 * @param oids
	 * @return the objects that were looked up, in the same order as the oids
	 *         parameter.
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection getObjectsById(Collection oids) throws JDOException,
			DAOException;

	/**
	 * Return the objects with the given oids.
	 * 
	 * @param oids
	 * @param validate
	 *            validate - if true, the existance of the objects in the
	 *            datastore will be validated.
	 * @return the objects that were looked up, in the same order as the oids
	 *         parameter.
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection getObjectsById(Collection oids, boolean validate)
			throws JDOException, DAOException;

	/**
	 * Return the objects with the given oids. This method is equivalent to
	 * calling getObjectsById(Object[],boolean) with the validate flag true.
	 * 
	 * @param oids -
	 *            the oids of the objects to return
	 * @return the objects that were looked up, in the same order as the oids
	 *         parameter.
	 */
	Object[] getObjectsById(Object[] oids) throws JDOException, DAOException;

	/**
	 * Return the objects with the given oids.
	 * 
	 * @param oids -
	 *            the oids of the objects to return
	 * @param validate -
	 *            validate - if true, the existance of the objects in the
	 *            datastore will be validated.
	 * @return the objects that were looked up, in the same order as the oids
	 *         parameter.
	 */
	Object[] getObjectsById(Object[] oids, boolean validate)
			throws JDOException, DAOException;

	/**
	 * @param clazz
	 * @return
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection query(Class clazz) throws JDOException, DAOException;

	/**
	 * Query with the Class of the candidate instances and filter
	 * 
	 * @param clazz
	 * @return the filtered Collection
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection query(Class clazz, String filter) throws JDOException,
			DAOException;

	/**
	 * Query with the Class of the candidate instances and filter
	 * 
	 * @param clazz
	 * @return the filtered Collection
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection query(Class clazz, String filter, String ordering)
			throws JDOException, DAOException;

	/**
	 * Query with the Class of the candidate instances and filter
	 * 
	 * @param clazz
	 * @return the filtered Collection
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection queryWithMap(Class clazz, String filter, String parameters,
			Map values) throws JDOException, DAOException;

	/**
	 * Query with the Class of the candidate instances and filter
	 * 
	 * @param clazz
	 * @return the filtered Collection
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection queryWithMap(Class clazz, String filter, String ordering,
			String parameters, Map values) throws JDOException, DAOException;

	/**
	 * Query with the Class of the candidate instances and filter
	 * 
	 * @param clazz
	 * @return the filtered Collection
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection queryWithMap(Class clazz, String filter, String parameters,
			Object[] values) throws JDOException, DAOException;

	/**
	 * Query with the Class of the candidate instances and filter
	 * 
	 * @param clazz
	 * @return the filtered Collection
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection queryWithMap(Class clazz, String filter, String ordering,
			String parameters, Object[] values) throws JDOException,
			DAOException;

	/**
	 * Query with the Class of the candidate instances, candidate Collection.
	 * 
	 * @param clazz
	 * @return the filtered Collection.
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection query(Class clazz, Collection coll) throws JDOException,
			DAOException;

	/**
	 * Query with the Class of the candidate instances, candidate Collection,
	 * and filter.
	 * 
	 * @param clazz
	 * @return the filtered Collection.
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection query(Class clazz, Collection coll, String filter)
			throws JDOException, DAOException;

	/**
	 * Query using elements from another Query.
	 * 
	 * @param clazz
	 * @return the filtered Collection.
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection query(Object object) throws JDOException, DAOException;

	/**
	 * Query instance using the specified String as the single-string
	 * representation of the query.
	 * 
	 * @param clazz
	 * @return the filtered Collection.
	 * @throws JDOException
	 * @throws DAOException
	 */
	Collection query(String queryStr) throws JDOException, DAOException;

	/**
	 * Mark an instance as no longer needed in the cache
	 * 
	 * @param pc -
	 *            the instance to evict from the cache.
	 */
	void evict(Object object) throws JDOException, DAOException;

	/**
	 * Mark all persistent-nontransactional instances as no longer needed in the
	 * cache.
	 * 
	 * @throws JDOException
	 * @throws DAOException
	 */
	void evictAll() throws JDOException, DAOException;

	/**
	 * Mark a Collection of instances as no longer needed in the cache.
	 * 
	 * @param pcs
	 * @throws JDOException
	 * @throws DAOException
	 */
	void evictAll(Collection coll) throws JDOException, DAOException;

	/**
	 * Mark an array of instances as no longer needed in the cache.
	 * 
	 * @param pcs
	 * @throws JDOException
	 * @throws DAOException
	 */
	void evictAll(Object[] objects) throws JDOException, DAOException;

	/**
	 * Flushes all dirty, new, and deleted instances to the data store. It has
	 * no effect if a transaction is not active.
	 * 
	 * @throws JDOException
	 * @throws DAOException
	 */
	void flush() throws JDOException, DAOException;

	/**
	 * Make the transient instance persistent in this PersistenceManager.
	 * 
	 * @param object -
	 *            a transient instance of a Class that implements
	 *            PersistenceCapable
	 * @throws JDOException
	 * @throws DAOException
	 */
	void makePersistent(Object object) throws JDOException, DAOException;

	/**
	 * Make a Collection of instances persistent.
	 * 
	 * @param coll -
	 *            a Collection of transient instances
	 */
	void makePersistentAll(Collection coll) throws JDOException, DAOException;

	/**
	 * Make an array of instances persistent.
	 * 
	 * @param pcs
	 */
	void makePersistentAll(Object[] objects) throws JDOException, DAOException;

	/**
	 * Delete the persistent instance from the data store.
	 * 
	 * @param pc
	 * @throws JDOException
	 * @throws DAOException
	 */
	void deletePersistent(Object object) throws JDOException, DAOException;

	/**
	 * Delete a Collection of instances from the data store.
	 * 
	 * @param pcs
	 * @throws JDOException
	 * @throws DAOException
	 */
	void deletePersistentAll(Collection coll) throws JDOException, DAOException;

	/**
	 * Delete an array of instances from the data store.
	 * 
	 * @param pcs
	 */
	void deletePersistentAll(Object[] objects) throws JDOException,
			DAOException;

}