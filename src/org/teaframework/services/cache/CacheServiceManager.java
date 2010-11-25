/**
 * @(#)CacheServiceManager.java
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
 * This is the main interface in Caching framework. It defines the methods that
 * all the client applications can depend on, to use caching in project. It
 * hides the clients from any 3rd party caching API that is used to implement
 * the caching mechanism.
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @author Srini Penchikala
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:48:52 $
 * @version Revision: 1.0
 */

public interface CacheServiceManager {

    public CacheService getCacheService(String regionName, Properties prop)
            throws CacheServiceException;

}