package net.smartcosmos.edge.things.service.local.things;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.security.user.SmartCosmosUser;
import org.springframework.http.ResponseEntity;

/**
 * Defines the service for the REST endpoint used to create Things.
 */
public interface CreateThingRestService {

    /**
     * Send request to REST endpoint to create a thing.
     *
     * @param type the type of the thing
     * @param thingCreate the thing creation request body object
     * @param user the user making the request
     * @return the ResponseEntity
     */
    ResponseEntity<?> create(String type, RestThingCreate thingCreate, SmartCosmosUser user);

    /**
     * Send request to REST endpoint to create a thing using the default values.
     *
     * @param type the type of the thing
     * @param user the user making the request
     * @return the ResponseEntity
     */
    ResponseEntity<?> create(String type, SmartCosmosUser user);

}
