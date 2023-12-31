/*************************************************
 * MailerSend Java SDK
 * https://github.com/mailersend/mailersend-java
 * 
 * @author MailerSend <support@mailersend.com>
 * https://mailersend.com
 **************************************************/
package com.mailersend.sdk.tokens;

import com.google.gson.annotations.SerializedName;
import com.mailersend.sdk.MailerSendResponse;

/**
 * <p>TokenAddResponse class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class TokenAddResponse extends MailerSendResponse {

    @SerializedName("data")
    public TokenAdd tokenAdd;
}
