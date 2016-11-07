package net.smartcosmos.edge.things.service.things;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;

import net.smartcosmos.edge.things.domain.things.RestThingCreate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to create Things.
 */
public interface CreateThingRestService {

    /**
     * <p>Send request to REST endpoint to create a thing.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param type the type of the thing
     * @param thingCreate the thing creation request body object
     * @param user the user making the request
     * @return the ResponseEntity
     */
    @Retryable
    ResponseEntity<?> create(String type, RestThingCreate thingCreate, SmartCosmosUser user);

    /**
     * <p>Send request to REST endpoint to create a thing using the default values.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param type the type of the thing
     * @param user the user making the request
     * @return the ResponseEntity
     */
    @Retryable
    ResponseEntity<?> create(String type, SmartCosmosUser user);

}
