/**
 * @(#)DAOFactoryImpl.java
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

package org.teaframework.services.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.teaframework.exception.DAOException;

/**
 * <p>
 * Alternatvie DAO Factory implementation with reflection.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/04/27 08:02:53 $
 * @version Revision: 1.0
 */

public class DAOFactoryImpl implements DAOFactory {

    private Map daoCache = Collections.synchronizedMap(new HashMap());

    /**
     * This method instantiates the DAO class.
     * 
     * @param clazz
     * @throws DAOException
     */
    public DAO getDAO(Class clazz) throws DAOException {

        try {
            if (daoCache.containsKey(clazz.getName())) {
                return (DAO) daoCache.get(clazz.getName());
            } else {
                Object obj = Class.forName(clazz.getName()).newInstance();
                daoCache.put(clazz.getName(), obj);
                return (DAO) obj;
            }
        } catch (InstantiationException e) {
            throw new DAOException("DAOFactoryImpl.getDAO(" + clazz.getName()
                    + "):  NamingException while getting DAO type : \n"
                    + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new DAOException("DAOFactoryImpl.getDAO(" + clazz.getName()
                    + "):  NamingException while getting DAO type : \n"
                    + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DAOException("DAOFactoryImpl.getDAO(" + clazz.getName()
                    + "):  NamingException while getting DAO type : \n"
                    + e.getMessage());
        }
    }

}