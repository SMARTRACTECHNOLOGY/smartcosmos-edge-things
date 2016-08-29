package net.smartcosmos.edge.things.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Methods for updating things and their associated metadata.
 */
public interface UpdateThingEdgeService {

    /**
     * Update the Thing and Metadata.
     *
     * @param response the {@link DeferredResult} response that should be asynchronous
     * @param type the type of the thing to update
     * @param urn the URN of the thing to update
     * @param requestBody the request body containing the Metadata fields to update
     * @param user the Spring embedded {@link SmartCosmosUser}
     */
    void update(DeferredResult<ResponseEntity> response, String type, String urn, Map<String, Object> requestBody, SmartCosmosUser user);
}
