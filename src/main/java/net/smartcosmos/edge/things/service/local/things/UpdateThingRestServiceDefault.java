package net.smartcosmos.edge.things.service.local.things;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.edge.things.domain.local.things.RestThingUpdate;
import net.smartcosmos.edge.things.rest.template.thing.ThingRestConnector;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST things endpoint to update a thing.
 */
@Slf4j
@Service
public class UpdateThingRestServiceDefault implements UpdateThingRestService {

    private final ThingRestConnector restTemplate;

    @Autowired
    public UpdateThingRestServiceDefault(ThingRestConnector restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> update(String type, String urn, RestThingUpdate thingUpdate, SmartCosmosUser user) {
        try {
            return restTemplate.update(type, urn, thingUpdate);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }
}
