package net.smartcosmos.edge.things.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Methods for updating things and their associated metadata.
 */
public interface SignThingEdgeService {

    /**
     * Update the Thing and Metadata.
     *
     * @param type the type of the thing to update
     * @param urn the URN of the thing to update
     * @param user the Spring embedded {@link SmartCosmosUser}
     */
    ResponseEntity sign(String type, String urn, SmartCosmosUser user);
}
