/*************************************************
 * MailerSend Java SDK
 * https://github.com/mailersend/mailersend-java
 * 
 * @author MailerSend <support@mailersend.com>
 * https://mailersend.com
 **************************************************/
package com.mailersend.sdk.recipients;

import com.google.gson.annotations.SerializedName;
import com.mailersend.sdk.util.PaginatedResponse;

/**
 * <p>SuppressionList class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class SuppressionList extends PaginatedResponse {

    @SerializedName("data")
    public SuppressionItem[] items;
}
