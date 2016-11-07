package net.smartcosmos.edge.things.service.metadata;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for calling the REST endpoint used for creating metadata.
 */
public interface CreateMetadataRestService {

    /**
     * <p>Send request to REST endpoint to create metadata.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param ownerType the type of the owner
     * @param ownerUrn the URN for the owner of the metadata, what the metadata is attached to
     * @param force if true performs an upsert, overwriting existing entries. False will cause a 4xx failure indicating entry already exists
     * @param metadataMap a Map of the metadata
     * @param user the user making the request
     * @return the ResponseEntity
     */
    @Retryable
    ResponseEntity<?> create(String ownerType, String ownerUrn, Boolean force, Map<String, Object> metadataMap, SmartCosmosUser user);
}
