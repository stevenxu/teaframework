/**
 * @(#)Service.java
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
 * Class Service.
 * </p>
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>*
 * @version $Revision: 1.6 $ $Date: 2005/01/17 13:29:57 $
 * @version Revision: 1.0
 */

public class Service implements java.io.Serializable {

    /**
     * Field _serviceEntryList
     */
    private java.util.Vector _serviceEntryList;

    public Service() {
        super();
        _serviceEntryList = new Vector();
    }

    /**
     * Method addServiceEntry
     * 
     * @param vServiceEntry
     */
    public void addServiceEntry(
            org.teaframework.schema.service.ServiceEntry vServiceEntry)
            throws java.lang.IndexOutOfBoundsException {
        _serviceEntryList.addElement(vServiceEntry);
    }

    /**
     * Method addServiceEntry
     * 
     * @param index
     * @param vServiceEntry
     */
    public void addServiceEntry(int index,
            org.teaframework.schema.service.ServiceEntry vServiceEntry)
            throws java.lang.IndexOutOfBoundsException {
        _serviceEntryList.insertElementAt(vServiceEntry, index);
    }

    /**
     * Method enumerateServiceEntry
     */
    public java.util.Enumeration enumerateServiceEntry() {
        return _serviceEntryList.elements();
    }

    /**
     * Method getServiceEntry
     * 
     * @param index
     */
    public org.teaframework.schema.service.ServiceEntry getServiceEntry(
            int index) throws java.lang.IndexOutOfBoundsException {
        //-- check bounds for index
        if ((index < 0) || (index > _serviceEntryList.size())) {
            throw new IndexOutOfBoundsException();
        }

        return (org.teaframework.schema.service.ServiceEntry) _serviceEntryList
                .elementAt(index);
    }

    /**
     * Method getServiceEntry
     */
    public org.teaframework.schema.service.ServiceEntry[] getServiceEntry() {
        int size = _serviceEntryList.size();
        org.teaframework.schema.service.ServiceEntry[] mArray = new org.teaframework.schema.service.ServiceEntry[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.teaframework.schema.service.ServiceEntry) _serviceEntryList
                    .elementAt(index);
        }
        return mArray;
    }

    /**
     * Method getServiceEntryCount
     */
    public int getServiceEntryCount() {
        return _serviceEntryList.size();
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
     * Method removeAllServiceEntry
     */
    public void removeAllServiceEntry() {
        _serviceEntryList.removeAllElements();
    }

    /**
     * Method removeServiceEntry
     * 
     * @param index
     */
    public org.teaframework.schema.service.ServiceEntry removeServiceEntry(
            int index) {
        java.lang.Object obj = _serviceEntryList.elementAt(index);
        _serviceEntryList.removeElementAt(index);
        return (org.teaframework.schema.service.ServiceEntry) obj;
    }

    /**
     * Method setServiceEntry
     * 
     * @param index
     * @param vServiceEntry
     */
    public void setServiceEntry(int index,
            org.teaframework.schema.service.ServiceEntry vServiceEntry)
            throws java.lang.IndexOutOfBoundsException {
        //-- check bounds for index
        if ((index < 0) || (index > _serviceEntryList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _serviceEntryList.setElementAt(vServiceEntry, index);
    }

    /**
     * Method setServiceEntry
     * 
     * @param serviceEntryArray
     */
    public void setServiceEntry(
            org.teaframework.schema.service.ServiceEntry[] serviceEntryArray) {
        //-- copy array
        _serviceEntryList.removeAllElements();
        for (int i = 0; i < serviceEntryArray.length; i++) {
            _serviceEntryList.addElement(serviceEntryArray[i]);
        }
    }

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static org.teaframework.schema.service.Service unmarshal(
            java.io.Reader reader)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {
        return (org.teaframework.schema.service.Service) Unmarshaller
                .unmarshal(org.teaframework.schema.service.Service.class,
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