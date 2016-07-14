package net.smartcosmos.edge.things.service;

import javax.inject.Inject;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.service.local.metadata.DeleteMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.DeleteThingRestService;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation for {@Link net.smartcosmos.edge.things.service.DeleteThingEdgeService}
 */
@Service
public class DeleteThingEdgeServiceDefault implements DeleteThingEdgeService {

    private final EventSendingService eventSendingService;
    private final ConversionService conversionService;
    private final DeleteMetadataRestService deleteMetadataService;
    private final DeleteThingRestService deleteThingService;

    @Inject
    public DeleteThingEdgeServiceDefault(
        EventSendingService eventSendingService, ConversionService conversionService, DeleteMetadataRestService deleteMetadataService,
        DeleteThingRestService deleteThingService) {
        this.eventSendingService = eventSendingService;
        this.conversionService = conversionService;
        this.deleteMetadataService = deleteMetadataService;
        this.deleteThingService = deleteThingService;
    }

    @Override
    @Async
    public void delete(
        DeferredResult<ResponseEntity> response, String type, String urn, SmartCosmosUser user) {

        ResponseEntity thingResponse = deleteThingService.delete(type, urn, user);

        if (thingResponse.getStatusCode().is2xxSuccessful()) {
            ResponseEntity metadataResponse = deleteMetadataService.delete(type, urn, user);

            if ( !metadataResponse.getStatusCode().is2xxSuccessful() && HttpStatus.NOT_FOUND != metadataResponse.getStatusCode()) {
                // if there was a problem with the metadata deletion, we return that (but 404 Not Found is okay)
                response.setResult(metadataResponse);
                return;
            }
        }

        response.setResult(thingResponse);
    }
}
