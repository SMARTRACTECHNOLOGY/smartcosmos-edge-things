package net.smartcosmos.edge.things.service.local.things;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to create Things.
 */
public interface CreateThingRestService {
    /**
     * Send request to REST endpoint to create a thing.
     *
     * @param type the type of the thing
     * @param urn the URN for the thing
     * @param user the user making the request
     * @return the ResponseEntity
     */
    ResponseEntity<?> create(String type, String urn, SmartCosmosUser user);

}
