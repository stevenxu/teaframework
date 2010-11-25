/**
 * @(#)JCSCacheServiceImpl.java
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

package org.teaframework.services.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.teaframework.exception.CacheServiceException;

/**
 * <p>
 * Support for Apache Turbine's JCS
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @author <a href="mailto:m.bogaert@intrasoft.be">Mathias Bogaert </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */

public class JCSCacheServiceImpl implements CacheService {

    private static final Log log = LogFactory.getLog(JCSCacheServiceImpl.class);

    private JCS cache;

    /**
     * Constructor with given param.
     */
    public JCSCacheServiceImpl(String regionName) throws CacheServiceException {
        try {
            cache = JCS.getInstance(regionName);
        } catch (CacheException e) {
            log.error("could not create JCS region", e);
            throw new CacheServiceException(e);
        }
    }

    /**
     * @see org.teaframework.services.cache.CacheService#get(java.lang.Object)
     */
    public Object get(Object key) throws CacheServiceException {
        return cache.get(key);
    }

    /**
     * @see org.teaframework.services.cache.CacheService#put(java.lang.Object,
     *      java.lang.Object)
     */
    public void put(Object key, Object value) throws CacheServiceException {
        try {
            cache.put(key, value);
        } catch (CacheException e) {
            log.error("could not add to JCS region", e);
            throw new CacheServiceException(e);
        }
    }

    /**
     * @see org.teaframework.services.cache.CacheService#remove(java.lang.Object)
     */
    public void remove(Object key) throws CacheServiceException {
        try {
            cache.remove(key);
        } catch (CacheException e) {
            log.error("could not remove from JCS region", e);
            throw new CacheServiceException(e);
        }
    }

    /**
     * @see org.teaframework.services.cache.CacheService#clear()
     */
    public void clear() throws CacheServiceException {
        try {
            cache.remove();
        } catch (CacheException e) {
            log.error("could not remove from JCS region", e);
            throw new CacheServiceException(e);
        }
    }
}