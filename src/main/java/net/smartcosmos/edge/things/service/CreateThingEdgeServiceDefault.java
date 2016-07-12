package net.smartcosmos.edge.things.service;

import java.util.Map;
import javax.inject.Inject;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.edge.things.service.local.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.CreateThingRestService;
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
    public void create(DeferredResult<ResponseEntity> response, String type, Map<String, Object> metadataMap, Boolean force, SmartCosmosUser user) {

        // when the conversion is done, all fields consumable by the Things local service are removed, thus the remaining fields are metadata
        RestThingCreate thingCreate = conversionService.convert(metadataMap, RestThingCreate.class);
        ResponseEntity thingResponse = createThingRestService.create(type, thingCreate);

        if (!thingResponse.getStatusCode().is2xxSuccessful()) {
            // if there was an error creating the Thing, that's the error to return
            response.setResult(thingResponse);
        }

        // TODO: Create Metadata
        // - Get URN from Response
        // - Send request via metadata service class

        response.setResult(thingResponse);
    }
}
