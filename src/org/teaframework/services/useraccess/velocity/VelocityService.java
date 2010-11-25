/**
 * @(#)VelocityService.java
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
package org.teaframework.services.useraccess.velocity;

import org.apache.velocity.Template;

/**
 * <p>
 * Velocity Service.
 * </p>
 * 
 * @author <a href="mailto:eos.peter@gmail.com">Peter Cheng </a>
 * @version Revision: Date: Sep 1, 2006
 * @version Revision: 1.0
 * 
 */
public interface VelocityService {

    /**
         * Obtain velocity template
         * 
         * @param templateFile
         * @return Template
         * @throws Exception
         */
    Template getTemplate(String templateFile) throws Exception;

    /**
         * Obtain velocity template with encoding.
         * 
         * @param templateFile
         * @param encoding
         * @return Template
         * @throws Exception
         */
    Template getTemplate(String templateFile, String encoding) throws Exception;

}