package net.smartcosmos.edge.things.service.local.metadata;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to delete Metadata associated to a Thing.
 */
public interface DeleteMetadataRestService {

    /**
     * Send request to REST endpoint to delete metadata associated to a thing.
     *
     * @param ownerType the type of the owner thing
     * @param ownerUrn the URN of the owner thing
     * @param user the user making the request
     * @return the ResponseEntity
     */
    ResponseEntity<?> delete(String ownerType, String ownerUrn, SmartCosmosUser user);
}
