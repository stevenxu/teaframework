/**
 * @(#)InterceptorManagerImpl.java
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.teaframework.schema.service.ServiceEntry;
import org.teaframework.util.resource.ResourceHelper;

/**
 * InterceptorManager imlementation.
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.9 $ $Date: 2005/05/22 06:46:49 $
 * @version Revision: 1.0
 */

public class InterceptorManagerImpl implements InterceptorManager {

    private InterceptorChain interceptorChain = new InterceptorChainImpl();

    private Collection interceptorList = new ArrayList();

    /**
     * Process Interceptor.
     * 
     * @param interceptorColl
     * @param serviceEntryColl
     * @throws Throwable
     */
    public void processInterceptor(Collection interceptorColl,
            Collection serviceEntryColl) throws Throwable {
        instantiationInteceptor(interceptorColl);
        Iterator serviceEntryIter = serviceEntryColl.iterator();
        ServiceEntry serviceModel = null;
        while (serviceEntryIter.hasNext()) {
            serviceModel = (ServiceEntry) serviceEntryIter.next();
            interceptorChain.processInterceptor(interceptorList, serviceModel);
        }
    }

    /**
     * Init interceptor instance.
     * 
     * @param coll Interceptor collection
     * @throws Throwable
     */
    private void instantiationInteceptor(Collection coll) throws Throwable {
        Iterator iter = coll.iterator();
        while (iter.hasNext()) {
            interceptorList.add(ResourceHelper
                    .instantiate((String) iter.next()));
        }
    }

}