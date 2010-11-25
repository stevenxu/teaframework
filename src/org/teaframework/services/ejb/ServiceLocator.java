/**
 * @(#)ServiceLocator.java
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

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.mail.Session;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.teaframework.exception.ServiceLocatorException;

/**
 * <p>
 * This class is an implementation of the Service Locator pattern. It is used to
 * looukup resources such as EJBHomes, JMS Destinations, etc. This
 * implementation uses the "singleton instance" strategy and also the "caching"
 * strategy.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.10 $ $Date: 2005/05/22 06:49:31 $
 * @version Revision: 1.0
 */

public interface ServiceLocator {

    /**
     * will get the ejb Local home factory. If this ejb home factory has already
     * been clients need to cast to the type of EJBHome they desire
     * 
     * @return the EJB Home corresponding to the homeName
     */
    public EJBLocalHome getLocalHome(String jndiHomeName)
            throws ServiceLocatorException;

    /**
     * will get the ejb Remote home factory. If this ejb home factory has
     * already been clients need to cast to the type of EJBHome they desire
     * 
     * @return the EJB Home corresponding to the homeName
     */
    public EJBHome getRemoteHome(String jndiHomeName, Class className)
            throws ServiceLocatorException;

    /**
     * @return the factory for the factory to get queue connections from
     */
    public QueueConnectionFactory getQueueConnectionFactory(
            String qConnFactoryName) throws ServiceLocatorException;

    /**
     * @return the Queue Destination to send messages to
     */
    public Queue getQueue(String queueName) throws ServiceLocatorException;

    /**
     * This method helps in obtaining the topic factory
     * 
     * @return the factory for the factory to get topic connections from
     */
    public TopicConnectionFactory getTopicConnectionFactory(
            String topicConnFactoryName) throws ServiceLocatorException;

    /**
     * This method helps in obtaining the unified factory.
     * 
     * @return the unified factory to get connections from
     */
    public ConnectionFactory getJMSConnectionFactory(String ConnFactoryName)
            throws ServiceLocatorException;

    /**
     * This method helps in obtianing hte unified destination.
     * 
     * @return the Destination to send messages to
     */
    public Destination getJMSDestination(String destName)
            throws ServiceLocatorException;

    /**
     * This method obtains the topc itself for a caller
     * 
     * @return the Topic Destination to send messages to
     */
    public Topic getTopic(String topicName) throws ServiceLocatorException;

    /**
     * This method obtains the datasource itself for a caller
     * 
     * @return the DataSource corresponding to the name parameter
     */
    public DataSource getDataSource(String dataSourceName)
            throws ServiceLocatorException;

    /**
     * This method obtains the mail seesion itself for a caller
     * 
     * @return the Session corresponding to the name parameter
     */
    public Session getMailSession(String mailSessionName)
            throws ServiceLocatorException;

    /**
     * This method obtains the UserTransaction itself for a caller
     * 
     * @return the UserTransaction corresponding to the name parameter
     */
    public UserTransaction getUserTransaction(String utName)
            throws ServiceLocatorException;

    /**
     * @return the URL value corresponding to the env entry name.
     */
    public URL getUrl(String envName) throws ServiceLocatorException;

    /**
     * @return the boolean value corresponding to the env entry such as
     *         SEND_CONFIRMATION_MAIL property.
     */
    public boolean getBoolean(String envName) throws ServiceLocatorException;

    /**
     * @return the String value corresponding to the env entry name.
     */
    public String getString(String envName) throws ServiceLocatorException;

    /**
     * Service class acts as a factory of the Dynamic proxy for the target
     * service endpoint.
     * 
     * @see java.xml.rpc.Service.java
     * @return the Service instance
     */
    public Remote getServicePort(String jndiHomeName, Class className)
            throws ServiceLocatorException;

}