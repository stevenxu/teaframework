/**
 * @(#)ApplicationException.java
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
 * <p>
 * Parent exception for all Application level exception.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.2 $ $Date: 2005/05/22 06:47:24 $
 * @version Revision: 1.0
 */

public class ApplicationException extends NestedCheckedException {

    /**
     * Construct a new exception with no cause and the specified detail message.
     * Note modern JVMs may still track the exception that caused this one.
     * 
     * @param message the message detailing the exception.
     */
    protected ApplicationException(final String message) {
        super(message);
    }

    /**
     * Construct a new exception with the specified cause and no detail message.
     * 
     * @param cause the exception that caused this one.
     */
    protected ApplicationException(final Throwable cause) {
        super(cause);
    }

    /**
     * Construct a new exception with the specified cause and the specified
     * detail message.
     * 
     * @param message the message detailing the exception.
     * @param cause the exception that caused this one.
     */
    protected ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}