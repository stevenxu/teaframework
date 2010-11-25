/**
 * @(#)PicoTilesRequestProcessor.java
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

package org.teaframework.services.useraccess.struts;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.TilesRequestProcessor;

/**
 * Uses Pico to produce Actions and inject dependencies into them.  Use this class if
 * you are using the Tiles library.  If not, you can use the {@link org.nanocontainer.nanowar.sample.struts.PicoRequestProcessor}
 * instead.
 *
 * @author Stephen Molitor
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:45:44 $
 * @version Revision: 1.0
 */
public class PicoTilesRequestProcessor extends TilesRequestProcessor {
	
	private final ActionFactory actionFactory = new ActionFactory();

    /**
     * Creates or retrieves the action instance.  The action is retrieved from the actions
     * Pico container, using the mapping path as the component key.  If no such action exists,
     * a new one will be instantiated and placed in the actions container, thus injecting
     * its dependencies.
     *
     * @param request  the HTTP request object.
     * @param response the HTTP response object.
     * @param mapping  the action mapping.
     * @return the action instance.
     */
    protected Action processActionCreate(HttpServletRequest request,
                                         HttpServletResponse response,
                                         ActionMapping mapping) throws IOException {
        return actionFactory.processAction(request, mapping, this.servlet);
    }

}
