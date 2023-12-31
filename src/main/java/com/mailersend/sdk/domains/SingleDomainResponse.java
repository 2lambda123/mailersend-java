/*************************************************
 * MailerSend Java SDK
 * https://github.com/mailersend/mailersend-java
 * 
 * @author MailerSend <support@mailersend.com>
 * https://mailersend.com
 **************************************************/
package com.mailersend.sdk.domains;

import com.google.gson.annotations.SerializedName;
import com.mailersend.sdk.MailerSendResponse;

/**
 * <p>SingleDomainResponse class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class SingleDomainResponse extends MailerSendResponse {

    @SerializedName("data")
    public Domain domain;
}
