/**
 * @(#)EhCacheServiceImpl.java
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

import java.io.IOException;
import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.teaframework.exception.CacheServiceException;

/**
 * <p>
 * Support for ehcache.
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */

public class EhCacheServiceImpl implements CacheService {

    private Cache cache;

    /**
     * Creates a new pluggable cache based on a cache name.
     * 
     * @param cache The underlying EhCache instance to use.
     */
    public EhCacheServiceImpl(Cache cache) {
        this.cache = cache;
    }

    /**
     * @see org.teaframework.services.cache.CacheService#get(java.lang.Object)
     */
    public Object get(Object key) throws CacheServiceException {
        try {
            if (key == null) {
                return null;
            } else {
                Element element = cache.get((Serializable) key);
                if (element == null) {
                    return null;
                } else {
                    return element.getValue();
                }
            }
        } catch (CacheException e) {
            throw new CacheServiceException(e);
        }
    }

    /**
     * @see org.teaframework.services.cache.CacheService#put(java.lang.Object,
     *      java.lang.Object)
     */
    public void put(Object key, Object value) throws CacheServiceException {
        try {
            Element element = new Element((Serializable) key,
                    (Serializable) value);
            cache.put(element);
        } catch (IllegalArgumentException e) {
            throw new CacheServiceException(e);
        } catch (IllegalStateException e) {
            throw new CacheServiceException(e);
        }

    }

    /**
     * @see org.teaframework.services.cache.CacheService#remove(java.lang.Object)
     */
    public void remove(Object key) throws CacheServiceException {
        try {
            cache.remove((Serializable) key);
        } catch (ClassCastException e) {
            throw new CacheServiceException(e);
        } catch (IllegalStateException e) {
            throw new CacheServiceException(e);
        }

    }

    /**
     * @see org.teaframework.services.cache.CacheService#clear()
     */
    public void clear() throws CacheServiceException {
        try {
            cache.removeAll();
        } catch (IllegalStateException e) {
            throw new CacheServiceException(e);
        } catch (IOException e) {
            throw new CacheServiceException(e);
        }

    }

}