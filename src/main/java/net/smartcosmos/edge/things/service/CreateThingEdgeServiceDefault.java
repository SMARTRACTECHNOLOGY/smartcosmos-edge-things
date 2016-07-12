package net.smartcosmos.edge.things.service;

import java.util.Map;
import javax.inject.Inject;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation for {@Link net.smartcosmos.edge.things.service.CreateThingEdgeService}
 */
@Service
public class CreateThingEdgeServiceDefault implements CreateThingEdgeService {
    private final EventSendingService eventSendingService;
    private final ConversionService conversionService;
    private final CreateMetadataRestService createMetadataService;
    private final CreateThingRestService createThingRestService;

    @Inject

    public CreateThingEdgeServiceDefault(
        EventSendingService eventSendingService, ConversionService conversionService, CreateMetadataRestService createMetadataService,
        CreateThingRestService createThingRestService) {
        this.eventSendingService = eventSendingService;
        this.conversionService = conversionService;
        this.createMetadataService = createMetadataService;
        this.createThingRestService = createThingRestService;
    }

    @Override
    @Async
    public void create(
        DeferredResult<ResponseEntity> response, String type, String urn, Map<String, Object> metadataMap, Boolean force, SmartCosmosUser user) {
        // lookup and call things REST service
        // if all goes well
        //     lookup and call metadata REST service
    }
}
