package com.mailersend.sdk.scheduledmessages;

import java.util.ArrayList;
import java.util.stream.IntStream;

import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendApi;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.exceptions.MailerSendException;

/**
 * <p>ScheduledMessages class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class ScheduledMessages {

    private MailerSend apiObjectReference;
    
    private int pageFilter = 1;
    private int limitFilter = 25;
    private String domainIdFilter;
    private String statusFilter;
    
    /**
     * <p>Constructor for ScheduledMessages.</p>
     *
     * @param ref a {@link com.mailersend.sdk.MailerSend} object.
     */
    public ScheduledMessages(MailerSend ref) {
    	apiObjectReference = ref;
    }
    
    /**
     * <p>page.</p>
     *
     * @param page a int.
     * @return a {@link com.mailersend.sdk.scheduledmessages.ScheduledMessages} object.
     */
    public ScheduledMessages page(int page) {
    	pageFilter = page;
    	return this;
    }
    
    /**
     * <p>limit.</p>
     *
     * @param limit a int.
     * @return a {@link com.mailersend.sdk.scheduledmessages.ScheduledMessages} object.
     */
    public ScheduledMessages limit(int limit) {
    	limitFilter = limit;
    	return this;
    }
    
    /**
     * <p>domainId.</p>
     *
     * @param domainId a {@link java.lang.String} object.
     * @return a {@link com.mailersend.sdk.scheduledmessages.ScheduledMessages} object.
     */
    public ScheduledMessages domainId(String domainId) {
    	domainIdFilter = domainId;
    	return this;
    }
    
    /**
     * <p>status.</p>
     *
     * @param status a {@link java.lang.String} object.
     * @return a {@link com.mailersend.sdk.scheduledmessages.ScheduledMessages} object.
     */
    public ScheduledMessages status(String status) {
    	statusFilter = status;
    	return this;
    }
    
    /**
     * <p>getScheduledMessages.</p>
     *
     * @return a {@link com.mailersend.sdk.scheduledmessages.ScheduledMessagesList} object.
     * @throws com.mailersend.sdk.exceptions.MailerSendException if any.
     */
    public ScheduledMessagesList getScheduledMessages() throws MailerSendException {
    	String endpoint = "/message-schedules".concat(prepareParamsUrl());
    	
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        ScheduledMessagesList response = api.getRequest(endpoint, ScheduledMessagesList.class);
        
        for (ScheduledMessage m : response.scheduledMessages) {
        	m.postDeserialize();
        }
        
        return response;
    }
    
    /**
     * <p>getScheduledMessage.</p>
     *
     * @param messageId a {@link java.lang.String} object.
     * @return a {@link com.mailersend.sdk.scheduledmessages.ScheduledMessage} object.
     * @throws com.mailersend.sdk.exceptions.MailerSendException if any.
     */
    public ScheduledMessage getScheduledMessage(String messageId) throws MailerSendException {
    	String endpoint = "/message-schedules/".concat(messageId);
    	
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        SingleScheduledMessageResponse response = api.getRequest(endpoint, SingleScheduledMessageResponse.class);
        
        response.scheduledMessage.postDeserialize();
        
        return response.scheduledMessage;
    }
    
    
    /**
     * <p>deleteScheduledMessage.</p>
     *
     * @param messageId a {@link java.lang.String} object.
     * @return a boolean.
     * @throws com.mailersend.sdk.exceptions.MailerSendException if any.
     */
    public boolean deleteScheduledMessage(String messageId) throws MailerSendException {
    	String endpoint = "/message-schedules/".concat(messageId);
    	
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        MailerSendResponse response = api.deleteRequest(endpoint, MailerSendResponse.class);
        
        // return true if the response is 200, 204 or 202
        return IntStream.of(new int[] {200, 204, 202}).anyMatch(x -> x == response.responseStatusCode);
    }
    
    /**
     * Prepares the query part of the request url
     * @return
     */
    private String prepareParamsUrl() {
        
        // prepare the request parameters
        ArrayList<String> params = new ArrayList<String>();

        params.add("page=".concat(String.valueOf(pageFilter)));
        
        params.add("limit=".concat(String.valueOf(limitFilter)));
  
        if (domainIdFilter != null) {
        	
        	params.add("domain_id=".concat(domainIdFilter));
        }
        
        if (statusFilter != null) {
        	params.add("status=".concat(statusFilter));
        }
        
        String requestParams = "";
        for (int i = 0; i < params.size(); i++) {
            
            String attrSep = "&";
            
            if (i == 0) {
                
                attrSep = "?";
            }
            
            requestParams = requestParams.concat(attrSep).concat(params.get(i));
        }
        
        return requestParams;
    }
}
