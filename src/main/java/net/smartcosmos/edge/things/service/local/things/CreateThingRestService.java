package net.smartcosmos.edge.things.service.local.things;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;

/**
 * Defines the service for the REST endpoint used to create Things.
 */
public interface CreateThingRestService {

    /**
     * Send request to REST endpoint to create a thing.
     *
     * @param type the type of the thing
     * @param thingCreate the thing creation request body object
     * @return the ResponseEntity
     */
    ResponseEntity<?> create(String type, RestThingCreate thingCreate);

    /**
     * Send request to REST endpoint to create a thing using the default values.
     *
     * @param type the type of the thing
     * @return the ResponseEntity
     */
    ResponseEntity<?> create(String type);

}
