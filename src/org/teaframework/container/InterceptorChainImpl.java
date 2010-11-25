/**
 * @(#)InterceptorChainImpl.java
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

package org.teaframework.container;

import java.util.Collection;
import java.util.Iterator;

/**
 * InterceptorChain implementation.
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version $Revision: 1.11 $ $Date: 2005/05/22 06:46:49 $
 * @version Revision: 1.0
 */

public class InterceptorChainImpl implements InterceptorChain {

    /**
     * Invoke intercetor stack.
     * 
     * @param interceptorColl
     * @param serviceModel
     * @throws Throwable
     */
    public void processInterceptor(Collection interceptorColl,
            ServiceEntry serviceModel) throws Throwable {
        Iterator interceptorIter = interceptorColl.iterator();
        ServiceInterceptor serviceInterceptor = null;
        while (interceptorIter.hasNext()) {
            serviceInterceptor = (ServiceInterceptor) interceptorIter.next();
            serviceInterceptor.invoke(serviceModel);
        }
    }
}