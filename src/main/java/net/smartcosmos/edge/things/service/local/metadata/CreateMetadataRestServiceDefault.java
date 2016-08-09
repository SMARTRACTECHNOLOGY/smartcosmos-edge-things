package net.smartcosmos.edge.things.service.local.metadata;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import lombok.extern.slf4j.Slf4j;

import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestConnector;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation of the metadata service.
 */
@Slf4j
@Service
public class CreateMetadataRestServiceDefault implements CreateMetadataRestService {
    private final ConversionService conversionService;
    private final MetadataRestConnector restTemplate;

    @Inject
    public CreateMetadataRestServiceDefault(ConversionService conversionService, MetadataRestConnector restTemplate) {
        this.conversionService = conversionService;
        this.restTemplate = restTemplate;

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    public ResponseEntity<?> create(String ownerType, String ownerUrn, boolean force, Map<String, Object> metadataMap, SmartCosmosUser user) {
        try {
            return restTemplate.create(ownerType, ownerUrn, force, metadataMap);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }
}
