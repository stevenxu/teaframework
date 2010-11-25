/**
 * @(#)EncodingFilter.java
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

package org.teaframework.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Filter that sets the character encoding to be used in parsing the incoming
 * request, either unconditionally or only if the client did not specify a
 * character encoding. Resource of this filter is based on the following
 * initialization parameters:
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.7 $ $Date: 2006/02/15 08:45:45 $
 * @version Revision: 1.0
 */

public class EncodingFilter implements Filter {

    /**
     * The filter configuration object we are associated with. If this value is
     * null, this filter instance is not currently configured.
     */
    private FilterConfig config = null;

    /**
     * The default character encoding to set for requests that pass through this
     * filter.
     */
    private String targetEncoding = "utf-8";

    /**
     * Place this filter into service.
     * 
     * @param config The filter configuration object
     * @throws javax.servlet.ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        String encodingConfig = config.getInitParameter("encoding");
        if (encodingConfig != null) {
            this.targetEncoding = encodingConfig;
        }
    }

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        config = null;
        targetEncoding = null;
    }

    /**
     * Select and set (if specified) the character encoding to be used to
     * interpret request parameters for this request.
     * 
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding(targetEncoding);
        chain.doFilter(servletRequest, servletResponse);
    }
}