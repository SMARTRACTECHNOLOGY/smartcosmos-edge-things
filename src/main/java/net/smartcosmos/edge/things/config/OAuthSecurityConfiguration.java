package net.smartcosmos.edge.things.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import net.smartcosmos.edge.things.rest.errorhandler.ProxyOauth2ErrorHandler;

@Configuration
@EnableOAuth2Client
public class OAuthSecurityConfiguration {

    @Bean
    @Autowired
    public OAuth2RestTemplate oAuth2RestTemplate(
        OAuth2ProtectedResourceDetails resourceDetails,
        SpringClientFactory clientFactory,
        ProxyOauth2ErrorHandler errorHandler) {

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        restTemplate.setRequestFactory(new RibbonClientHttpRequestFactory(clientFactory));
        restTemplate.setErrorHandler(errorHandler);

        return restTemplate;
    }
}
