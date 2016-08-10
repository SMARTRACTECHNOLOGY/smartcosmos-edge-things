package net.smartcosmos.edge.things.service.local.metadata;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestConnector;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST Metadata endpoint to upsert metadata.
 */
@Slf4j
@Service
public class UpsertMetadataRestServiceDefault implements UpsertMetadataRestService {

    private final MetadataRestConnector restTemplate;

    @Autowired
    public UpsertMetadataRestServiceDefault(MetadataRestConnector restTemplate) {

        this.restTemplate = restTemplate;

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    public ResponseEntity<?> upsert(String ownerType, String ownerUrn, Map<String, Object> metadataMap, SmartCosmosUser user) {

        try {
            return restTemplate.upsert(ownerType, ownerUrn, metadataMap);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity.status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }
}
