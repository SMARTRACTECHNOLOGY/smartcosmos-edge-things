package net.smartcosmos.edge.things.service;

import net.smartcosmos.edge.things.domain.RestThingMetadataCreateContainer;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.service.local.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.CreateThingRestService;
import net.smartcosmos.security.user.SmartCosmosUser;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import javax.inject.Inject;
import java.util.Map;

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
        RestThingMetadataCreateContainer container = conversionService.convert(metadataMap, RestThingMetadataCreateContainer.class);
        ResponseEntity thingResponse = createThingRestService.create(type, container.getThingRequestBody(), user);

        if (thingResponse.getStatusCode().is2xxSuccessful()
            && thingResponse.hasBody() && thingResponse.getBody() instanceof RestThingCreateResponseDto
            && !metadataMap.isEmpty()) {
            RestThingCreateResponseDto thingResponseBody = (RestThingCreateResponseDto) thingResponse.getBody();
            String urn = thingResponseBody.getUrn();

            ResponseEntity metadataResponse = createMetadataService.create(type, urn, force, container.getMetadataRequestBody(), user);

            if (!metadataResponse.getStatusCode().is2xxSuccessful()) {
                // if there was a problem with the metadata creation, we return that
                response.setResult(metadataResponse);
                return;
            }
        }

        // usually we just return the Thing creation response
        response.setResult(thingResponse);
    }
}
