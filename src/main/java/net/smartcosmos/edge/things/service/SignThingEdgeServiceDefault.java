package net.smartcosmos.edge.things.service;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.domain.RestThingMetadataUpdateContainer;
import net.smartcosmos.edge.things.domain.things.RestThingSignResponseDto;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.AuthRequestFactory;
import net.smartcosmos.edge.things.service.metadata.UpsertMetadataRestService;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation for {@link UpdateThingEdgeService}
 */
@Service
@Slf4j
public class SignThingEdgeServiceDefault implements SignThingEdgeService {

    private final ConversionService conversionService;
    private final GetThingEdgeService getThingService;
    private final RestTemplateFactory restTemplateFactory;
    private final AuthRequestFactory authRequestFactory;
    private final UpsertMetadataRestService upsertMetadataService;

    private final ObjectMapper objectMapper;

    @Autowired
    public SignThingEdgeServiceDefault(ConversionService conversionService,
                                       GetThingEdgeService getThingService,
                                       RestTemplateFactory restTemplateFactory,
                                       AuthRequestFactory authRequestFactory,
                                       UpsertMetadataRestService upsertMetadataService,
                                       ObjectMapper objectMapper) {
        this.conversionService = conversionService;
        this.getThingService = getThingService;
        this.restTemplateFactory = restTemplateFactory;
        this.authRequestFactory = authRequestFactory;
        this.upsertMetadataService = upsertMetadataService;
        this.objectMapper = objectMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity sign(String type, String urn, SmartCosmosUser user) {
        try {
            // fetch thing
            ResponseEntity thingResponse = getThingService.getByTypeAndUrn(type, urn, Collections.emptySet(), user);
            Map<String, Object> thingResponseMap = conversionService.convert(thingResponse.getBody(), Map.class);
            thingResponseMap.remove("jwt");

            // get signed jwt (of thing) from auth-server
            String thingJson = objectMapper.writeValueAsString(thingResponseMap);
            RequestEntity<Map<String, Object>> requestEntity = authRequestFactory.createSignRequest(thingJson);
            ResponseEntity signResponse = restTemplateFactory.getRestTemplate()
                .exchange(requestEntity, RestThingSignResponseDto.class);

            // save jwt to thing metadata
            if (signResponse.getStatusCode().is2xxSuccessful()) {
                String jwt = (String) conversionService.convert(signResponse.getBody(), Map.class).get("jwt");
                RestThingMetadataUpdateContainer container = conversionService.convert(
                    Collections.singletonMap("jwt", jwt),
                    RestThingMetadataUpdateContainer.class);
                Map<String, Object> metadata = container.getMetadataRequestBody();
                upsertMetadataService.upsert(type, urn, metadata, user);
            }

            // return jwt
            return signResponse;

        } catch (JsonProcessingException e) {
            log.warn(signLogMessage(type, urn, user, e.getMessage()));
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
        }
    }

    private String signLogMessage(String type, String urn, SmartCosmosUser user, String message) {
        return String.format("Sign request for Thing with type '%s' and urn '%s' by user '%s' failed: %s",
                             type,
                             urn,
                             user,
                             message);
    }
}
