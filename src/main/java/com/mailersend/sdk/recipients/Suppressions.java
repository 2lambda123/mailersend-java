/*************************************************
 * MailerSend Java SDK
 * https://github.com/mailersend/mailersend-java
 * 
 * @author MailerSend <support@mailersend.com>
 * https://mailersend.com
 **************************************************/
package com.mailersend.sdk.recipients;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendApi;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.exceptions.MailerSendException;
import com.mailersend.sdk.util.JsonSerializationDeserializationStrategy;

/**
 * <p>Suppressions class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class Suppressions {
    
    private MailerSend apiObjectReference;
    
    private int pageFilter = 1;
    private int limitFilter = 25;
    private String domainIdFilter = null;
    
    private SuppressionAddBuilder addBuilder;
    
    
    /**
     * Do not initialize directly. This should only be accessed from MailerSend.analytics
     *
     * @param ref a {@link com.mailersend.sdk.MailerSend} object.
     */
    public Suppressions(MailerSend ref) {
        
        apiObjectReference = ref;
        addBuilder = new SuppressionAddBuilder(ref);
    }
    
    
    /**
     * Get the suppression add builder
     *
     * @return a {@link com.mailersend.sdk.recipients.SuppressionAddBuilder} object.
     */
    public SuppressionAddBuilder addBuilder() {
        
        return addBuilder;
    }
    
    
    /**
     * Set the page of the request
     *
     * @param page a int.
     * @return a {@link com.mailersend.sdk.recipients.Suppressions} object.
     */
    public Suppressions page(int page) {
        
        pageFilter = page;
        
        return this;
    }
    
    
    /**
     * Set the results limit (10 - 100)
     *
     * @param limit a int.
     * @return a {@link com.mailersend.sdk.recipients.Suppressions} object.
     */
    public Suppressions limit(int limit) {
        
        limitFilter = limit;
        
        return this;
    }
    
    
    /**
     * Set the domain ID
     *
     * @param domainId a {@link java.lang.String} object.
     * @return a {@link com.mailersend.sdk.recipients.Suppressions} object.
     */
    public Suppressions domainId(String domainId) {
        
        domainIdFilter = domainId;
        
        return this;
    }
    
    
    /**
     * Gets the recipients in a blocklist of an account or domain
     *
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.recipients.BlocklistListResponse} object.
     */
    public BlocklistListResponse getBlocklist() throws MailerSendException {
        
        String endpoint = "/suppressions/blocklist".concat(prepareParamsUrl());
        
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        BlocklistListResponse response = api.getRequest(endpoint, BlocklistListResponse.class);
        
        return response;
    }
    
    
    /**
     * Gets the recipients that hard bounced
     *
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.recipients.SuppressionList} object.
     */
    public SuppressionList getHardBounces() throws MailerSendException {
        
        String endpoint = "/suppressions/hard-bounces".concat(prepareParamsUrl());
        
        return this.getSuppressionList(endpoint);
    }
    
    
    /**
     * Gets the spam complaints
     *
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.recipients.SuppressionList} object.
     */
    public SuppressionList getSpamComplaints() throws MailerSendException {
        
        String endpoint = "/suppressions/spam-complaints".concat(prepareParamsUrl());
        
        return this.getSuppressionList(endpoint);
    }
    
    
    /**
     * Gets the unsubscribes
     *
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.recipients.SuppressionList} object.
     */
    public SuppressionList getUnsubscribes() throws MailerSendException {
        
        String endpoint = "/suppressions/unsubscribes".concat(prepareParamsUrl());
        
        return this.getSuppressionList(endpoint);
    }
    
    
    /**
     * Used to retrieve similar responses from multiple endpoints
     * @return
     * @throws MailerSendException
     */
    private SuppressionList getSuppressionList(String endpoint) throws MailerSendException {
        
        
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        SuppressionList response = api.getRequest(endpoint, SuppressionList.class);
        
        return response;
    }
    
    
    /**
     * Deletes the items with the given id from the blocklist
     *
     * @param ids an array of {@link java.lang.String} objects.
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteBlocklistItems(String[] ids) throws MailerSendException {
        
        return this.deleteSuppressionListItems("/suppressions/blocklist", ids);
    }
    
    
    /**
     * Deletes all items from the blocklist
     *
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteBlocklistAllItems() throws MailerSendException {
        
        return this.deleteSuppressionListAllItems("/suppressions/blocklist");
    }
    
    
    /**
     * Deletes the items with the given id from the hard bounces suppression list
     *
     * @param ids an array of {@link java.lang.String} objects.
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteHardBouncesItems(String[] ids) throws MailerSendException {
        
        return this.deleteSuppressionListItems("/suppressions/hard-bounces", ids);
    }
    
    
    /**
     * Deletes all items from the hard bounces suppression list
     *
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteHardBouncesAllItems() throws MailerSendException {
        
        return this.deleteSuppressionListAllItems("/suppressions/hard-bounces");
    }
    
    
    /**
     * Deletes the items with the given id from the spam complaints suppression list
     *
     * @param ids an array of {@link java.lang.String} objects.
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteSpamComplaintsItems(String[] ids) throws MailerSendException {
        
        return this.deleteSuppressionListItems("/suppressions/spam-complaints", ids);
    }
    
    
    /**
     * Deletes all items from the spam complaints suppression list
     *
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteSpamComplaintsAllItems() throws MailerSendException {
        
        return this.deleteSuppressionListAllItems("/suppressions/spam-complaints");
    }
    
    
    /**
     * Deletes the items with the given id from the unsubscribes suppression list
     *
     * @param ids an array of {@link java.lang.String} objects.
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteUnsubscribesItems(String[] ids) throws MailerSendException {
        
        return this.deleteSuppressionListItems("/suppressions/unsubscribres", ids);
    }
    
    
    /**
     * Deletes all items from the unsubscribes suppression list
     *
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteUnsubscribesAllItems() throws MailerSendException {
        
        return this.deleteSuppressionListAllItems("/suppressions/unsubscribes");
    }
    
    
    /**
     * Deletes the items with the given id from the suppression list with the given endpoint
     * @param ids
     * @return
     * @throws MailerSendException
     */
    private MailerSendResponse deleteSuppressionListItems(String endpoint, String[] ids) throws MailerSendException {
        
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new JsonSerializationDeserializationStrategy(false))
                .addDeserializationExclusionStrategy(new JsonSerializationDeserializationStrategy(true))
                .create();
        
        String requestBody = gson.toJson(ids);
        
        requestBody = "{\"ids\":".concat(requestBody); 
        
        if (domainIdFilter != null) {
        	
        	requestBody = requestBody.concat(",\"domain_id\":\"".concat(domainIdFilter).concat("\""));
        }
        
        requestBody = requestBody.concat("}");
        
        MailerSendResponse response = api.deleteRequest(endpoint, requestBody, MailerSendResponse.class);
        
        return response;
    }
    
    
    /**
     * Deletes all items from the suppression list with the given endpoint
     * @return
     * @throws MailerSendException
     */
    private MailerSendResponse deleteSuppressionListAllItems(String endpoint) throws MailerSendException {
        
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        String requestBody = "{\"all\":true";
        
        if (domainIdFilter != null) {
        	
        	requestBody = requestBody.concat(",\"domain_id\":\"".concat(domainIdFilter).concat("\""));
        }
        
        requestBody = requestBody.concat("}");
        
        MailerSendResponse response = api.deleteRequest(endpoint, requestBody, MailerSendResponse.class);
        
        return response;
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
