package net.smartcosmos.edge.things.service;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.domain.RestThingMetadataUpdateContainer;
import net.smartcosmos.edge.things.domain.things.RestThingUpdate;
import net.smartcosmos.edge.things.service.metadata.UpsertMetadataRestService;
import net.smartcosmos.edge.things.service.things.UpdateThingRestService;
import net.smartcosmos.security.user.SmartCosmosUser;

import static net.smartcosmos.edge.things.util.ResponseBuilderUtility.buildBadRequestResponse;
import static net.smartcosmos.edge.things.util.ResponseBuilderUtility.buildForwardingResponse;

/**
 * Default implementation for {@link net.smartcosmos.edge.things.service.UpdateThingEdgeService}
 */
@Service
@Slf4j
public class UpdateThingEdgeServiceDefault implements UpdateThingEdgeService {

    private final ConversionService conversionService;
    private final UpsertMetadataRestService upsertMetadataService;
    private final UpdateThingRestService updateThingService;

    @Autowired
    public UpdateThingEdgeServiceDefault(
        ConversionService conversionService, UpsertMetadataRestService upsertMetadataService, UpdateThingRestService updateThingService) {

        this.conversionService = conversionService;
        this.upsertMetadataService = upsertMetadataService;
        this.updateThingService = updateThingService;
    }

    @Override
    public void update(DeferredResult<ResponseEntity> response, String type, String urn, Map<String, Object> requestBody, SmartCosmosUser user) {

        try {
            response.setResult(updateWorker(type, urn, requestBody, user));
        } catch (Exception e) {
            log.error(updateByTypeAndUrnLogMessage(type, urn, user, e.toString(), requestBody.toString()));
            log.debug(updateByTypeAndUrnLogMessage(type, urn, user, e.toString(), requestBody.toString()), e);
            response.setErrorResult(e);
        }
    }

    protected ResponseEntity<?> updateWorker(String type, String urn, Map<String, Object> requestBody, SmartCosmosUser user) {

        RestThingMetadataUpdateContainer container = conversionService.convert(requestBody, RestThingMetadataUpdateContainer.class);

        Map<String, Object> reducedMetadataMap = container.getMetadataRequestBody();
        RestThingUpdate thingUpdate = container.getThingRequestBody();

        if (thingUpdate == null && reducedMetadataMap.isEmpty()) {
            // if there is nothing to update, we return 400 Bad Request
            log.warn(updateByTypeAndUrnLogMessage(type, urn, user, "Nothing to update", requestBody.toString()));
            return buildBadRequestResponse(1, "Nothing to update");
        }

        if (thingUpdate != null) {
            ResponseEntity thingResponse = updateThingService.update(type, urn, thingUpdate, user);
            if (!thingResponse.getStatusCode()
                .is2xxSuccessful()) {
                // if there was a problem with the thing update, we return that
                log.warn(updateByTypeAndUrnLogMessage(type, urn, user, "update core thing = " + thingResponse.toString(), requestBody.toString()));
                return buildForwardingResponse(thingResponse);
            }
        }

        if (!reducedMetadataMap.isEmpty()) {
            ResponseEntity metadataResponse = upsertMetadataService.upsert(type, urn, reducedMetadataMap, user);
            if (!metadataResponse.getStatusCode()
                .is2xxSuccessful()) {
                // if there was a problem with the metadata update, we return that
                log.warn(updateByTypeAndUrnLogMessage(type, urn, user, "update core metadata = " + metadataResponse.toString(), requestBody.toString()));
                return buildForwardingResponse(metadataResponse);
            }
        }

        // usually we just return 204 No Content
        return ResponseEntity.noContent()
            .build();
    }

    private String updateByTypeAndUrnLogMessage(String type, String urn, SmartCosmosUser user, String message, String requestBody) {
        return String.format("Update request for Thing with type '%s' and urn '%s' by user '%s' failed: %s\nRequest: %s",
                             type,
                             urn,
                             user,
                             message,
                             requestBody);
    }
}
