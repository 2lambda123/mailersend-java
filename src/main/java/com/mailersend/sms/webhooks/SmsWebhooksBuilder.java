/*************************************************
 * MailerSend Java SDK
 * https://github.com/mailersend/mailersend-java
 * 
 * @author MailerSend <support@mailersend.com>
 * https://mailersend.com
 **************************************************/
package com.mailersend.sms.webhooks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendApi;
import com.mailersend.sdk.exceptions.MailerSendException;
import com.mailersend.sdk.util.JsonSerializationDeserializationStrategy;

/**
 * <p>SmsWebhooksBuilder class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class SmsWebhooksBuilder {

	 private SmsWebhookBuilderBody builderBody = new SmsWebhookBuilderBody();
	    
	    private MailerSend apiObjectReference;
	    
	    
	    /**
	     * No instantiation from outside the sdk
	     *
	     * @param apiObjectRef a {@link com.mailersend.sdk.MailerSend} object.
	     */
	    protected SmsWebhooksBuilder(MailerSend apiObjectRef) {
	        
	        apiObjectReference = apiObjectRef;
	        
	    }
	    
	    
	    /**
	     * Set the webhook's url
	     *
	     * @param url a {@link java.lang.String} object.
	     * @return a {@link com.mailersend.sms.webhooks.SmsWebhooksBuilder} object.
	     */
	    public SmsWebhooksBuilder url(String url) {
	        
	        builderBody.url = url;
	        
	        return this;
	    }
	    
	    
	    /**
	     * Set the webhook's name
	     *
	     * @param name a {@link java.lang.String} object.
	     * @return a {@link com.mailersend.sms.webhooks.SmsWebhooksBuilder} object.
	     */
	    public SmsWebhooksBuilder name(String name) {
	        
	        builderBody.name = name;
	        
	        return this;
	    }
	    
	    
	    /**
	     * Add an event for the webhook
	     *
	     * @param event a {@link java.lang.String} object.
	     * @return a {@link com.mailersend.sms.webhooks.SmsWebhooksBuilder} object.
	     */
	    public SmsWebhooksBuilder addEvent(String event) {
	        
	        builderBody.events.add(event);
	        
	        return this;
	    }
	    
	    
	    /**
	     * Clears the events of the webhook request
	     *
	     * @return a {@link com.mailersend.sms.webhooks.SmsWebhooksBuilder} object.
	     */
	    public SmsWebhooksBuilder clearEvents() {
	        
	        builderBody.events.clear();
	        
	        return this;
	    }
	    
	    
	    /**
	     * Creates a webhook
	     *
	     * @throws com.mailersend.sdk.exceptions.MailerSendException
	     * @param smsNumberId a {@link java.lang.String} object.
	     * @return a {@link com.mailersend.sms.webhooks.SmsWebhook} object.
	     */
	    public SmsWebhook createWebhook(String smsNumberId) throws MailerSendException {
	        
	        builderBody.smsNumberId = smsNumberId;
	        
	        if (builderBody.name == null || builderBody.name.isBlank()) {
	            
	            throw new MailerSendException("Webhook name cannot be empty");
	        }
	        
	        if (builderBody.url == null || builderBody.url.isBlank()) {
	            
	            throw new MailerSendException("Webhook URL cannot be empty");
	        }
	        
	        if (smsNumberId == null || smsNumberId.isBlank()) {
	            
	            throw new MailerSendException("Sms number ID cannot be empty");
	        }
	    
	        
	        String endpoint = "/sms-webhooks";
	        
	        MailerSendApi api = new MailerSendApi();
	        api.setToken(apiObjectReference.getToken());
	        
	        Gson gson = new GsonBuilder()
	                .addSerializationExclusionStrategy(new JsonSerializationDeserializationStrategy(false))
	                .addDeserializationExclusionStrategy(new JsonSerializationDeserializationStrategy(true))
	                .create();
	        
	        String json = gson.toJson(builderBody);
	        
	        builderBody = new SmsWebhookBuilderBody();
	        
	        SingleSmsWebhookResponse response = api.postRequest(endpoint, json, SingleSmsWebhookResponse.class);
	        
	        return response.webhook;
	    }
	    
	    
	    /**
	     * Updates the webhook with the given id
	     *
	     * @param webhookId a {@link java.lang.String} object.
	     * @throws com.mailersend.sdk.exceptions.MailerSendException
	     * @return a {@link com.mailersend.sms.webhooks.SmsWebhook} object.
	     */
	    public SmsWebhook updateWebhook(String webhookId) throws MailerSendException {
	        
	        if (webhookId == null || webhookId.isBlank()) {
	            
	            throw new MailerSendException("Webhook ID cannot be empty");
	        }
	        
	        String endpoint = "/sms-webhooks/".concat(webhookId);

	        
	        MailerSendApi api = new MailerSendApi();
	        api.setToken(apiObjectReference.getToken());
	        
	        Gson gson = new GsonBuilder()
	                .addSerializationExclusionStrategy(new JsonSerializationDeserializationStrategy(false))
	                .addDeserializationExclusionStrategy(new JsonSerializationDeserializationStrategy(true))
	                .create();
	        
	        String json = gson.toJson(builderBody);
	        
	        builderBody = new SmsWebhookBuilderBody();
	        
	        SingleSmsWebhookResponse response = api.putRequest(endpoint, json, SingleSmsWebhookResponse.class);
	        
	        return response.webhook;
	    }
}
