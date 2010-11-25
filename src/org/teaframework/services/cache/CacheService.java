/**
 * @(#)CacheService.java
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

/**
 * <p>
 * A cache, being a mechanism for efficient temporary storage of objects for the
 * purpose of improving the overall performance of an application system
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */
public interface CacheService {

    /**
     * The get method will return, from the cache, the object associated with
     * the argument "key".
     * 
     * @param key whose associated value is to be returned
     * @return the value to which this cache maps the specified key, or null if
     *         the cache contains no mapping for this key.
     * @throws CacheException
     */
    public Object get(Object key) throws CacheServiceException;

    /**
     * The put method adds the object "value" to the cache identified by the
     * object "key".
     * 
     * @param key
     * @param value
     * @throws CacheServiceException
     */
    public void put(Object key, Object value) throws CacheServiceException;

    /**
     * The remove method will delete the object from the cache including the
     * key, the associated value and the associated CacheStatistics object.
     */
    public void remove(Object key) throws CacheServiceException;

    /**
     * The clear method will remove all objects from the cache including the
     * key, the associated value and the associated CacheStatistics object.
     */
    public void clear() throws CacheServiceException;

}