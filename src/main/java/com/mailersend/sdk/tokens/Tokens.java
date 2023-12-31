/*************************************************
 * MailerSend Java SDK
 * https://github.com/mailersend/mailersend-java
 * 
 * @author MailerSend <support@mailersend.com>
 * https://mailersend.com
 **************************************************/
package com.mailersend.sdk.tokens;

import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendApi;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.exceptions.MailerSendException;

/**
 * <p>Tokens class.</p>
 *
 * @author mailersend
 * @version $Id: $Id
 */
public class Tokens {

    private MailerSend apiObjectReference;
    
    private TokenAddBuilder addTokenBuilder;
    
    /**
     * <p>Constructor for Tokens.</p>
     *
     * @param ref a {@link com.mailersend.sdk.MailerSend} object.
     */
    public Tokens(MailerSend ref) {
        
        apiObjectReference = ref;
        
        addTokenBuilder = new TokenAddBuilder(ref);
    }
    
    
    /**
     * Returns the add token builder
     *
     * @return a {@link com.mailersend.sdk.tokens.TokenAddBuilder} object.
     */
    public TokenAddBuilder addBuilder() {
        
        return addTokenBuilder;
    }
    
    
    /**
     * Updates a token's status
     *
     * @param tokenId a {@link java.lang.String} object.
     * @param paused a boolean.
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.tokens.Token} object.
     */
    public Token updateToken(String tokenId, boolean paused) throws MailerSendException {
        
        String endpoint = "/token/".concat(tokenId).concat("/settings");
        
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        String json = "{\"status\":\"pause\"}";
        
        if (!paused) {
            
            json = "{\"status\":\"unpause\"}";
        }
        
        
        TokenResponse response = api.putRequest(endpoint, json, TokenResponse.class);
        
        response.token.postDeserialize();
        
        return response.token;
    }
    
        
    /**
     * Deletes a token
     *
     * @param tokenId a {@link java.lang.String} object.
     * @throws com.mailersend.sdk.exceptions.MailerSendException
     * @return a {@link com.mailersend.sdk.MailerSendResponse} object.
     */
    public MailerSendResponse deleteToken(String tokenId) throws MailerSendException {
        
        String endpoint = "/token/".concat(tokenId);
        
        MailerSendApi api = new MailerSendApi();
        api.setToken(apiObjectReference.getToken());
        
        MailerSendResponse response = api.deleteRequest(endpoint, MailerSendResponse.class);
        
        return response;
    }
    
}
