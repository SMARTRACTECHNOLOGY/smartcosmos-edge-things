package net.smartcosmos.edge.things.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.domain.RestThingMetadataUpdateContainer;
import net.smartcosmos.edge.things.domain.local.things.RestThingUpdate;
import net.smartcosmos.edge.things.service.local.metadata.UpsertMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.UpdateThingRestService;
import net.smartcosmos.edge.things.utility.ResponseBuilderUtility;
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

    @Autowired
    public UpdateThingEdgeServiceDefault(
        EventSendingService eventSendingService, ConversionService conversionService,
        UpsertMetadataRestService upsertMetadataService, UpdateThingRestService updateThingService) {

        this.eventSendingService = eventSendingService;
        this.conversionService = conversionService;
        this.upsertMetadataService = upsertMetadataService;
        this.updateThingService = updateThingService;

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    public void update(DeferredResult<ResponseEntity> response, String type, String urn, Map<String, Object> requestBody, SmartCosmosUser user) {

        RestThingMetadataUpdateContainer container = conversionService.convert(requestBody, RestThingMetadataUpdateContainer.class);

        Map<String, Object> reducedMetadataMap = container.getMetadataRequestBody();
        RestThingUpdate thingUpdate = container.getThingRequestBody();

        if (thingUpdate == null && reducedMetadataMap.isEmpty()) {
            // if there is nothing to update, we return 400 Bad Request
            ResponseEntity nothingToUpdate = ResponseBuilderUtility.buildBadRequestResponse(1, "Nothing to update");
            response.setResult(nothingToUpdate);
            return;
        }

        if (thingUpdate != null) {
            ResponseEntity thingResponse = updateThingService.update(type, urn, thingUpdate, user);
            if (!thingResponse.getStatusCode()
                .is2xxSuccessful()) {
                // if there was a problem with the thing update, we return that
                response.setResult(thingResponse);
                return;
            }
        }

        if (!reducedMetadataMap.isEmpty()) {
            ResponseEntity metadataResponse = upsertMetadataService.upsert(type, urn, reducedMetadataMap, user);
            if (!metadataResponse.getStatusCode()
                .is2xxSuccessful()) {
                // if there was a problem with the metadata update, we return that
                response.setResult(metadataResponse);
                return;
            }
        }

        // usually we just return 204 No Content
        response.setResult(ResponseEntity.noContent()
                               .build());
    }
}
