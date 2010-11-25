/**
 * @(#)IbatisConfigurationException.java
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

package org.teaframework.exception;

/**
 * Represents exceptions raised when initialize Ibatis Dao configration.
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.5 $ $Date: 2005/05/22 06:47:24 $
 * @version Revision: 1.0
 */

public class IbatisConfigurationException extends NestedRuntimeException {

    /**
     * Construct a new exception with no cause and no detail message. Note
     * modern JVMs may still track the exception that caused this one.
     */
    public IbatisConfigurationException() {
    }

    /**
     * Construct a new exception with no cause and the specified detail message.
     * Note modern JVMs may still track the exception that caused this one.
     * 
     * @param message the message detailing the exception.
     */
    public IbatisConfigurationException(final String message) {
        super(message);

    }

    /**
     * Construct a new exception with the specified cause and no detail message.
     * 
     * @param cause the exception that caused this one.
     */
    public IbatisConfigurationException(final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * Construct a new exception with the specified cause and no detail message.
     * 
     * @param cause the exception that caused this one.
     */
    public IbatisConfigurationException(final Throwable cause) {
        super(cause);
    }
}