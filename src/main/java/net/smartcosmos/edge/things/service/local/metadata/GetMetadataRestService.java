package net.smartcosmos.edge.things.service.local.metadata;

import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.security.user.SmartCosmosUser;

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

    /**
     * Return owners which have all of the given keys-value pairs assigned to them.
     *
     * @param keyValuePairs a Map of the metadata key-value pairs
     * @param page the page number
     * @param size the page size
     * @param sortOrder the sortOrder
     * @param sortBy the field to sort by
     * @param user the current logged in user
     * @return a page of owners
     */
    ResponseEntity<?> findByKeyValuePairs(
        Map<String, Object> keyValuePairs,
        Integer page,
        Integer size,
        String sortOrder,
        String sortBy,
        SmartCosmosUser user);
}
