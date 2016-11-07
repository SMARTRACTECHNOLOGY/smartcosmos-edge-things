package net.smartcosmos.edge.things.service.things;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;

import net.smartcosmos.edge.things.domain.things.RestThingUpdate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to update Things.
 */
public interface UpdateThingRestService {

    /**
     * <p>Send request to REST endpoint to update a thing.
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param type the type of the thing to update
     * @param urn the URN of the thing to update
     * @param thingUpdate the {@link RestThingUpdate} instance containing fields to update
     * @param user the user making the request
     * @return the ResponseEntity
     */
    @Retryable
    ResponseEntity<?> update(String type, String urn, RestThingUpdate thingUpdate, SmartCosmosUser user);
}
