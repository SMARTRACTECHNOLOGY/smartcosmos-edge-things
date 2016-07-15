package net.smartcosmos.edge.things.service;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Methods for looking up things and their associated metadata.
 */
public interface GetThingEdgeService {

    /**
     * Look up a specific Thing and its Metadata.
     *
     * @param type the type of the thing
     * @param urn the URN of the thing
     * @param user the Spring embedded {@link SmartCosmosUser}
     * @return the response entity
     */
    ResponseEntity<?> getByTypeAndUrn(String type, String urn, SmartCosmosUser user);

    /**
     * Look up all Things and their Metadata in the realm of the authenticated user.
     *
     * @param page number of the requested page
     * @param size requested page size
     * @param sortOrder the sort order
     * @param sortBy the field to sort by
     * @param user the Spring embedded {@link SmartCosmosUser}
     * @return the response entity
     */
    ResponseEntity<?> getAll(Integer page, Integer size, String sortOrder, String sortBy, SmartCosmosUser user);
}
