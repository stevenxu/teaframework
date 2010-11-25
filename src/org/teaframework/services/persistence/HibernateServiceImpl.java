/**
 * @(#)HibernateServiceImpl.java
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
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.teaframework.exception.DAOException;

/**
 * <p>
 * Hibernate persistence service implementation.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.11 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class HibernateServiceImpl implements HibernateService {

	private final Log logger = LogFactory.getLog(getClass());

	private Session session = null;

    private Transaction transaction = null;

	private SessionFactory sessionFactory = null;

	/**
	 * Default constructor.
	 */
	public HibernateServiceImpl() {
	}

	/**
	 * Set SessionFactory
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#openSession()
	 */
	public Session openSession() throws HibernateException {
		if (session == null) {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
		}
		return session;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#closeSession()
	 */
	public void closeSession() throws HibernateException {
		if (session != null) {
			if (transaction != null && !transaction.wasCommitted()
					&& !transaction.wasRolledBack()) {
				transaction.commit();
				transaction = null;
			}
			session.close();
			session = null;
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#commit()
	 */
	public void commit() throws HibernateException {
		if (session != null && transaction != null
				&& !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.commit();
			transaction = session.beginTransaction();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#reset()
	 */
	public void reset() {
		try {
			rollback();
			if (session != null) {
				session.clear();
				session.close();
			}
		} catch (HibernateException ex) {
			// we do nothing here, because this shall be called if something
			// went wrong.
		} finally {
			session = null;
			transaction = null;
		}

	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#rollback()
	 */
	public void rollback() throws HibernateException {
		if (session != null && transaction != null
				&& !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.rollback();
			transaction = session.beginTransaction();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#contains(Object)
	 */
	public boolean contains(Object object) throws HibernateException,
			DAOException {
		boolean isContains = false;
		Session session = null;
		try {
			session = openSession();
			session.contains(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return isContains;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#delete(Object)
	 */
	public void delete(Object object) throws HibernateException, DAOException {
		Session session = null;
		try {
			session = openSession();
			session.delete(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#delete(Object,
	 *      LockMode)
	 */
	public void delete(Object object, LockMode lockMode)
			throws HibernateException, DAOException {
		Session session = null;
		try {
			session = openSession();
			session.lock(object, lockMode);
			session.delete(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new HibernateException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#delete(String)
	 */
	public int delete(String query) throws HibernateException, DAOException {
		int instancesCount = 0;
		Session session = null;
		try {
			session = openSession();
			instancesCount = session.delete(query);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return instancesCount;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#delete(String,
	 *      Object, Type)
	 */
	public int delete(String query, Object value, Type type)
			throws HibernateException, DAOException {
		int instancesCount = 0;
		Session session = null;
		try {
			session = openSession();
			instancesCount = session.delete(query, value, type);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return instancesCount;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#delete(String,
	 *      Object[], Type[])
	 */
	public int delete(String query, Object[] values, Type[] types)
			throws HibernateException, DAOException {
		int instancesCount = 0;
		Session session = null;
		try {
			session = openSession();
			instancesCount = session.delete(query, values, types);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return instancesCount;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#evict(Object)
	 */
	public void evict(Object object) throws HibernateException, DAOException {
		Session session;
		try {
			session = openSession();
			session.evict(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#find(String)
	 */
	public List find(String query) throws HibernateException, DAOException {
		List list = null;
		Session session = null;
		try {
			session = openSession();
			list = session.find(query);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return list;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#find(String,
	 *      Object, Type)
	 */
	public List find(String query, Object value, Type type)
			throws HibernateException, DAOException {
		List list = null;
		Session session;
		try {
			session = openSession();
			list = session.find(query, value, type);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return list;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#find(String,
	 *      Object[], Type[])
	 */
	public List find(String query, Object[] values, Type[] types)
			throws HibernateException, DAOException {
		List list = null;
		Session session = null;
		try {
			session = openSession();
			list = session.find(query, values, types);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return list;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#get(Class,
	 *      Serializable)
	 */
	public Object get(Class clazz, final Serializable id)
			throws HibernateException, DAOException {
		Object object;
		Session session = null;
		try {
			session = openSession();
			object = session.get(clazz, id);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return object;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#get(Class,
	 *      Serializable, LockMode)
	 */
	public Object get(Class clazz, final Serializable id, LockMode lockMode)
			throws HibernateException, DAOException {
		Object object;
		Session session;
		try {
			session = openSession();
			object = session.get(clazz, id, lockMode);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return object;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#iterate(String)
	 */
	public Iterator iterate(String query) throws HibernateException,
			DAOException {
		Iterator iter;
		Session session = null;
		try {
			session = openSession();
			iter = session.iterate(query);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return iter;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#iterate(String,
	 *      Object, Type)
	 */
	public Iterator iterate(String query, Object value, Type type)
			throws HibernateException, DAOException {
		Iterator iter;
		Session session = null;
		try {
			session = openSession();
			iter = session.iterate(query, value, type);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return iter;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#iterate(String,
	 *      Object[], Type[])
	 */
	public Iterator iterate(String query, Object[] values, Type[] types)
			throws HibernateException, DAOException {
		Iterator iter;
		Session session;
		try {
			session = openSession();
			iter = session.iterate(query, values, types);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return iter;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#load(Class,
	 *      Serializable)
	 */
	public Object load(Class clazz, final Serializable id)
			throws HibernateException, DAOException {
		Object object;
		Session session = null;
		try {
			session = openSession();
			object = session.load(clazz, id);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return object;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#load(Class,
	 *      Serializable, LockMode)
	 */
	public Object load(Class clazz, final Serializable id, LockMode lockMode)
			throws HibernateException, DAOException {
		Object object;
		Session session;
		try {
			session = openSession();
			object = session.load(clazz, id, lockMode);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return object;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#load(Object,
	 *      Serializable)
	 */
	public void load(Object object, final Serializable id)
			throws HibernateException, DAOException {
		Session session = null;
		try {
			session = openSession();
			session.load(object, id);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#load(Class,
	 *      Serializable, LockMode)
	 */
	public void lock(Object object, LockMode lockMode)
			throws HibernateException, DAOException {
		Session session;
		try {
			session = openSession();
			session.lock(object, lockMode);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#update(Object)
	 */
	public void update(Object object) throws HibernateException, DAOException {
		Session session = null;
		try {
			session = openSession();
			session.update(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#update(Object,
	 *      Serializable)
	 */
	public void update(Object object, final Serializable id)
			throws HibernateException, DAOException {
		Session session = null;
		try {
			session = openSession();
			session.update(object, id);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#saveOrUpdate(Object)
	 */
	public Object saveOrUpdateCopy(Object object) throws HibernateException,
			DAOException {
		Session session;
		Object obj;
		try {
			session = openSession();
			obj = session.saveOrUpdateCopy(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return obj;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#saveOrUpdateCopy(Object,
	 *      Serializable)
	 */
	public Object saveOrUpdateCopy(Object object, final Serializable id)
			throws HibernateException, DAOException {
		Session session = null;
		Object obj;
		try {
			session = openSession();
			obj = session.saveOrUpdateCopy(object, id);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return obj;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#refresh(Object)
	 */
	public void refresh(Object object) throws HibernateException, DAOException {
		Session session = null;
		try {
			session = openSession();
			session.refresh(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#refresh(Object,
	 *      LockMode)
	 */
	public void refresh(Object object, LockMode lockMode)
			throws HibernateException, DAOException {
		Session session = null;
		try {
			session = openSession();
			session.refresh(object, lockMode);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#save(Object)
	 */
	public Serializable save(Object object) throws HibernateException,
			DAOException {
		Serializable seriaObj;
		Session session = null;
		try {
			session = openSession();
			seriaObj = session.save(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
		return seriaObj;
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#save(Object,
	 *      Serializable)
	 */
	public void save(Object object, final Serializable id)
			throws HibernateException, DAOException {
		Session session;
		try {
			session = openSession();
			session.save(object, id);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {			
			session = null;
			closeSession();
		}
	}

	/**
	 * @see org.teaframework.services.persistence.HibernateService#saveOrUpdate(Object)
	 */
	public void saveOrUpdate(Object object) throws HibernateException,
			DAOException {
		Session session = null;
		try {
			session = openSession();
			session.saveOrUpdate(object);
			commit();
		} catch (Exception e) {
			rollback();
			throw new DAOException(e);
		} finally {
			session = null;
			closeSession();
		}
	}

}