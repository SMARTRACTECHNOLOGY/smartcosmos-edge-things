package net.smartcosmos.edge.things.service.local.metadata;

import net.smartcosmos.security.user.SmartCosmosUser;
import org.springframework.http.ResponseEntity;

import java.util.Set;

/**
 * Defines the service for the REST endpoint used to get Metadata.
 */
public interface GetMetadataRestService {

    /**
     * Look up Metadata associated with a given Thing that match a set of given key names.
     *
     * @param ownerType the type of the owner Thing
     * @param ownerUrn the URN of the owner Thing
     * @param keyNames the keys to include in the response
     * @param user the user making the request
     * @return the response entity
     */
    ResponseEntity<?> findByOwner(String ownerType, String ownerUrn, Set<String> keyNames, SmartCosmosUser user);

    /**
     * Look up all Metadata associated with a given Thing.
     *
     * @param ownerType the type of the owner Thing
     * @param ownerUrn the URN of the owner Thing
     * @param user the user making the request
     * @return the response entity
     */
    ResponseEntity<?> findByOwner(String ownerType, String ownerUrn, SmartCosmosUser user);
}
