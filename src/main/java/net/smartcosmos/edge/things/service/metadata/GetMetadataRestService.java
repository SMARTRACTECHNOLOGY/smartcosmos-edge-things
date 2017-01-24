package net.smartcosmos.edge.things.service.metadata;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to get Metadata.
 */
public interface GetMetadataRestService {

    /**
     * <p>Look up Metadata associated with a given Thing that match a set of given key names.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param ownerType the type of the owner Thing
     * @param ownerUrn the URN of the owner Thing
     * @param keyNames the keys to include in the response
     * @param user the user making the request
     * @return the response entity
     */
    @Retryable(exclude = { HttpClientErrorException.class }, include = { Throwable.class })
    ResponseEntity<?> findByOwner(String ownerType, String ownerUrn, Set<String> keyNames, SmartCosmosUser user);

    /**
     * <p>Look up all Metadata associated with a given Thing.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param ownerType the type of the owner Thing
     * @param ownerUrn the URN of the owner Thing
     * @param user the user making the request
     * @return the response entity
     */
    @Retryable(exclude = { HttpClientErrorException.class }, include = { Throwable.class })
    ResponseEntity<?> findByOwner(String ownerType, String ownerUrn, SmartCosmosUser user);
}
