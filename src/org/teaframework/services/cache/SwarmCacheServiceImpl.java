/**
 * @(#)SwarmCacheServiceImpl.java
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

import java.io.Serializable;

import net.sf.swarmcache.ObjectCache;

import org.teaframework.exception.CacheServiceException;

/**
 * <p>
 * Support for SwarmCache.
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @author <a href="mailto:m.bogaert@intrasoft.be">Mathias Bogaert </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */

public class SwarmCacheServiceImpl implements CacheService {

    private ObjectCache cache;

    public SwarmCacheServiceImpl(ObjectCache cache) {
        this.cache = cache;
    }

    /**
     * @see org.teaframework.services.cache.CacheService#get(java.lang.Object)
     */
    public Object get(Object key) throws CacheServiceException {
        if (key instanceof Serializable) {
            return cache.get((Serializable) key);
        } else {
            throw new CacheServiceException("Keys must implement Serializable");
        }
    }

    /**
     * @see org.teaframework.services.cache.CacheService#put(java.lang.Object,
     *      java.lang.Object)
     */
    public void put(Object key, Object value) throws CacheServiceException {
        if (key instanceof Serializable) {
            cache.put((Serializable) key, value);
        } else {
            throw new CacheServiceException("Keys must implement Serializable");
        }
    }

    /**
     * @see org.teaframework.services.cache.CacheService#remove(java.lang.Object)
     */
    public void remove(Object key) throws CacheServiceException {
        if (key instanceof Serializable) {
            cache.clear((Serializable) key);
        } else {
            throw new CacheServiceException("Keys must implement Serializable");
        }
    }

    /**
     * @see org.teaframework.services.cache.CacheService#clear()
     */
    public void clear() throws CacheServiceException {
        cache.clearAll();
    }

}