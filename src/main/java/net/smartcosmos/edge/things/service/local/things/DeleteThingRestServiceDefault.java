package net.smartcosmos.edge.things.service.local.things;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.domain.local.things.RestPagedThingResponse;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST things endpoint to delete a thing.
 */
@Slf4j
@Service
public class DeleteThingRestServiceDefault implements DeleteThingRestService {

    private final RestTemplateFactory restTemplateFactory;
    private final ThingRequestFactory requestFactory;

    @Autowired
    public DeleteThingRestServiceDefault(RestTemplateFactory restTemplateFactory, ThingRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    @Override
    public ResponseEntity<?> delete(String type, String urn, SmartCosmosUser user) {

        RequestEntity<?> requestEntity = requestFactory.deleteRequest(type, urn);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestPagedThingResponse.class);
    }
}
