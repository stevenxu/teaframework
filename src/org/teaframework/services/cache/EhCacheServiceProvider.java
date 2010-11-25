/**
 * @(#)EhCacheServiceProvider.java
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

import java.util.Properties;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

import org.teaframework.exception.CacheServiceException;

/**
 * <p>
 * EhCacheService for pluggable cache.
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */

public class EhCacheServiceProvider implements CacheServiceProvider {

    private CacheManager manager;

    /**
     * @see org.teaframework.services.cache.CacheServiceProvider#buildCacheService(java.lang.String,
     *      java.util.Properties)
     */
    public CacheService buildCacheService(String regionName,
            Properties properties) throws CacheServiceException {
        try {
            manager = CacheManager.create();
            Cache cache = manager.getCache(regionName);
            if (cache == null) {
                manager.addCache(regionName);
                cache = manager.getCache(regionName);
            }
            return new EhCacheServiceImpl(cache);
        } catch (CacheException e) {
            throw new CacheServiceException("Build EhCacheService ", e);
        }
    }

}