package net.smartcosmos.edge.things.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestTemplate;
import net.smartcosmos.edge.things.rest.template.thing.ThingRestTemplate;

@Configuration
@EnableConfigurationProperties({ SmartCosmosEdgeThingsProperties.class })
public class ThingsEdgeConfiguration {

    @Autowired
    private SmartCosmosEdgeThingsProperties edgeThingsProperties;

    @Bean
    ThingRestTemplate thingRestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details,
            SpringClientFactory clientFactory) {

        RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory = new RibbonClientHttpRequestFactory(clientFactory);
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(details, oauth2ClientContext);

        restTemplate.setRequestFactory(ribbonClientHttpRequestFactory);

        return new ThingRestTemplate(restTemplate, edgeThingsProperties.getLocal().getThings());
    }

    @Bean
    MetadataRestTemplate metadataRestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details,
            SpringClientFactory clientFactory) {

        RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory = new RibbonClientHttpRequestFactory(clientFactory);
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(details, oauth2ClientContext);

        restTemplate.setRequestFactory(ribbonClientHttpRequestFactory);

        return new MetadataRestTemplate(restTemplate, edgeThingsProperties.getLocal().getMetadata());
    }

}
