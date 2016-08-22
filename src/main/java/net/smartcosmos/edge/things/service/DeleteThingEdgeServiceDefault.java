package net.smartcosmos.edge.things.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.service.local.metadata.DeleteMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.DeleteThingRestService;
import net.smartcosmos.security.user.SmartCosmosUser;

import static net.smartcosmos.edge.things.utility.ResponseBuilderUtility.buildForwardingResponse;

/**
 * Default implementation for {@link net.smartcosmos.edge.things.service.DeleteThingEdgeService}
 */
@Service
@Slf4j
public class DeleteThingEdgeServiceDefault implements DeleteThingEdgeService {

    private final EventSendingService eventSendingService;
    private final ConversionService conversionService;
    private final DeleteMetadataRestService deleteMetadataService;
    private final DeleteThingRestService deleteThingService;

    @Autowired
    public DeleteThingEdgeServiceDefault(
        EventSendingService eventSendingService, ConversionService conversionService,
        DeleteMetadataRestService deleteMetadataService, DeleteThingRestService deleteThingService) {

        this.eventSendingService = eventSendingService;
        this.conversionService = conversionService;
        this.deleteMetadataService = deleteMetadataService;
        this.deleteThingService = deleteThingService;
    }

    @Override
    public void delete(DeferredResult<ResponseEntity> response, String type, String urn, SmartCosmosUser user) {

        try {
            response.setResult(deleteWorker(type, urn, user));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            response.setErrorResult(e);
        }
    }

    protected ResponseEntity<?> deleteWorker(String type, String urn, SmartCosmosUser user) {

        ResponseEntity thingResponse = deleteThingService.delete(type, urn, user);

        if (thingResponse.getStatusCode()
            .is2xxSuccessful()) {
            ResponseEntity metadataResponse = deleteMetadataService.delete(type, urn, user);

            if (!metadataResponse.getStatusCode()
                .is2xxSuccessful() && HttpStatus.NOT_FOUND != metadataResponse.getStatusCode()) {
                // if there was a problem with the metadata deletion, we return that (but 404 Not Found is okay)
                return buildForwardingResponse(metadataResponse);
            }
        }

        return buildForwardingResponse(thingResponse);
    }
}
