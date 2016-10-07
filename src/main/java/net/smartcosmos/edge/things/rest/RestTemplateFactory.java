package net.smartcosmos.edge.things.rest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Factory class creating new {@link RestTemplate} instances for calls to other services in the roundRock.
 */
@Component
@Slf4j
public class RestTemplateFactory {

    private final OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public RestTemplateFactory(OAuth2RestTemplate oAuth2RestTemplate) {

        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    /**
     * Get a new {@link RestTemplate} to execute requests as the user making the original incoming request.
     *
     * @return the REST template
     */
    public RestTemplate getRestTemplate() {

        oAuth2RestTemplate.getOAuth2ClientContext()
            .setAccessToken(getOAuth2TokenFromAuthentication());

        return oAuth2RestTemplate;
    }

    protected OAuth2AccessToken getOAuth2TokenFromAuthentication() {

        return new DefaultOAuth2AccessToken(((OAuth2AuthenticationDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getDetails()).getTokenValue());
    }
}
