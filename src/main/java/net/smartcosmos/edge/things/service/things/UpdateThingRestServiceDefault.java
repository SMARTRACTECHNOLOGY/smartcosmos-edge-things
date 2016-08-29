package net.smartcosmos.edge.things.service.things;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.domain.things.RestThingUpdate;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST things endpoint to update a thing.
 */
@Slf4j
@Service
public class UpdateThingRestServiceDefault implements UpdateThingRestService {

    private final RestTemplateFactory restTemplateFactory;
    private final ThingRequestFactory requestFactory;

    @Autowired
    public UpdateThingRestServiceDefault(RestTemplateFactory restTemplateFactory, ThingRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    @Override
    public ResponseEntity<?> update(String type, String urn, RestThingUpdate thingUpdate, SmartCosmosUser user) {

        RequestEntity<RestThingUpdate> requestEntity = requestFactory.updateRequest(type, urn, thingUpdate);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, Void.class);
    }
}
