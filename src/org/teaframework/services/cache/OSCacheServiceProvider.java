/**
 * @(#)OSCacheServiceProvider.java
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
import org.teaframework.util.resource.PropertiesHelper;
import org.teaframework.util.resource.StringHelper;

import com.opensymphony.oscache.base.CacheEntry;
import com.opensymphony.oscache.base.Config;

/**
 * <p>
 * OSCacheService for pluggable cache.
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @author <a href="mailto:m.bogaert@intrasoft.be">Mathias Bogaert </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */

public class OSCacheServiceProvider implements CacheServiceProvider {

    /**
     * The OSCache refresh period property suffix.
     */
    public static final String OSCACHE_REFRESH_PERIOD = "refresh.period";

    /**
     * The OSCache CRON expression property suffix.
     */
    public static final String OSCACHE_CRON = "cron";

    /**
     * The OSCache cache capacity property suffix.
     */
    public static final String OSCACHE_CAPACITY = "capacity";

    private static final Properties OSCACHE_PROPERTIES = new Config()
            .getProperties();

    /**
     * @see org.teaframework.services.cache.CacheServiceProvider#buildCache(java.lang.String,
     *      java.util.Properties)
     */
    public CacheService buildCacheService(String regionName,
            Properties properties) throws CacheServiceException {
        int refreshPeriod = PropertiesHelper.getInt(StringHelper.qualify(
                regionName, OSCACHE_REFRESH_PERIOD), OSCACHE_PROPERTIES,
                CacheEntry.INDEFINITE_EXPIRY);
        String cron = OSCACHE_PROPERTIES.getProperty(StringHelper.qualify(
                regionName, OSCACHE_CRON));

        // construct the cache
        OSCacheServiceImpl cache = new OSCacheServiceImpl(refreshPeriod, cron);

        Integer capacity = PropertiesHelper.getInteger(StringHelper.qualify(
                regionName, OSCACHE_CAPACITY), OSCACHE_PROPERTIES);
        if (capacity != null)
            cache.setCacheCapacity(capacity.intValue());

        return cache;
    }

}