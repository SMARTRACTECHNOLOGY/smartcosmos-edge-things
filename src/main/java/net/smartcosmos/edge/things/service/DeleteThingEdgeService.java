package net.smartcosmos.edge.things.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Methods for deleting things and their associated metadata.
 */
public interface DeleteThingEdgeService {

    /**
     * Delete the Thing and metadata.
     * @param response the {@link DeferredResult} response that should be asynchronous
     * @param type the type of the thing to delete
     * @param urn the URN of the thing to delete
     * @param user the Spring embedded {@link SmartCosmosUser}
     */
    void delete(DeferredResult<ResponseEntity> response, String type, String urn, SmartCosmosUser user);
}
