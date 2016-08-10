package net.smartcosmos.edge.things.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.domain.RestThingMetadataCreateContainer;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.service.local.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.CreateThingRestService;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation for {@link net.smartcosmos.edge.things.service.CreateThingEdgeService}
 */
@Service
public class CreateThingEdgeServiceDefault implements CreateThingEdgeService {

    private final EventSendingService eventSendingService;
    private final ConversionService conversionService;
    private final CreateMetadataRestService createMetadataService;
    private final CreateThingRestService createThingService;

    @Autowired
    public CreateThingEdgeServiceDefault(
        EventSendingService eventSendingService, ConversionService conversionService,
        CreateMetadataRestService createMetadataService, CreateThingRestService createThingService) {

        this.eventSendingService = eventSendingService;
        this.conversionService = conversionService;
        this.createMetadataService = createMetadataService;
        this.createThingService = createThingService;

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    public void create(DeferredResult<ResponseEntity> response, String type, Map<String, Object> metadataMap, Boolean force, SmartCosmosUser user) {

        RestThingMetadataCreateContainer container = conversionService.convert(metadataMap, RestThingMetadataCreateContainer.class);
        ResponseEntity thingResponse = createThingService.create(type, container.getThingRequestBody(), user);
        Map<String, Object> reducedMetadataMap = container.getMetadataRequestBody();

        if (thingResponse.getStatusCode()
                .is2xxSuccessful() && thingResponse.hasBody()
            && thingResponse.getBody() instanceof RestThingCreateResponseDto && !reducedMetadataMap.isEmpty()) {
            RestThingCreateResponseDto thingResponseBody = (RestThingCreateResponseDto) thingResponse.getBody();
            String urn = thingResponseBody.getUrn();

            ResponseEntity metadataResponse = createMetadataService.create(type, urn, force, reducedMetadataMap, user);

            if (!metadataResponse.getStatusCode()
                .is2xxSuccessful()) {
                // if there was a problem with the metadata creation, we return that
                response.setResult(metadataResponse);
                return;
            }
        }

        // usually we just return the Thing creation response
        response.setResult(thingResponse);
    }
}
