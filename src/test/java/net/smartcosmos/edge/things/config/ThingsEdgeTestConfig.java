package net.smartcosmos.edge.things.config;

import org.mockito.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestConnector;
import net.smartcosmos.edge.things.rest.template.thing.ThingRestConnector;

@Configuration
public class ThingsEdgeTestConfig {

    @Bean
    public ThingRestConnector thingRestConnector() {
        return Mockito.mock(ThingRestConnector.class);
    }

    @Bean
    public MetadataRestConnector metadataRestConnector() {
        return Mockito.mock(MetadataRestConnector.class);
    }

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        return Mockito.mock(OAuth2ProtectedResourceDetails.class);
    }
}
