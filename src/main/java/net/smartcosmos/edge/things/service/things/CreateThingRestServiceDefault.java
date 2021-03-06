package net.smartcosmos.edge.things.service.things;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.domain.things.RestThingCreate;
import net.smartcosmos.edge.things.domain.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST things endpoint to create a thing.
 */
@Slf4j
@Service
public class CreateThingRestServiceDefault implements CreateThingRestService {

    private final RestTemplateFactory restTemplateFactory;
    private final ThingRequestFactory requestFactory;

    @Autowired
    public CreateThingRestServiceDefault(RestTemplateFactory restTemplateFactory, ThingRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    @Override
    public ResponseEntity<?> create(String type, RestThingCreate thingCreate, SmartCosmosUser user) {

        RequestEntity<RestThingCreate> requestEntity = requestFactory.createRequest(type, thingCreate);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestThingCreateResponseDto.class);
    }

    @Override
    public ResponseEntity<?> create(String type, SmartCosmosUser user) {

        return create(type, new RestThingCreate(), user);
    }
}
