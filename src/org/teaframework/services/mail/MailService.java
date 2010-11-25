/**
 * @(#)MailService.java
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

package org.teaframework.services.mail;

import org.apache.commons.mail.Email;
import org.teaframework.exception.MailException;

/**
 * <p>
 * Mail service, Wrapper service for commons-email.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:50:11 $
 * @version Revision: 1.0
 */

public interface MailService {
    
    /**
     * Send email.
     * 
     * @param mailMessage
     * @throws MailException
     */
    public void sendMail(MailMessage mailMessage) throws MailException;

    /**
     * Send email with commons-email
     * 
     * @param email
     * @throws MailException
     */
    public void sendMail(Email email) throws MailException;

    /**
     * Send email through appserver mail session.
     * 
     * @param email
     * @param mailSessionName
     * @throws MailException
     */
    public void sendMailWithSession(Email email, String mailSessionName)
            throws MailException;

}