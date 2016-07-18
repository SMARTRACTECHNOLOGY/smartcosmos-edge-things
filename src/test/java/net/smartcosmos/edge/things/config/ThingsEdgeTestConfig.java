package net.smartcosmos.edge.things.config;

import org.mockito.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;

import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestTemplate;
import net.smartcosmos.edge.things.rest.template.thing.ThingRestTemplate;

@Configuration
public class ThingsEdgeTestConfig {

    @Bean
    public ThingRestTemplate thingRestTemplate() {
        return Mockito.mock(ThingRestTemplate.class);
    }

    @Bean
    public MetadataRestTemplate metadataRestTemplate() {
        return Mockito.mock(MetadataRestTemplate.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return Mockito.mock(RestTemplate.class);
    }

    @Bean
    public OAuth2ClientContext oAuth2ClientContext() {
        return Mockito.mock(OAuth2ClientContext.class);
    }

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        return Mockito.mock(OAuth2ProtectedResourceDetails.class);
    }
}
