/**
 * @(#)MailServiceImpl.java
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

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.teaframework.exception.MailException;
import org.teaframework.exception.ServiceLocatorException;
import org.teaframework.services.ejb.ServiceLocator;

/**
 * <p>
 * Mail service implementation.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:50:11 $
 * @version Revision: 1.0
 */

public class MailServiceImpl implements MailService {

    private ServiceLocator serviceLocator;

    public MailServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    /**
     * @see org.teaframework.services.mail.MailService#sendMail(org.apache.commons.mail.Email)
     */
    public void sendMail(Email email) throws MailException {
        try {
            email.send();
        } catch (EmailException e) {
            throw new MailException("Send mail failed " + e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailService#sendMailWithSession(org.apache.commons.mail.Email)
     */
    public void sendMailWithSession(Email email, String mailSessionName)
            throws MailException {
        try {
            Session session = serviceLocator.getMailSession(mailSessionName);
            email.setMailSession(session);
            email.send();
        } catch (ServiceLocatorException e) {
            throw new MailException("Lookup mail session failed ", e);
        } catch (EmailException e) {
            throw new MailException("Send mail failed ", e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailService#sendMail(org.teaframework.services.mail.MailMessage)
     */
    public void sendMail(MailMessage mailMessage) throws MailException {
        try {
            Transport.send(mailMessage.getMimeMessage());
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }
}