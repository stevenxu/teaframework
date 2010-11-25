/**
 * @(#)CacheServiceManagerImpl.java
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
import java.io.InputStream;
import java.util.Properties;

import org.teaframework.exception.CacheServiceException;
import org.teaframework.util.resource.ResourceHelper;

/**
 * <p>
 * CachingServiceManager is medator of pluggable service provider.
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */

public class CacheServiceManagerImpl implements CacheServiceManager {

    public CacheServiceManagerImpl() {
    }

    /**
     * @see org.teaframework.services.cache.CacheServiceManager#getCacheService()
     */
    public CacheService getCacheService(String regionName, Properties prop)
            throws CacheServiceException {
        CacheServiceProvider cacheServiceProvider = null;
        Properties props = null;
        try {
            InputStream stream = ResourceHelper
                    .getResourceAsStream(CacheConstant.CACHE_PROPERTIES);

            if (stream != null) {
                props = new Properties();
                props.load(stream);
                stream.close();
            }
        } catch (IOException e) {
            throw new CacheServiceException(
                    "Load Cache configuration IO exception " + e);
        } catch (SecurityException e) {
            throw new CacheServiceException("Cache access security exception "
                    + e);
        }

        String providerClass = props.getProperty(CacheConstant.CACHE_PROVIDER);
        if (providerClass != null && !providerClass.equals("")) {
            cacheServiceProvider = getCacheServiceProvider(providerClass);
        } else {
            cacheServiceProvider = new HashMapCacheServiceProvider();
        }
        return cacheServiceProvider.buildCacheService(regionName, prop);

    }

    /**
     * Load cache service provider.
     * 
     * @param providerClass
     * @return CacheServiceProvider
     * @throws CacheServiceException
     */
    private CacheServiceProvider getCacheServiceProvider(String providerClass)
            throws CacheServiceException {
        try {
            return (CacheServiceProvider) Class.forName(providerClass)
                    .newInstance();
        } catch (Exception e) {
            throw new CacheServiceException("Getting cahce service provider ",
                    e);
        }
    }

}