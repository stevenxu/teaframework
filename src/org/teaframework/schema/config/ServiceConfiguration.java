/**
 * @(#)ServiceConfiguration.java
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
 * Class ServiceConfiguration.
 * 
 * @version $Revision: 1.5 $ $Date: 2005/05/22 06:47:43 $
 */
public class ServiceConfiguration implements java.io.Serializable {

    /**
     * Field _configurationEntryList
     */
    private java.util.Vector _configurationEntryList;

    public ServiceConfiguration() {
        super();
        _configurationEntryList = new Vector();
    }

    /**
     * Method addConfigurationEntry
     * 
     * @param vConfigurationEntry
     */
    public void addConfigurationEntry(
            org.teaframework.schema.config.ConfigurationEntry vConfigurationEntry)
            throws java.lang.IndexOutOfBoundsException {
        _configurationEntryList.addElement(vConfigurationEntry);
    }

    /**
     * Method addConfigurationEntry
     * 
     * @param index
     * @param vConfigurationEntry
     */
    public void addConfigurationEntry(
            int index,
            org.teaframework.schema.config.ConfigurationEntry vConfigurationEntry)
            throws java.lang.IndexOutOfBoundsException {
        _configurationEntryList.insertElementAt(vConfigurationEntry, index);
    }

    /**
     * Method enumerateConfigurationEntry
     */
    public java.util.Enumeration enumerateConfigurationEntry() {
        return _configurationEntryList.elements();
    }

    /**
     * Method getConfigurationEntry
     * 
     * @param index
     */
    public org.teaframework.schema.config.ConfigurationEntry getConfigurationEntry(
            int index) throws java.lang.IndexOutOfBoundsException {
        //-- check bounds for index
        if ((index < 0) || (index > _configurationEntryList.size())) {
            throw new IndexOutOfBoundsException();
        }

        return (org.teaframework.schema.config.ConfigurationEntry) _configurationEntryList
                .elementAt(index);
    }

    /**
     * Method getConfigurationEntry
     */
    public org.teaframework.schema.config.ConfigurationEntry[] getConfigurationEntry() {
        int size = _configurationEntryList.size();
        org.teaframework.schema.config.ConfigurationEntry[] mArray = new org.teaframework.schema.config.ConfigurationEntry[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (org.teaframework.schema.config.ConfigurationEntry) _configurationEntryList
                    .elementAt(index);
        }
        return mArray;
    }

    /**
     * Method getConfigurationEntryCount
     */
    public int getConfigurationEntryCount() {
        return _configurationEntryList.size();
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
     * Method removeAllConfigurationEntry
     */
    public void removeAllConfigurationEntry() {
        _configurationEntryList.removeAllElements();
    }

    /**
     * Method removeConfigurationEntry
     * 
     * @param index
     */
    public org.teaframework.schema.config.ConfigurationEntry removeConfigurationEntry(
            int index) {
        java.lang.Object obj = _configurationEntryList.elementAt(index);
        _configurationEntryList.removeElementAt(index);
        return (org.teaframework.schema.config.ConfigurationEntry) obj;
    }

    /**
     * Method setConfigurationEntry
     * 
     * @param index
     * @param vConfigurationEntry
     */
    public void setConfigurationEntry(
            int index,
            org.teaframework.schema.config.ConfigurationEntry vConfigurationEntry)
            throws java.lang.IndexOutOfBoundsException {
        //-- check bounds for index
        if ((index < 0) || (index > _configurationEntryList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _configurationEntryList.setElementAt(vConfigurationEntry, index);
    }

    /**
     * Method setConfigurationEntry
     * 
     * @param configurationEntryArray
     */
    public void setConfigurationEntry(
            org.teaframework.schema.config.ConfigurationEntry[] configurationEntryArray) {
        //-- copy array
        _configurationEntryList.removeAllElements();
        for (int i = 0; i < configurationEntryArray.length; i++) {
            _configurationEntryList.addElement(configurationEntryArray[i]);
        }
    }

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static org.teaframework.schema.config.ServiceConfiguration unmarshal(
            java.io.Reader reader)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {
        return (org.teaframework.schema.config.ServiceConfiguration) Unmarshaller
                .unmarshal(
                        org.teaframework.schema.config.ServiceConfiguration.class,
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