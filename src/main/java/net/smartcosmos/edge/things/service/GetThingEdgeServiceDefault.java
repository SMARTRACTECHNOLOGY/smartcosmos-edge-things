package net.smartcosmos.edge.things.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.domain.local.things.RestThingResponse;
import net.smartcosmos.edge.things.service.local.metadata.GetMetadataRestService;
import net.smartcosmos.edge.things.service.local.things.GetThingRestService;
import net.smartcosmos.security.user.SmartCosmosUser;

@Service
public class GetThingEdgeServiceDefault implements GetThingEdgeService {

    private final EventSendingService eventSendingService;
    private final ConversionService conversionService;
    private final GetMetadataRestService getMetadataService;
    private final GetThingRestService getThingService;


    @Autowired
    public GetThingEdgeServiceDefault(EventSendingService eventSendingService,
                                      ConversionService conversionService,
                                      GetMetadataRestService getMetadataService,
                                      GetThingRestService getThingService) {
        this.eventSendingService = eventSendingService;
        this.conversionService = conversionService;
        this.getMetadataService = getMetadataService;
        this.getThingService = getThingService;
    }

    @Override
    public ResponseEntity<?> getByTypeAndUrn(String type, String urn, Set<String> metadataKeys, SmartCosmosUser user) {

        ResponseEntity thingResponse = getThingService.findByTypeAndUrn(type, urn, user);
        if (!thingResponse.getStatusCode().is2xxSuccessful()) {
            return thingResponse;
        }

        Map<String, Object> resultMap = new LinkedHashMap<>();

        if (thingResponse.hasBody() && thingResponse.getBody() instanceof RestThingResponse) {
            @SuppressWarnings("unchecked")
            Map<String, Object> thingResponseMap = conversionService.convert(thingResponse.getBody(), Map.class);
            resultMap.putAll(thingResponseMap);
        }

        ResponseEntity metadataResponse = getMetadataService.findByOwner(type, urn, metadataKeys, user);
        HttpStatus metadataHttpStatus = metadataResponse.getStatusCode();
        if (!metadataHttpStatus.is2xxSuccessful() && !HttpStatus.NOT_FOUND.equals(metadataHttpStatus)) {
            return metadataResponse;
        }

        if (metadataHttpStatus.is2xxSuccessful()
            && metadataResponse.hasBody() && metadataResponse.getBody() instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> metadaResponseMap = (Map<String, Object>) metadataResponse.getBody();
            resultMap.putAll(metadaResponseMap);
        }

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(resultMap);
    }

    @Override
    public ResponseEntity<?> getAll(Set<String> metadataKeys, Integer page, Integer size, String sortOrder, String sortBy, SmartCosmosUser user) {
        return null;
    }
}
