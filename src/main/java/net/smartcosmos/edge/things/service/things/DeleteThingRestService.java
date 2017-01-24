package net.smartcosmos.edge.things.service.things;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to delete Things.
 */
public interface DeleteThingRestService {

    /**
     * <p>Send request to REST endpoint to delete a thing.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param type the type of the thing
     * @param urn the URN of the thing
     * @param user the user making the request
     * @return the ResponseEntity
     */
    @Retryable(exclude = { HttpClientErrorException.class }, include = { Throwable.class })
    ResponseEntity<?> delete(String type, String urn, SmartCosmosUser user);
}
