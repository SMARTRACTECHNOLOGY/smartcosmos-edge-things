package net.smartcosmos.edge.things.service.metadata;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to upsert Metadata associated to a Thing.
 */
public interface UpsertMetadataRestService {

    /**
     * <p>Send request to REST endpoint to upsert Metadata.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param ownerType the type of the owner Thing
     * @param ownerUrn the URN of the owner Thing
     * @param metadataMap the map of the Metadata to upsert
     * @param user the user making the request
     * @return the ResponseEntity
     */
    @Retryable(exclude = { HttpClientErrorException.class }, include = { Throwable.class })
    ResponseEntity<?> upsert(String ownerType, String ownerUrn, Map<String, Object> metadataMap, SmartCosmosUser user);
}
