/**
 * @(#)MailMessageImpl.java
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

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.teaframework.exception.MailException;

/**
 * <p>
 * Implementation of the MailMessage interface.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2005/05/22 06:50:11 $
 * @version Revision: 1.0
 */

public class MailMessageImpl implements MailMessage {

    private MimeMessage mimeMessage;

    private static final String CONTENT_TYPE_HTML = "text/html";

    private static final String CONTENT_TYPE_CHARSET_SUFFIX = ";charset=";

    /**
     * @param session
     */
    public MailMessageImpl(Session session) {
        this.mimeMessage = new MimeMessage(session);
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setFrom(java.lang.String,
     *      java.lang.String)
     */
    public void setFrom(String from, String personal) throws MailException {
        try {
            this.mimeMessage.setFrom(new InternetAddress(from, personal));
        } catch (UnsupportedEncodingException e) {
            throw new MailException(e);
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setFrom(java.lang.String)
     */
    public void setFrom(String from) throws MailException {
        try {
            this.mimeMessage.setFrom(new InternetAddress(from));
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setReplyTo(java.lang.String)
     */
    public void setReplyTo(String replyTo) throws MailException {
        try {
            this.mimeMessage
                    .setReplyTo(new InternetAddress[] { new InternetAddress(
                            replyTo) });
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setTo(java.lang.String)
     */
    public void setTo(String to) throws MailException {
        try {
            this.mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setTo(java.util.List)
     */
    public void setTo(List toList) throws MailException {
        try {
            this.mimeMessage.setRecipients(Message.RecipientType.TO,
                    toInternetAddressArray(toList));
        } catch (MessagingException e) {
            throw new MailException(e);
        }

    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setCc(java.lang.String)
     */
    public void setCc(String cc) throws MailException {
        try {
            this.mimeMessage.setRecipient(Message.RecipientType.CC,
                    new InternetAddress(cc));
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setCc(java.util.List)
     */
    public void setCc(List ccList) throws MailException {
        try {
            this.mimeMessage.setRecipients(Message.RecipientType.CC,
                    toInternetAddressArray(ccList));
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setBcc(java.lang.String)
     */
    public void setBcc(String bcc) throws MailException {
        try {
            this.mimeMessage.setRecipient(Message.RecipientType.BCC,
                    new InternetAddress(bcc));
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setBcc(java.util.List)
     */
    public void setBcc(List bccList) throws MailException {
        try {
            this.mimeMessage.setRecipients(Message.RecipientType.BCC,
                    toInternetAddressArray(bccList));
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setSentDate(java.util.Date)
     */
    public void setSentDate(Date sentDate) throws MailException {
        try {
            this.mimeMessage.setSentDate(sentDate);
        } catch (MessagingException e) {
            throw new MailException(e);
        }

    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setSubject(java.lang.String)
     */
    public void setSubject(String subject) throws MailException {
        try {
            this.mimeMessage.setSubject(subject);
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setSubject(java.lang.String)
     */
    public void setSubject(String subject, String charset) throws MailException {
        try {
            this.mimeMessage.setSubject(subject, charset);
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setText(java.lang.String)
     */
    public void setText(String text, boolean html) throws MailException {
        try {
            Multipart multipt = new MimeMultipart();
            MimeBodyPart msgbody = new MimeBodyPart();
            msgbody.setText(text);
            multipt.addBodyPart(msgbody);
            if (html) {
                this.mimeMessage.setContent(multipt, CONTENT_TYPE_HTML);
            } else {
                this.mimeMessage.setContent(multipt);
            }
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setText(java.lang.String)
     */
    public void setText(String text, String charset, boolean html)
            throws MailException {
        try {
            Multipart multipt = new MimeMultipart();
            MimeBodyPart msgbody = new MimeBodyPart();
            msgbody.setText(text, charset);
            multipt.addBodyPart(msgbody);
            if (html) {
                this.mimeMessage.setContent(multipt, CONTENT_TYPE_HTML
                        + CONTENT_TYPE_CHARSET_SUFFIX + charset);
            } else {
                this.mimeMessage.setContent(multipt);
            }
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    protected InternetAddress[] toInternetAddressArray(List aList) {
        InternetAddress[] ia = (InternetAddress[]) aList
                .toArray(new InternetAddress[0]);
        return ia;
    }

    /**
     * @see org.teaframework.services.mail.MailMessage#setContentLanguage(java.lang.String[])
     */
    public void setContentLanguage(String[] langs) throws MailException {
        try {
            this.mimeMessage.setContentLanguage(langs);
        } catch (MessagingException e) {
            throw new MailException(e);
        }
    }

    /**
     * see org.huihoo.jfox.soaf.services.mail.MailMessage#getMimeMessage()
     */
    public MimeMessage getMimeMessage() {
        return this.mimeMessage;
    }

}