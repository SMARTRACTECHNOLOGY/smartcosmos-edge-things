package net.smartcosmos.edge.things.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * Utility class to provide OAuth 2 Access Tokens for requests to other services.
 */
@Component
@Slf4j
public class OAuth2TokenProvider {

    private static Map<String, OAuth2ClientContext> apiClientContexts = new ConcurrentHashMap<>();

    private final RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory;

    @Autowired
    public OAuth2TokenProvider(SpringClientFactory clientFactory) {

        ribbonClientHttpRequestFactory = new RibbonClientHttpRequestFactory(clientFactory);
    }

    /**
     * Gets the {@link OAuth2ClientContext} based on the the OAuth token in the incoming request from the authenticated user.
     *
     * @return the client context with a valid access token
     * @throws IllegalStateException if the access token can't be retrieved
     */
    public OAuth2ClientContext getRequestContextToken() throws IllegalStateException {

        OAuth2ClientContext requestContext = new DefaultOAuth2ClientContext();

        String oAuth2Token = getRequestContextOAuth2Token();
        if (StringUtils.isNotBlank(oAuth2Token)) {
            OAuth2AccessToken requestToken = new DefaultOAuth2AccessToken(oAuth2Token);
            requestContext.setAccessToken(requestToken);
            return requestContext;
        }
        throw new IllegalStateException("Can't access OAuth2 Client Context");
    }

    private static OAuth2ClientContext getApiClientContextForUser(String username) {

        Assert.isTrue(StringUtils.isNotBlank(username), "username may not be blank");

        if (apiClientContexts.containsKey(username)) {
            OAuth2ClientContext clientContext = apiClientContexts.get(username);
            if (clientContext != null && clientContext.getAccessToken() != null && !clientContext.getAccessToken().isExpired()) {
                return clientContext;
            }
        }

        OAuth2ClientContext defaultContext = new DefaultOAuth2ClientContext();
        apiClientContexts.put(username, defaultContext);

        return defaultContext;
    }

    private String getRequestContextOAuth2Token() {
        try {
            return String.valueOf(RequestContextHolder.currentRequestAttributes().getAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE,
                    RequestAttributes.SCOPE_REQUEST));
        }
        catch (Exception e) {
            return "";
        }
    }
}
