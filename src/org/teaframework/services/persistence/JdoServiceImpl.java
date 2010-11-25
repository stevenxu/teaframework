/**
 * @(#)JDOServiceImpl.java
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
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.teaframework.exception.DAOException;

/**
 * <p>
 * JDO persistence service wrapper implementation
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class JdoServiceImpl implements JdoService {

    private PersistenceManagerFactory pmFactory;

    private Transaction transaction;

    private PersistenceManager pm;

    /**
     * Default Constructor.
     */
    public JdoServiceImpl() {    	
    }
    
    /**
	 * Set SessionFactory
	 * @param sessionFactory
	 */
	public void setPersistenceManagerFactory(PersistenceManagerFactory pmFactory) {
		this.pmFactory = pmFactory;
	}

    /**
     * @see org.teaframework.services.persistence.JdoService#getPersistenceManager()
     */
    private PersistenceManager getPersistenceManager() throws JDOException {
        if (pm == null) {
            pm = pmFactory.getPersistenceManager();
        }
        return pm;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#openTransaction()
     */
    public void beginTransaction() throws JDOException {
        if (pm == null && transaction == null) {
            pm = getPersistenceManager();
            transaction = pm.currentTransaction();
            transaction.begin();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#commit()
     */
    public void commit() throws JDOException {
        if (pm != null && !pm.isClosed() && transaction != null
                && transaction.isActive()) {
            transaction.commit();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#rollback()
     */
    public void rollback() throws JDOException {
        if (pm != null && !pm.isClosed() && transaction != null
                && transaction.isActive()) {
            transaction.rollback();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#close()
     */
    public void close() throws JDOException {
        if (pm != null && !pm.isClosed()) {
            pm.close();
            pm = null;
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectById(Object)
     */
    public Object getObjectById(Object oid) throws JDOException, DAOException {
        Object obj;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            obj = pm.getObjectById(oid);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return obj;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectById(Class, Object)
     */
    public Object getObjectById(Class clazz, Object oid) throws JDOException,
            DAOException {
        Object obj;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            obj = pm.getObjectById(clazz, oid);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return obj;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectById(Object, boolean)
     */
    public Object getObjectById(Object oid, boolean validate)
            throws JDOException, DAOException {
        Object obj;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            obj = pm.getObjectById(oid, validate);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return obj;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectById(Object)
     */
    public Object getObjectId(Object pc) throws JDOException, DAOException {
        Object obj;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            obj = pm.getObjectById(pc);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return obj;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectById(Class)
     */
    public Class getObjectIdClass(Class clazz) throws JDOException,
            DAOException {
        Class claz;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            claz = pm.getObjectIdClass(clazz);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return claz;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectsById(Collection)
     */
    public Collection getObjectsById(Collection oids) throws JDOException,
            DAOException {
        Collection coll;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            coll = pm.getObjectsById(oids);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return coll;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectById(Object, boolean)
     */
    public Collection getObjectsById(Collection oids, boolean validate)
            throws JDOException, DAOException {
        Collection coll;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            coll = pm.getObjectsById(oids, validate);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return coll;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectsById(Object[])
     */
    public Object[] getObjectsById(Object[] oids) throws JDOException,
            DAOException {
        Object[] objects;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            objects = pm.getObjectsById(oids);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return objects;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#getObjectsById(Object[], boolean)
     */
    public Object[] getObjectsById(Object[] oids, boolean validate)
            throws JDOException, DAOException {
        Object[] objects;
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            objects = pm.getObjectsById(oids, validate);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return objects;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#query(Class)
     */
    public Collection query(Class clazz) throws JDOException, DAOException {
        Collection coll;
        PersistenceManager pm;
        Query query;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            query = pm.newQuery(clazz);
            coll = (Collection) query.execute();
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return coll;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#query(Class, String)
     */
    public Collection query(Class clazz, String filter) throws JDOException,
            DAOException {
        return query(clazz, filter, null);
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#query(Class, String)
     */
    public Collection query(Class clazz, String filter, String ordering)
            throws JDOException, DAOException {
        Collection coll;
        PersistenceManager pm;
        Query query;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            query = pm.newQuery(clazz, filter);
            if (ordering != null) {
                query.setOrdering(ordering);
            }
            coll = (Collection) query.execute();
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return coll;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#queryWithMap(Class, String, String, Map)
     */
    public Collection queryWithMap(Class clazz, String filter,
            String parameters, Map values) throws JDOException, DAOException {
        return queryWithMap(clazz, filter, null, parameters, values);
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#queryWithMap(Class, String, String, String, Map)
     */
    public Collection queryWithMap(Class clazz, String filter, String ordering,
            String parameters, Map values) throws JDOException, DAOException {
        Collection coll;
        PersistenceManager pm;
        Query query;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            query = pm.newQuery(clazz, filter);
            if (ordering != null) {
                query.setOrdering(ordering);
            }
            query.declareParameters(parameters);
            coll = (Collection) query.executeWithMap(values);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return coll;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#queryWithMap(Class, String, String, Object[])
     */
    public Collection queryWithMap(Class clazz, String filter,
            String parameters, Object[] values) throws JDOException,
            DAOException {
        return queryWithMap(clazz, filter, null, parameters, values);
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#queryWithMap(Class, String, String, String, Object[])
     */
    public Collection queryWithMap(Class clazz, String filter, String ordering,
            String parameters, Object[] values) throws JDOException,
            DAOException {
        Collection coll;
        PersistenceManager pm;
        Query query;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            query = pm.newQuery(clazz, filter);
            if (ordering != null) {
                query.setOrdering(ordering);
            }
            query.declareParameters(parameters);
            coll = (Collection) query.executeWithArray(values);
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return coll;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#query(Class, Collection)
     */
    public Collection query(Class clazz, Collection coll) throws JDOException,
            DAOException {
        Collection cln;
        PersistenceManager pm;
        Query query;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            query = pm.newQuery(clazz, coll);
            cln = (Collection) query.execute();
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return cln;

    }

    /**
     * @see org.teaframework.services.persistence.JdoService#query(Class, Collection, String)
     */
    public Collection query(Class clazz, Collection coll, String filter)
            throws JDOException, DAOException {
        Collection cln;
        PersistenceManager pm;
        Query query;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            query = pm.newQuery(clazz, coll, filter);
            cln = (Collection) query.execute();
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return cln;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#query(Object)
     */
    public Collection query(Object object) throws JDOException, DAOException {
        Collection cln;
        PersistenceManager pm;
        Query query;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            query = pm.newQuery(object);
            cln = (Collection) query.execute();
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return cln;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#query(String)
     */
    public Collection query(String queryStr) throws JDOException, DAOException {
        Collection cln;
        PersistenceManager pm;
        Query query;
        try {
            pm = getPersistenceManager();
            beginTransaction();
            query = pm.newQuery(queryStr);
            cln = (Collection) query.execute();
            commit();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
        return cln;
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#evict(Object)
     */
    public void evict(Object object) throws JDOException, DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.evict(object);
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#evictAll()
     */
    public void evictAll() throws JDOException, DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.evictAll();
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#evictAll(Collection)
     */
    public void evictAll(Collection coll) throws JDOException, DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.evictAll(coll);
        } catch (Exception e) {
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#evictAll(Object[])
     */
    public void evictAll(Object[] objects) throws JDOException, DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.evictAll(objects);
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#flush()
     */
    public void flush() throws JDOException, DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.flush();
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#makePersistent(Object)
     */
    public void makePersistent(Object object) throws JDOException, DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.makePersistent(object);
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#makePersistentAll(Collection)
     */
    public void makePersistentAll(Collection coll) throws JDOException,
            DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.makePersistentAll(coll);
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#makePersistentAll(Object[])
     */
    public void makePersistentAll(Object[] objects) throws JDOException,
            DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.makePersistentAll(objects);
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#deletePersistent(Object)
     */
    public void deletePersistent(Object object) throws JDOException,
            DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.deletePersistent(object);
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#deletePersistentAll(Collection)
     */
    public void deletePersistentAll(Collection coll) throws JDOException,
            DAOException {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.deletePersistent(coll);
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }

    /**
     * @see org.teaframework.services.persistence.JdoService#deletePersistentAll(Object[])
     */
    public void deletePersistentAll(Object[] objects) {
        PersistenceManager pm;
        try {
            pm = getPersistenceManager();
            pm.deletePersistent(objects);
        } catch (Exception e) {
            rollback();
            throw new DAOException(e);
        } finally {
            pm = null;
            close();
        }
    }
}