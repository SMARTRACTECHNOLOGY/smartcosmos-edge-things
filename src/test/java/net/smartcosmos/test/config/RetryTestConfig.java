package net.smartcosmos.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import net.smartcosmos.edge.things.service.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.metadata.DeleteMetadataRestService;
import net.smartcosmos.edge.things.service.metadata.GetMetadataRestService;
import net.smartcosmos.edge.things.service.metadata.UpsertMetadataRestService;
import net.smartcosmos.edge.things.service.things.CreateThingRestService;
import net.smartcosmos.edge.things.service.things.DeleteThingRestService;
import net.smartcosmos.edge.things.service.things.GetThingRestService;
import net.smartcosmos.edge.things.service.things.UpdateThingRestService;

import static org.mockito.Mockito.mock;

public class RetryTestConfig {

    // region Thing services

    @Primary
    @Bean
    public CreateThingRestService createThingRestService() {

        return mock(CreateThingRestService.class);
    }

    @Primary
    @Bean
    public DeleteThingRestService deleteThingRestService() {

        return mock(DeleteThingRestService.class);
    }

    @Primary
    @Bean
    public GetThingRestService getThingRestService() {

        return mock(GetThingRestService.class);
    }

    @Primary
    @Bean
    public UpdateThingRestService updateThingRestService() {

        return mock(UpdateThingRestService.class);
    }

    // endregion

    // region Metadata services

    @Primary
    @Bean
    public CreateMetadataRestService createMetadataRestService() {

        return mock(CreateMetadataRestService.class);
    }

    @Primary
    @Bean
    public DeleteMetadataRestService deleteMetadataRestService() {

        return mock(DeleteMetadataRestService.class);
    }

    @Primary
    @Bean
    public GetMetadataRestService getMetadataRestService() {

        return mock(GetMetadataRestService.class);
    }

    @Primary
    @Bean
    public UpsertMetadataRestService upsertMetadataRestService() {

        return mock(UpsertMetadataRestService.class);
    }

    // endregion
}
