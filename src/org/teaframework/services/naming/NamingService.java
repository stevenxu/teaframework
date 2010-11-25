/**
 * @(#)NamingService.java
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

package org.teaframework.services.naming;

import javax.naming.NamingException;

/**
 * <p>
 * JNDI Service.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.1 $ $Date: 2006/02/15 08:27:46 $
 * @version Revision: 1.0
 */

public interface NamingService {
	
	/**
	 * Retrieves the named object.
	 * 
	 * @param name - the name of the object to look up
	 * @return object - the object bound to name
	 * @throws NamingException - if a naming exception is encountered
	 */
	public Object lookup(String name) throws NamingException;
	
	/**
	 * Binds a name to an object.
	 * 
	 * @param name - the name to bind; may not be empty
	 * @param obj - the object to bind; possibly null
	 * @throws NamingException if a naming exception is encountered
	 */
	public void bind(String name, Object obj) throws NamingException;
	
	/**
	 * Binds a name to an object, overwriting any existing binding.
	 * 
	 * @param name - the name to bind; may not be empty
	 * @param obj - the object to bind; possibly null
	 * @throws NamingException - if a naming exception is encountered
	 */
	public void rebind(String name, Object obj) throws NamingException;
	
	/**
	 * Unbinds the named object.
	 * 
	 * @param name - the name to unbind; may not be empty
	 * @throws NamingException - if a naming exception is encountered
	 */
	public void unbind(String name) throws NamingException;

}
