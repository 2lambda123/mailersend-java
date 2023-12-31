/*************************************************
 * MailerSend Java SDK
 * https://github.com/mailersend/mailersend-java
 * 
 * @author MailerSend <support@mailersend.com>
 * https://mailersend.com
 **************************************************/
package com.mailersend.sdk.templates;

import com.google.gson.annotations.SerializedName;
import com.mailersend.sdk.domains.Domain;

/**
 * <p>TemplateDomain class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class TemplateDomain extends Domain {

    @SerializedName("totals")
    public TemplateDomainTotals totals;
}
