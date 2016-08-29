package net.smartcosmos.edge.things.service.things;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to delete Things.
 */
public interface DeleteThingRestService {

    /**
     * Send request to REST endpoint to delete a thing.
     *
     * @param type the type of the thing
     * @param urn the URN of the thing
     * @param user the user making the request
     * @return the ResponseEntity
     */
    ResponseEntity<?> delete(String type, String urn, SmartCosmosUser user);
}
