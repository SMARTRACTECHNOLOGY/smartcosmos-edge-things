package net.smartcosmos.edge.things.service.local.things;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to get Things.
 */
public interface GetThingRestService {

    /**
     * Look up a Thing by Type and URN.
     *
     * @param type the type of the Thing
     * @param urn the URN of the Thing
     * @param user the user making the request
     * @return the response entity
     */
    ResponseEntity<?> findByTypeAndUrn(String type, String urn, SmartCosmosUser user);
}
