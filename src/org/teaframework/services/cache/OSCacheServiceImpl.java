/**
 * @(#)OSCacheServiceImpl.java
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

import org.teaframework.exception.CacheServiceException;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * <p>
 * Support for OpenSymphony OSCache
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @author <a href="mailto:m.bogaert@intrasoft.be">Mathias Bogaert </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */

public class OSCacheServiceImpl implements CacheService {

    private GeneralCacheAdministrator cache = new GeneralCacheAdministrator();

    private int refreshPeriod;

    private String cron;

    /**
     * Default constructor with given param
     * 
     * @param refreshPeriod
     * @param cron
     */
    public OSCacheServiceImpl(int refreshPeriod, String cron) {
        this.refreshPeriod = refreshPeriod;
        this.cron = cron;
    }

    /**
     * @see org.teaframework.services.cache.CacheService#get(java.lang.Object)
     */
    public Object get(Object key) throws CacheServiceException {
        try {
            return cache.getFromCache(String.valueOf(key), refreshPeriod, cron);
        } catch (NeedsRefreshException e) {
            cache.cancelUpdate(String.valueOf(key));
            return null;
        }
    }

    /**
     * @see org.teaframework.services.cache.CacheService#put(java.lang.Object,
     *      java.lang.Object)
     */
    public void put(Object key, Object value) throws CacheServiceException {
        cache.putInCache(String.valueOf(key), value);
    }

    /**
     * @see org.teaframework.services.cache.CacheService#remove(java.lang.Object)
     */
    public void remove(Object key) throws CacheServiceException {
        cache.flushEntry(String.valueOf(key));
    }

    /**
     * @see org.teaframework.services.cache.CacheService#clear()
     */
    public void clear() throws CacheServiceException {
        cache.flushAll();
    }

    /**
     * Set cache capacity
     * 
     * @param cacheCapacity
     */
    public void setCacheCapacity(int cacheCapacity) {
        cache.setCacheCapacity(cacheCapacity);
    }

}