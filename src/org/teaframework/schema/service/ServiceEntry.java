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

package org.teaframework.schema.service;

import java.util.Vector;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * <p>
 * Class ServiceEntry.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.7 $ $Date: 2005/05/22 06:48:07 $
 * @version Revision: 1.0
 */

public class ServiceEntry implements java.io.Serializable,
        org.teaframework.container.ServiceEntry {

    /**
     * Field _id
     */
    private java.lang.String _id;

    /**
     * Field _description
     */
    private java.lang.String _description;

    /**
     * Field _manageable
     */
    private java.lang.String _manageable;

    /**
     * Field _interface
     */
    private java.lang.String _interface;

    /**
     * Field _implementation
     */
    private java.lang.String _implementation;

    /**
     * Field _parameterList
     */
    private java.util.Vector _parameterList;

    public ServiceEntry() {
        super();
        _parameterList = new Vector();
    }

    /**
     * Method addParameter
     * 
     * @param vParameter
     */
    public void addParameter(
            org.teaframework.schema.service.Parameter vParameter)
            throws java.lang.IndexOutOfBoundsException {
        _parameterList.addElement(vParameter);
    }

    /**
     * Method addParameter
     * 
     * @param index
     * @param vParameter
     */
    public void addParameter(int index,
            org.teaframework.schema.service.Parameter vParameter)
            throws java.lang.IndexOutOfBoundsException {
        _parameterList.insertElementAt(vParameter, index);
    }

    /**
     * Method enumerateParameter
     */
    public java.util.Enumeration enumerateParameter() {
        return _parameterList.elements();
    }

    /**
     * Method getDescriptionReturns the value of field 'description'.
     * 
     * @return the value of field 'description'.
     */
    public java.lang.String getDescription() {
        return this._description;
    }

    /**
     * Method getIdReturns the value of field 'id'.
     * 
     * @return the value of field 'id'.
     */
    public java.lang.String getId() {
        return this._id;
    }

    /**
     * Method getImplementationReturns the value of field 'implementation'.
     * 
     * @return the value of field 'implementation'.
     */
    public java.lang.String getImplementation() {
        return this._implementation;
    }

    /**
     * Method getInterfaceReturns the value of field 'interface'.
     * 
     * @return the value of field 'interface'.
     */
    public java.lang.String getInterface() {
        return this._interface;
    }

    /**
     * Method getManageableReturns the value of field 'manageable'.
     * 
     * @return the value of field 'manageable'.
     */
    public java.lang.String getManageable() {
        return this._manageable;
    }

    /**
     * Method getParameter
     * 
     * @param index
     */
    public org.teaframework.schema.service.Parameter getParameter(int index)
            throws java.lang.IndexOutOfBoundsException {
        //-- check bounds for index
        if ((index < 0) || (index > _parameterList.size())) {
            throw new IndexOutOfBoundsException();
        }

        return (org.teaframework.schema.service.Parameter) _parameterList
                .elementAt(index);
    }

    /**
     * Method getParameter
     */
    public org.teaframework.schema.service.Parameter[] getParameter() {
        int size = _parameterList.size();
        org.teaframework.schema.service.Parameter[] mArray = new org.teaframework.schema.service.Parameter[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.teaframework.schema.service.Parameter) _parameterList
                    .elementAt(index);
        }
        return mArray;
    }

    /**
     * Method getParameterCount
     */
    public int getParameterCount() {
        return _parameterList.size();
    }

    /**
     * Method isValid
     */
    public boolean isValid() {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Method marshal
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {

        Marshaller.marshal(this, out);
    }

    /**
     * Method marshal
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
            throws java.io.IOException, org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {

        Marshaller.marshal(this, handler);
    }

    /**
     * Method removeAllParameter
     */
    public void removeAllParameter() {
        _parameterList.removeAllElements();
    }

    /**
     * Method removeParameter
     * 
     * @param index
     */
    public org.teaframework.schema.service.Parameter removeParameter(
            int index) {
        java.lang.Object obj = _parameterList.elementAt(index);
        _parameterList.removeElementAt(index);
        return (org.teaframework.schema.service.Parameter) obj;
    }

    /**
     * Method setDescriptionSets the value of field 'description'.
     * 
     * @param description the value of field 'description'.
     */
    public void setDescription(java.lang.String description) {
        this._description = description;
    }

    /**
     * Method setIdSets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(java.lang.String id) {
        this._id = id;
    }

    /**
     * Method setImplementationSets the value of field 'implementation'.
     * 
     * @param implementation the value of field 'implementation'.
     */
    public void setImplementation(java.lang.String implementation) {
        this._implementation = implementation;
    }

    /**
     * Method setInterfaceSets the value of field 'interface'.
     * 
     * @param _interface
     * @param interface the value of field 'interface'.
     */
    public void setInterface(java.lang.String _interface) {
        this._interface = _interface;
    }

    /**
     * Method setManageableSets the value of field 'manageable'.
     * 
     * @param manageable the value of field 'manageable'.
     */
    public void setManageable(java.lang.String manageable) {
        this._manageable = manageable;
    }

    /**
     * Method setParameter
     * 
     * @param index
     * @param vParameter
     */
    public void setParameter(int index,
            org.teaframework.schema.service.Parameter vParameter)
            throws java.lang.IndexOutOfBoundsException {
        //-- check bounds for index
        if ((index < 0) || (index > _parameterList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _parameterList.setElementAt(vParameter, index);
    }

    /**
     * Method setParameter
     * 
     * @param parameterArray
     */
    public void setParameter(
            org.teaframework.schema.service.Parameter[] parameterArray) {
        //-- copy array
        _parameterList.removeAllElements();
        for (int i = 0; i < parameterArray.length; i++) {
            _parameterList.addElement(parameterArray[i]);
        }
    }

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static org.teaframework.schema.service.ServiceEntry unmarshal(
            java.io.Reader reader)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {
        return (org.teaframework.schema.service.ServiceEntry) Unmarshaller
                .unmarshal(
                        org.teaframework.schema.service.ServiceEntry.class,
                        reader);
    }

    /**
     * Method validate
     */
    public void validate() throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}