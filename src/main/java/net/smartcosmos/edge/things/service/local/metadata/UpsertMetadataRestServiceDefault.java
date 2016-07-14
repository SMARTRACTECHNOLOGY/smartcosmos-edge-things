package net.smartcosmos.edge.things.service.local.metadata;

import java.util.Map;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestTemplate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST Metadata endpoint to upsert metadata.
 */
@Slf4j
@Service
public class UpsertMetadataRestServiceDefault implements UpsertMetadataRestService {

    private final MetadataRestTemplate restTemplate;

    @Inject
    public UpsertMetadataRestServiceDefault(MetadataRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> upsert(String ownerType, String ownerUrn, Map<String, Object> metadataMap, SmartCosmosUser user) {
        try {
            return restTemplate.upsert(ownerType, ownerUrn, metadataMap);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }
}
