/**
 * @(#)NestedCheckedException.java
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

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Superclass for all checked Exceptions in JFoxSOAF.
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.9 $ $Date: 2005/05/22 06:47:24 $
 * @version Revision: 1.0
 */

public class NestedCheckedException extends Exception {

    /**
     * The exception that caused this one.
     */
    private Throwable cause;

    /**
     * Default Constructor.
     */
    public NestedCheckedException() {
        super();
    }

    /**
     * Construct a new exception with no cause and the specified detail message.
     * Note modern JVMs may still track the exception that caused this one.
     * 
     * @param message the message detailing the exception.
     */
    public NestedCheckedException(final String message) {
        super(message);
    }

    /**
     * Construct a new exception with the specified cause and no detail message.
     * 
     * @param cause the exception that caused this one.
     */
    public NestedCheckedException(final Throwable cause) {
        this.cause = cause;
    }

    /**
     * Construct a new exception with the specified cause and the specified
     * detail message.
     * 
     * @param message the message detailing the exception.
     * @param cause the exception that caused this one.
     */
    public NestedCheckedException(final String message, final Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * Retrieve the exception that caused this one.
     * 
     * @return the exception that caused this one, or null if it was not set.
     * @see Throwable#getCause() the method available since JDK 1.3 that is
     *      overridden by this method.
     */
    public Throwable getCause() {
        return this.cause;
    }

    /**
     * Returns the detail message string of this throwable.
     * 
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage() {
        if (this.cause == null || this.cause == this) {
            return super.getMessage();
        } else {
            return new StringBuffer().append(super.getMessage()).append(
                    ": nested throwable: ").append(
                    this.cause.getClass().getName()).append(": ").append(
                    this.cause.getMessage()).toString();
        }
    }

    /**
     * Prints this throwable and its backtrace to the standard error stream
     * 
     * @see java.lang.Throwable#printStackTrace()
     */
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    /**
     * Print the composite message and the embedded stack trace to the specified
     * stream.
     * 
     * @param printStrem
     */
    public void printStackTrace(PrintStream printStrem) {
        if (this.cause == null || this.cause == this) {
            super.printStackTrace(printStrem);
        } else {
            cause.printStackTrace(printStrem);
        }

    }

    /**
     * Print the composite message and the embedded stack trace to the specified
     * writer.
     * 
     * @param printWriter
     */
    public void printStackTrace(PrintWriter printWriter) {
        if (this.cause == null || this.cause == this) {
            super.printStackTrace(printWriter);
        } else {
            cause.printStackTrace(printWriter);
        }

    }
}