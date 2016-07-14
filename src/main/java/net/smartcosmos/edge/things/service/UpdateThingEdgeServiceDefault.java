package net.smartcosmos.edge.things.service;

import java.util.Map;
import javax.inject.Inject;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.service.local.metadata.UpsertMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.UpdateThingRestService;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation for {@link net.smartcosmos.edge.things.service.UpdateThingEdgeService}
 */
@Service
public class UpdateThingEdgeServiceDefault implements UpdateThingEdgeService {

    private final EventSendingService eventSendingService;
    private final ConversionService conversionService;
    private final UpsertMetadataRestService upsertMetadataService;
    private final UpdateThingRestService updateThingService;

    @Inject
    public UpdateThingEdgeServiceDefault(
        EventSendingService eventSendingService, ConversionService conversionService, UpsertMetadataRestService upsertMetadataService,
        UpdateThingRestService updateThingService) {
        this.eventSendingService = eventSendingService;
        this.conversionService = conversionService;
        this.upsertMetadataService = upsertMetadataService;
        this.updateThingService = updateThingService;
    }

    @Override
    @Async
    public void update(DeferredResult<ResponseEntity> response, String type, String urn, Map<String, Object> requestBody, SmartCosmosUser user) {


    }
}
