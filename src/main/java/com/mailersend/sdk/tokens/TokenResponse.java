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
 * <p>TokenResponse class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class TokenResponse extends MailerSendResponse {

    @SerializedName("data")
    public Token token;
}
