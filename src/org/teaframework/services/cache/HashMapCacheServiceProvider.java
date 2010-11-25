/**
 * @(#)HashMapCacheServiceProvider.java
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

import org.teaframework.exception.CacheServiceException;

/**
 * <p>
 * HashMapCacheService for pluggable cache.
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version $Revision: 1.2 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class HashMapCacheServiceProvider implements CacheServiceProvider {

    /**
     * @see org.teaframework.services.cache.CacheServiceProvider#buildCacheService(java.lang.String)
     */
    public CacheService buildCacheService(String regionName,
            Properties properties) throws CacheServiceException {
        return new HashMapCacheServiceImpl();
    }

}