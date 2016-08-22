package net.smartcosmos.edge.things.service;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.domain.RestThingMetadataCreateContainer;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.service.local.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.CreateThingRestService;
import net.smartcosmos.security.user.SmartCosmosUser;

import static net.smartcosmos.edge.things.utility.ResponseBuilderUtility.buildForwardingResponse;

/**
 * Default implementation for {@link net.smartcosmos.edge.things.service.CreateThingEdgeService}
 */
@Service
@Slf4j
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
    }

    @Override
    public void create(DeferredResult<ResponseEntity> response, String type, Map<String, Object> metadataMap, Boolean force, SmartCosmosUser user) {

        try {
            response.setResult(createWorker(type, metadataMap, force, user));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            response.setErrorResult(e);
        }
    }

    protected ResponseEntity<?> createWorker(String type, Map<String, Object> metadataMap, Boolean force, SmartCosmosUser user) {

        RestThingMetadataCreateContainer container = conversionService.convert(metadataMap, RestThingMetadataCreateContainer.class);
        ResponseEntity thingResponse = createThingService.create(type, container.getThingRequestBody(), user);
        Map<String, Object> reducedMetadataMap = container.getMetadataRequestBody();

        HttpStatus status = thingResponse.getStatusCode();
        if ((status.is2xxSuccessful() || (force && HttpStatus.CONFLICT.equals(status)))
            && thingResponse.hasBody()
            && thingResponse.getBody() instanceof RestThingCreateResponseDto
            && !reducedMetadataMap.isEmpty()) {
            RestThingCreateResponseDto thingResponseBody = (RestThingCreateResponseDto) thingResponse.getBody();
            String urn = thingResponseBody.getUrn();

            ResponseEntity metadataResponse = createMetadataService.create(type, urn, force, reducedMetadataMap, user);

            if (!metadataResponse.getStatusCode()
                .is2xxSuccessful()) {
                // if there was a problem with the metadata creation, we return that
                return buildForwardingResponse(metadataResponse);
            }
        }

        // usually we just return the Thing creation response
        return buildForwardingResponse(thingResponse);
    }
}
