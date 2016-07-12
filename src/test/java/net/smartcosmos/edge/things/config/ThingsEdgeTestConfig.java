package net.smartcosmos.edge.things.config;

import org.mockito.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.smartcosmos.edge.things.service.local.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.CreateThingEdgeService;
import net.smartcosmos.edge.things.service.local.things.CreateThingRestService;

@Configuration
public class ThingsEdgeTestConfig {
    @Bean
    public CreateThingEdgeService createThingServiceDefault() {
        return Mockito.mock(CreateThingEdgeService.class);
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
