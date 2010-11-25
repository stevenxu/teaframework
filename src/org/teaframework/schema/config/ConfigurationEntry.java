/**
 * @(#)ConfigurationEntry.java
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

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ConfigurationEntry.
 * 
 * @version $Revision: 1.8 $ $Date: 2005/05/22 06:47:43 $
 */

public class ConfigurationEntry implements java.io.Serializable {

    //--------------------------/
    //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name
     */
    private java.lang.String _name;

    /**
     * Field _value
     */
    private java.lang.String _value;

    //----------------/
    //- Constructors -/
    //----------------/

    public ConfigurationEntry() {
        super();
    } //-- org.huihoo.jfox.soaf.schema.config.ConfigurationEntry()

    //-----------/
    //- Methods -/
    //-----------/

    /**
     * Method getNameReturns the value of field 'name'.
     * 
     * @return the value of field 'name'.
     */
    public java.lang.String getName() {
        return this._name;
    } //-- java.lang.String getName()

    /**
     * Method getValueReturns the value of field 'value'.
     * 
     * @return the value of field 'value'.
     */
    public java.lang.String getValue() {
        return this._value;
    } //-- java.lang.String getValue()

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
    } //-- boolean isValid()

    /**
     * Method marshal
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {

        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer)

    /**
     * Method marshal
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
            throws java.io.IOException, org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {

        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler)

    /**
     * Method setNameSets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name) {
        this._name = name;
    } //-- void setName(java.lang.String)

    /**
     * Method setValueSets the value of field 'value'.
     * 
     * @param value the value of field 'value'.
     */
    public void setValue(java.lang.String value) {
        this._value = value;
    } //-- void setValue(java.lang.String)

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static org.teaframework.schema.config.ConfigurationEntry unmarshal(
            java.io.Reader reader)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {
        return (org.teaframework.schema.config.ConfigurationEntry) Unmarshaller
                .unmarshal(
                        org.teaframework.schema.config.ConfigurationEntry.class,
                        reader);
    } //-- org.huihoo.jfox.soaf.schema.config.ConfigurationEntry

    // unmarshal(java.io.Reader)

    /**
     * Method validate
     */
    public void validate() throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate()

}