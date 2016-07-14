package net.smartcosmos.edge.things.service.local.things;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.domain.local.things.RestThingUpdate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to update Things.
 */
public interface UpdateThingRestService {

    /**
     * Send request to REST endpoint to update a thing.
     *
     * @param type the type of the thing to update
     * @param urn the URN of the thing to update
     * @param thingUpdate the {@link RestThingUpdate} instance containing fields to update
     * @param user the user making the request
     * @return the ResponseEntity
     */
    ResponseEntity<?> update(String type, String urn, RestThingUpdate thingUpdate, SmartCosmosUser user);
}
