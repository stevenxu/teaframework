/**
 * @(#)InterceptorChain.java
 * 
 * Tea Service-Oriented JavaEE Application Framework
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

package org.teaframework.container;

import java.util.Collection;

/**
 * An ordered collection of independent interceptors.
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version $Revision: 1.8 $ $Date: 2005/01/17 13:29:59 $
 * @version Revision: 1.0
 */

public interface InterceptorChain {

    /**
     * Invoke intercetor stack.
     * 
     * @param interceptorColl
     * @param serviceEntryColl
     * @throws Throwable
     */
    void processInterceptor(Collection interceptorColl,
            ServiceEntry serviceEntry) throws Throwable;

}