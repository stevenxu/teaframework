/**
 * @(#)ServiceLocatorImpl.java
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

package org.teaframework.services.ejb;

import java.net.URL;
import java.rmi.Remote;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import javax.xml.rpc.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.teaframework.exception.ServiceLocatorException;

/**
 * <p>
 * ServiceLocator Implementation.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.14 $ $Date: 2005/05/22 06:49:31 $
 * @version Revision: 1.0
 */

public class ServiceLocatorImpl implements ServiceLocator {

    private InitialContext ic = null;

    private Map cache = null;

    private final Log logger = LogFactory.getLog(getClass());

    private DataSource dataSource = null;

    private Session mailSession = null;

    public ServiceLocatorImpl() throws ServiceLocatorException {
        try {
            ic = new InitialContext();
            cache = Collections.synchronizedMap(new HashMap());
        } catch (NamingException ne) {
            logger.error("ServiceService NamingException " + ne.getMessage());
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            logger.error("ServiceLocator Exception " + e.getMessage());
            throw new ServiceLocatorException(e);
        }
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getBoolean(java.lang.String)
     */
    public boolean getBoolean(String envName) throws ServiceLocatorException {
        Boolean bool = null;
        try {
            bool = (Boolean) ic.lookup(envName);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return bool.booleanValue();
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getLocalHome(java.lang.String)
     */
    public EJBLocalHome getLocalHome(String jndiHomeName)
            throws ServiceLocatorException {
        EJBLocalHome home = null;
        try {
            if (cache.containsKey(jndiHomeName)) {
                home = (EJBLocalHome) cache.get(jndiHomeName);
            } else {
                home = (EJBLocalHome) ic.lookup(jndiHomeName);
                cache.put(jndiHomeName, home);
            }
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return home;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getRemoteHome(java.lang.String,
     *      java.lang.ClassLoadProxy)
     */
    public EJBHome getRemoteHome(String jndiHomeName, Class className)
            throws ServiceLocatorException {
        EJBHome home = null;
        try {
            if (cache.containsKey(jndiHomeName)) {
                home = (EJBHome) cache.get(jndiHomeName);
            } else {
                Object objref = ic.lookup(jndiHomeName);
                Object obj = PortableRemoteObject.narrow(objref, className);
                home = (EJBHome) obj;
                cache.put(jndiHomeName, home);
            }
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return home;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getQueue(java.lang.String)
     */
    public Queue getQueue(String queueName) throws ServiceLocatorException {
        Queue queue = null;
        try {
            if (cache.containsKey(queueName)) {
                queue = (Queue) cache.get(queueName);
            } else {
                queue = (Queue) ic.lookup(queueName);
                cache.put(queueName, queue);
            }
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return queue;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getQueueConnectionFactory(java.lang.String)
     */
    public QueueConnectionFactory getQueueConnectionFactory(
            String qConnFactoryName) throws ServiceLocatorException {
        QueueConnectionFactory factory = null;
        try {
            if (cache.containsKey(qConnFactoryName)) {
                factory = (QueueConnectionFactory) cache.get(qConnFactoryName);
            } else {
                factory = (QueueConnectionFactory) ic.lookup(qConnFactoryName);
                cache.put(qConnFactoryName, factory);
            }
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return factory;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getTopic(java.lang.String)
     */
    public Topic getTopic(String topicName) throws ServiceLocatorException {
        Topic topic = null;
        try {
            if (cache.containsKey(topicName)) {
                topic = (Topic) cache.get(topicName);
            } else {
                topic = (Topic) ic.lookup(topicName);
                cache.put(topicName, topic);
            }
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return topic;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getTopicConnectionFactory(java.lang.String)
     */
    public TopicConnectionFactory getTopicConnectionFactory(
            String topicConnFactoryName) throws ServiceLocatorException {
        TopicConnectionFactory factory = null;
        try {
            if (cache.containsKey(topicConnFactoryName)) {
                factory = (TopicConnectionFactory) cache
                        .get(topicConnFactoryName);
            } else {
                factory = (TopicConnectionFactory) ic
                        .lookup(topicConnFactoryName);
                cache.put(topicConnFactoryName, factory);
            }
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return factory;
    }

    /**
     * @return the factory for the factory to get queue connections from
     */
    public ConnectionFactory getJMSConnectionFactory(String ConnFactoryName)
            throws ServiceLocatorException {
        ConnectionFactory factory = (ConnectionFactory) cache
                .get(ConnFactoryName);
        if (factory == null) {
            try {
                factory = (ConnectionFactory) ic.lookup(ConnFactoryName);
                cache.put(ConnFactoryName, factory);
            } catch (Exception e) {
                throw new ServiceLocatorException(e);
            }
        }
        return factory;
    }

    /**
     * @return the Queue Destination to send messages to
     */
    public Destination getJMSDestination(String destName)
            throws ServiceLocatorException {
        Destination dest = (Destination) cache.get(destName);
        if (dest == null) {
            try {
                dest = (Destination) ic.lookup(destName);
                cache.put(destName, dest);
            } catch (Exception e) {
                throw new ServiceLocatorException(e);
            }
        }
        return dest;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getDataSource(String)
     */
    public DataSource getDataSource(String dataSourceName)
            throws ServiceLocatorException {

        try {
            if (cache.containsKey(dataSourceName)) {
                dataSource = (DataSource) cache.get(dataSourceName);
            } else {
                dataSource = (DataSource) ic.lookup(dataSourceName);
                cache.put(dataSourceName, dataSource);
            }
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return dataSource;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getDataSource(String)
     */
    public Session getMailSession(String mailSessionName)
            throws ServiceLocatorException {

        try {
            if (cache.containsKey(mailSessionName)) {
                mailSession = (Session) cache.get(mailSessionName);
            } else {
                mailSession = (Session) ic.lookup(mailSessionName);
                cache.put(mailSessionName, mailSession);
            }
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return mailSession;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getString(String)
     */
    public String getString(String envName) throws ServiceLocatorException {
        String envEntry = null;
        try {
            envEntry = (String) ic.lookup(envName);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return envEntry;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getUserTransaction(String)
     */
    public UserTransaction getUserTransaction(String utName)
            throws ServiceLocatorException {
        try {
            return (UserTransaction) ic.lookup(utName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getUrl(String)
     */
    public URL getUrl(String envName) throws ServiceLocatorException {
        URL url = null;
        try {
            url = (URL) ic.lookup(envName);
        } catch (NamingException ne) {
            throw new ServiceLocatorException(ne);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
        return url;
    }

    /**
     * @see org.teaframework.services.ejb.ServiceLocator#getServicePort(String, Class)
     */
    public Remote getServicePort(String jndiHomeName, Class className)
            throws ServiceLocatorException {
        Remote servicePort = (Remote) cache.get(jndiHomeName);
        if (servicePort == null) {
            try {
                Service service = (Service) ic.lookup(jndiHomeName);
                servicePort = service.getPort(className);
                cache.put(jndiHomeName, servicePort);
            } catch (Exception e) {
                throw new ServiceLocatorException(e);
            }
        }
        return servicePort;
    }

}