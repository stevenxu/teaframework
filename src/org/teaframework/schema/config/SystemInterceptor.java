/**
 * @(#)SystemInterceptor.java
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

package org.teaframework.schema.config;

import java.util.Vector;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class SystemInterceptor.
 * 
 * @version $Revision: 1.4 $ $Date: 2005/01/17 13:29:59 $
 */
public class SystemInterceptor implements java.io.Serializable {

    /**
     * Field _interceptorList
     */
    private java.util.Vector _interceptorList;

    public SystemInterceptor() {
        super();
        _interceptorList = new Vector();
    }

    /**
     * Method addInterceptor
     * 
     * @param vInterceptor
     */
    public void addInterceptor(
            org.teaframework.schema.config.Interceptor vInterceptor)
            throws java.lang.IndexOutOfBoundsException {
        _interceptorList.addElement(vInterceptor);
    }

    /**
     * Method addInterceptor
     * 
     * @param index
     * @param vInterceptor
     */
    public void addInterceptor(int index,
            org.teaframework.schema.config.Interceptor vInterceptor)
            throws java.lang.IndexOutOfBoundsException {
        _interceptorList.insertElementAt(vInterceptor, index);
    }

    /**
     * Method enumerateInterceptor
     */
    public java.util.Enumeration enumerateInterceptor() {
        return _interceptorList.elements();
    }

    /**
     * Method getInterceptor
     * 
     * @param index
     */
    public org.teaframework.schema.config.Interceptor getInterceptor(
            int index) throws java.lang.IndexOutOfBoundsException {
        //-- check bounds for index
        if ((index < 0) || (index > _interceptorList.size())) {
            throw new IndexOutOfBoundsException();
        }

        return (org.teaframework.schema.config.Interceptor) _interceptorList
                .elementAt(index);
    }

    /**
     * Method getInterceptor
     */
    public org.teaframework.schema.config.Interceptor[] getInterceptor() {
        int size = _interceptorList.size();
        org.teaframework.schema.config.Interceptor[] mArray = new org.teaframework.schema.config.Interceptor[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.teaframework.schema.config.Interceptor) _interceptorList
                    .elementAt(index);
        }
        return mArray;
    }

    /**
     * Method getInterceptorCount
     */
    public int getInterceptorCount() {
        return _interceptorList.size();
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
     * Method removeAllInterceptor
     */
    public void removeAllInterceptor() {
        _interceptorList.removeAllElements();
    }

    /**
     * Method removeInterceptor
     * 
     * @param index
     */
    public org.teaframework.schema.config.Interceptor removeInterceptor(
            int index) {
        java.lang.Object obj = _interceptorList.elementAt(index);
        _interceptorList.removeElementAt(index);
        return (org.teaframework.schema.config.Interceptor) obj;
    }

    /**
     * Method setInterceptor
     * 
     * @param index
     * @param vInterceptor
     */
    public void setInterceptor(int index,
            org.teaframework.schema.config.Interceptor vInterceptor)
            throws java.lang.IndexOutOfBoundsException {
        //-- check bounds for index
        if ((index < 0) || (index > _interceptorList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _interceptorList.setElementAt(vInterceptor, index);
    }

    /**
     * Method setInterceptor
     * 
     * @param interceptorArray
     */
    public void setInterceptor(
            org.teaframework.schema.config.Interceptor[] interceptorArray) {
        //-- copy array
        _interceptorList.removeAllElements();
        for (int i = 0; i < interceptorArray.length; i++) {
            _interceptorList.addElement(interceptorArray[i]);
        }
    }

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static org.teaframework.schema.config.SystemInterceptor unmarshal(
            java.io.Reader reader)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {
        return (org.teaframework.schema.config.SystemInterceptor) Unmarshaller
                .unmarshal(
                        org.teaframework.schema.config.SystemInterceptor.class,
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