package net.smartcosmos.edge.things.service.local.metadata;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to upsert Metadata associated to a Thing.
 */
public interface UpsertMetadataRestService {

    /**
     * Send request to REST endpoint to upsert Metadata.
     *
     * @param ownerType the type of the owner Thing
     * @param ownerUrn the URN of the owner Thing
     * @param metadataMap the map of the Metadata to upsert
     * @param user the user making the request
     * @return the ResponseEntity
     */
    ResponseEntity<?> upsert(String ownerType, String ownerUrn, Map<String, Object> metadataMap, SmartCosmosUser user);
}
