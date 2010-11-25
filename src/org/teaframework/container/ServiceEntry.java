/**
 * @(#)ServiceEntry.java
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

package org.teaframework.container;

import java.util.Enumeration;

import org.teaframework.schema.service.Parameter;

/**
 * <p>
 * The service entry is used to access a boundary service in the container.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.7 $ $Date: 2005/05/22 06:46:49 $
 * @version Revision: 1.0
 */

public interface ServiceEntry {

    /**
     * Method addParameter
     * 
     * @param vParameter
     */
    public void addParameter(Parameter vParameter)
            throws IndexOutOfBoundsException;

    /**
     * Method addParameter
     * 
     * @param index
     * @param vParameter
     */
    public void addParameter(int index,
            org.teaframework.schema.service.Parameter vParameter)
            throws IndexOutOfBoundsException;

    /**
     * Method enumerateParameter
     */
    public Enumeration enumerateParameter();

    /**
     * Method getDescriptionReturns the value of field 'description'.
     * 
     * @return the value of field 'description'.
     */
    public String getDescription();

    /**
     * Method getIdReturns the value of field 'id'.
     * 
     * @return the value of field 'id'.
     */
    public String getId();

    /**
     * Method getImplementationReturns the value of field 'implementation'.
     * 
     * @return the value of field 'implementation'.
     */
    public String getImplementation();

    /**
     * Method getInterfaceReturns the value of field 'interface'.
     * 
     * @return the value of field 'interface'.
     */
    public String getInterface();

    /**
     * Method getManageableReturns the value of field 'manageable'.
     * 
     * @return the value of field 'manageable'.
     */
    public String getManageable();

    /**
     * Method getParameter
     * 
     * @param index
     */
    public Parameter getParameter(int index) throws IndexOutOfBoundsException;

    /**
     * Method getParameter
     */
    public Parameter[] getParameter();

    /**
     * Method getParameterCount
     */
    public int getParameterCount();

    /**
     * Method isValid
     */
    public boolean isValid();

    /**
     * Method marshal
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException;

    /**
     * Method marshal
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
            throws java.io.IOException, org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException;

    /**
     * Method removeAllParameter
     */
    public void removeAllParameter();

    /**
     * Method removeParameter
     * 
     * @param index
     */
    public Parameter removeParameter(int index);

    /**
     * Method setDescriptionSets the value of field 'description'.
     * 
     * @param description the value of field 'description'.
     */
    public void setDescription(String description);

    /**
     * Method setIdSets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(String id);

    /**
     * Method setImplementationSets the value of field 'implementation'.
     * 
     * @param implementation the value of field 'implementation'.
     */
    public void setImplementation(String implementation);

    /**
     * Method setInterfaceSets the value of field 'interface'.
     * 
     * @param _interface
     * @param interface the value of field 'interface'.
     */
    public void setInterface(String _interface);

    /**
     * Method setManageableSets the value of field 'manageable'.
     * 
     * @param manageable the value of field 'manageable'.
     */
    public void setManageable(String manageable);

    /**
     * Method setParameter
     * 
     * @param index
     * @param vParameter
     */
    public void setParameter(int index, Parameter vParameter)
            throws IndexOutOfBoundsException;

    /**
     * Method setParameter
     * 
     * @param parameterArray
     */
    public void setParameter(Parameter[] parameterArray);

    /**
     * Method validate
     */
    public void validate() throws org.exolab.castor.xml.ValidationException;

}