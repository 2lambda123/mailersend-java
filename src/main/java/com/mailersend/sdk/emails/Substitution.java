/*************************************************
 * MailerSend Java SDK
 * https://github.com/mailersend/mailersend-java
 * 
 * @author MailerSend <support@mailersend.com>
 * https://mailersend.com
 **************************************************/
package com.mailersend.sdk.emails;

import com.google.gson.annotations.SerializedName;

/**
 * <p>Substitution class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class Substitution {

    @SerializedName("var")
    public String variable;

    @SerializedName("value")
    public String value;
    
    
    /**
     * Simple constructor to setup the properties
     *
     * @param variable a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     */
    public Substitution(String variable, String value) {
        
        this.variable = variable;
        this.value = value;
    }
    
    
    /**
     * Allow instantiation without setting the properties
     */
    public Substitution() {
        
        // left empty on purpose
    }
}
