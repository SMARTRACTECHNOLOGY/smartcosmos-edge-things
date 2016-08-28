package net.smartcosmos.edge.things.rest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.smartcosmos.edge.things.rest.errorhandler.ProxyOauth2ErrorHandler;

/**
 * Factory class creating new {@link RestTemplate} instances for calls to other services in the roundRock.
 */
@Component
@Slf4j
public class RestTemplateFactory {

    private final OAuth2ProtectedResourceDetails resourceDetails;
    private final RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory;

    private final OAuth2TokenProvider tokenProvider;
    private final ProxyOauth2ErrorHandler errorHandler;

    @Autowired
    public RestTemplateFactory(
        SpringClientFactory clientFactory,
        OAuth2ProtectedResourceDetails resourceDetails,
        OAuth2TokenProvider tokenProvider,
        ProxyOauth2ErrorHandler errorHandler) {

        this.ribbonClientHttpRequestFactory = new RibbonClientHttpRequestFactory(clientFactory);

        this.resourceDetails = resourceDetails;
        this.tokenProvider = tokenProvider;
        this.errorHandler = errorHandler;
    }

    /**
     * Get a new {@link RestTemplate} to execute requests as the user making the original incoming request.
     *
     * @return the REST template
     */
    public RestTemplate getRestTemplate() {

        OAuth2ClientContext context = tokenProvider.getRequestContextToken();
        return getRestTemplate(context);
    }

    private RestTemplate getRestTemplate(OAuth2ClientContext context) {

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, context);
        restTemplate.setRequestFactory(ribbonClientHttpRequestFactory);
        restTemplate.setErrorHandler(errorHandler);

        return restTemplate;
    }
}
