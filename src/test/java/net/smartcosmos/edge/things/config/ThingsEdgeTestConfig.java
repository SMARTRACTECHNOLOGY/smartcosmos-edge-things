package net.smartcosmos.edge.things.config;

import org.mockito.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.client.RestTemplate;

import net.smartcosmos.edge.things.resource.GetThingResource;
import net.smartcosmos.edge.things.service.CreateThingEdgeService;
import net.smartcosmos.edge.things.service.GetThingEdgeService;
import net.smartcosmos.edge.things.service.local.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.CreateThingRestService;

@Configuration
public class ThingsEdgeTestConfig {

    @Bean
    public CreateThingEdgeService createThingEdgeService() {
        return Mockito.mock(CreateThingEdgeService.class);
    }

    @Bean
    public GetThingResource getThingResource() {
        return Mockito.mock(GetThingResource.class);
    }

    @Bean
    public GetThingEdgeService getThingEdgeService() {
        return Mockito.mock(GetThingEdgeService.class);
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

    @Bean
    public CreateMetadataRestService createMetadataRestServiceDefault() {
        return Mockito.mock(CreateMetadataRestService.class);
    }

    @Bean
    public CreateThingRestService createThingRestServiceDefault() {
        return Mockito.mock(CreateThingRestService.class);
    }
}
