/**
 * @(#)EJBProxyServiceImpl.java
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;

import org.teaframework.exception.ServiceLocatorException;

import com.opensymphony.util.EJBUtils;

/**
 * <p>
 * EJBProxyService Implementation.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.8 $ $Date: 2005/05/22 06:49:31 $
 * @version Revision: 1.0
 */

public class EJBProxyServiceImpl implements EJBProxyService {

    private ServiceLocator serviceLocator;

    public EJBProxyServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    /**
     * @see org.teaframework.services.ejb.EJBProxyService#getRemoteObject(java.lang.String)
     */
    public Object getRemoteObject(String jndiName) throws EJBException {
        try {
            EJBHome ejbHome = serviceLocator.getRemoteHome(jndiName,
                    EJBHome.class);
            //get the method of create
            Method method = ejbHome.getClass().getDeclaredMethod("create",
                    new Class[0]);
            Object obj = method.invoke(ejbHome, new Object[0]);
            return obj;
        } catch (ServiceLocatorException e) {
            throw new EJBException(e);
        } catch (SecurityException e) {
            throw new EJBException(e);
        } catch (IllegalArgumentException e) {
            throw new EJBException(e);
        } catch (NoSuchMethodException e) {
            throw new EJBException(e);
        } catch (IllegalAccessException e) {
            throw new EJBException(e);
        } catch (InvocationTargetException e) {
            throw new EJBException(e);
        }
    }

    /**
     * @see org.teaframework.services.ejb.EJBProxyService#getLocalObject(java.lang.String)
     */
    public Object getLocalObject(String jndiName) throws EJBException {
        Object obj;
        try {
            EJBLocalHome ejbLocalHome = serviceLocator.getLocalHome(jndiName);
            Method method = ejbLocalHome.getClass().getDeclaredMethod("create",
                    new Class[0]);
            obj = method.invoke(ejbLocalHome, new Object[0]);
            return obj;
        } catch (ServiceLocatorException e) {
            throw new EJBException(e);
        } catch (SecurityException e) {
            throw new EJBException(e);
        } catch (IllegalArgumentException e) {
            throw new EJBException(e);
        } catch (NoSuchMethodException e) {
            throw new EJBException(e);
        } catch (IllegalAccessException e) {
            throw new EJBException(e);
        } catch (InvocationTargetException e) {
            throw new EJBException(e);
        }
    }

    /**
     * @see org.teaframework.services.ejb.EJBProxyService#findEntity(
     *      javax.ejb.EJBHome, java.lang.String)
     */
    public EJBObject findEntity(EJBHome home, String id)
            throws RemoteException, FinderException {
        return EJBUtils.findEntity(home, id);
    }
}