package net.smartcosmos.edge.things.service.local.things;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.domain.local.things.RestThingUpdate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST things endpoint to update a thing.
 */
@Slf4j
@Service
public class UpdateThingRestServiceDefault implements UpdateThingRestService {

    @Override
    public ResponseEntity<?> update(
        String type, String urn, RestThingUpdate thingUpdate, SmartCosmosUser user) {
        return null;
    }
}
