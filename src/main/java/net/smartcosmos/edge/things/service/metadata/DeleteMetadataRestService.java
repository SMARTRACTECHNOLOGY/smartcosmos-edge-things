package net.smartcosmos.edge.things.service.metadata;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to delete Metadata associated to a Thing.
 */
public interface DeleteMetadataRestService {

    /**
     * <p>Send request to REST endpoint to delete metadata associated to a thing.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param ownerType the type of the owner thing
     * @param ownerUrn the URN of the owner thing
     * @param user the user making the request
     * @return the ResponseEntity
     */
    @Retryable
    ResponseEntity<?> delete(String ownerType, String ownerUrn, SmartCosmosUser user);
}
