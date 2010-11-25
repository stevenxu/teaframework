/**
 * @(#)EJBProxyService.java
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

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;

/**
 * <p>
 * Invokes any EJB without knowing anything more than the EJB's JNDI lookup
 * name.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.8 $ $Date: 2005/05/22 06:49:31 $
 * @version Revision: 1.0
 */

public interface EJBProxyService {

    /**
     * Retrieve remote EJB object.
     * 
     * @param jndiName
     * @return Object
     * @throws EJBException
     */
    Object getRemoteObject(String jndiName) throws EJBException;

    /**
     * Retrieve local EJB object.
     * 
     * @param jndiName
     * @return Object
     * @throws EJBException
     */
    Object getLocalObject(String jndiName) throws EJBException;

    /**
     * Utility method for attempting to find a specific entity bean given it's
     * home interface and primary key.
     * <p>
     * Attempts to call the following methods in order:
     * </p>
     * 
     * <pre>
     * 
     *    home.findByPrimaryKey(int id);
     *    home.findByPrimaryKey(long id);
     *    home.findByPrimaryKey(Integer id);
     *    home.findByPrimaryKey(Long id);
     *    home.findByPrimaryKey(String id);
     *  
     * </pre>
     * 
     * @param home Reference to entity home interface.
     * @param id Value of primary key.
     * @return Reference to <code>EJBObject</code> to be casted to desired
     *         type.
     * @exception java.rmi.RemoteException Rethrown if thrown by finder method.
     * @exception javax.ejb.FinderException Rethrown if thrown by finder method.
     */
    EJBObject findEntity(EJBHome home, String id) throws RemoteException,
            FinderException;

}