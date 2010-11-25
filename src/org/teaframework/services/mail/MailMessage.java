/**
 * @(#)MailMessage.java
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

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.teaframework.exception.MailException;

/**
 * <p>
 * Common interface for mail messages
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:50:11 $
 * @version Revision: 1.0
 */

public interface MailMessage {

    public void setFrom(String from) throws MailException;

    public void setFrom(String from, String personal) throws MailException;

    public void setReplyTo(String replyTo) throws MailException;

    public void setTo(String to) throws MailException;

    public void setTo(List toList) throws MailException;

    public void setCc(String cc) throws MailException;

    public void setCc(List ccList) throws MailException;

    public void setBcc(String bcc) throws MailException;

    public void setBcc(List bccList) throws MailException;

    public void setSentDate(Date sentDate) throws MailException;

    public void setSubject(String subject) throws MailException;

    public void setSubject(String subject, String charset) throws MailException;

    public void setText(String text, boolean html) throws MailException;

    public void setText(String text, String charset, boolean html)
            throws MailException;

    public void setContentLanguage(String[] langs) throws MailException;

    public MimeMessage getMimeMessage();

}