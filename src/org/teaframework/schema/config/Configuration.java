/**
 * @(#)Configuration.java
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

import java.io.Reader;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.Validator;

/**
 * Configuration.
 * 
 * @author <a href="mailto:founder_chen@yahoo.com.cn">Peter Cheng </a>
 * @version $Revision: 1.10 $ $Date: 2005/05/22 06:47:43 $
 * @version Revision: 1.0
 */

public class Configuration implements java.io.Serializable {

    /**
     * Field _serviceConfiguration
     */
    private ServiceConfiguration _serviceConfiguration;

    /**
     * Field _systemInterceptor
     */
    private SystemInterceptor _systemInterceptor;

    public Configuration() {
        super();
    }

    /**
     * Method getServiceConfigurationReturns the value of field
     * 'serviceConfiguration'.
     * 
     * @return the value of field 'serviceConfiguration'.
     */
    public ServiceConfiguration getServiceConfiguration() {
        return this._serviceConfiguration;
    }

    /**
     * Method getSystemInterceptorReturns the value of field
     * 'systemInterceptor'.
     * 
     * @return the value of field 'systemInterceptor'.
     */
    public SystemInterceptor getSystemInterceptor() {
        return this._systemInterceptor;
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
     * Method setServiceConfigurationSets the value of field
     * 'serviceConfiguration'.
     * 
     * @param serviceConfiguration the value of field 'serviceConfiguration'.
     */
    public void setServiceConfiguration(
            ServiceConfiguration serviceConfiguration) {
        this._serviceConfiguration = serviceConfiguration;
    }

    /**
     * Method setSystemInterceptorSets the value of field 'systemInterceptor'.
     * 
     * @param systemInterceptor the value of field 'systemInterceptor'.
     */
    public void setSystemInterceptor(SystemInterceptor systemInterceptor) {
        this._systemInterceptor = systemInterceptor;
    }

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static Configuration unmarshal(Reader reader)
            throws org.exolab.castor.xml.MarshalException,
            org.exolab.castor.xml.ValidationException {
        return (Configuration) Unmarshaller.unmarshal(
                org.teaframework.schema.config.Configuration.class, reader);
    }

    /**
     * Method validate
     */
    public void validate() throws ValidationException {
        Validator validator = new Validator();
        validator.validate(this);
    }

}