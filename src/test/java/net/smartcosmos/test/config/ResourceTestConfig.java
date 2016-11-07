package net.smartcosmos.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;

import static org.mockito.Mockito.mock;

@Configuration
public class ResourceTestConfig {

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {

        return mock(OAuth2ProtectedResourceDetails.class);
    }

    @Bean
    public RestTemplateFactory restTemplateFactory() {

        return mock(RestTemplateFactory.class);
    }

    @Bean
    public ThingRequestFactory thingRequestFactory() {

        return mock(ThingRequestFactory.class);
    }

    @Bean
    public MetadataRequestFactory metadataRequestFactory() {

        return mock(MetadataRequestFactory.class);
    }
}
