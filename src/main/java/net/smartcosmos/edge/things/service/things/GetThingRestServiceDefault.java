package net.smartcosmos.edge.things.service.things;

import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.domain.things.RestPagedThingResponse;
import net.smartcosmos.edge.things.domain.things.RestThingResponse;
import net.smartcosmos.edge.things.domain.things.RestUnpagedThingResponse;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST thing endpoint to get a Thing.
 */
@Slf4j
@Service
public class GetThingRestServiceDefault implements GetThingRestService {

    private final RestTemplateFactory restTemplateFactory;
    private final ThingRequestFactory requestFactory;

    @Autowired
    public GetThingRestServiceDefault(RestTemplateFactory restTemplateFactory, ThingRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    @Override
    public ResponseEntity<?> findByTypeAndUrn(String type, String urn, SmartCosmosUser user) {

        RequestEntity<Void> requestEntity = requestFactory.findSpecificRequest(type, urn);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestThingResponse.class);
    }

    @Override
    public ResponseEntity<?> findByTypeAndUrns(String type, Map<String, Set<String>> urns, SmartCosmosUser user) {

        RequestEntity<Void> requestEntity = requestFactory.findByTypeAndUrnsRequest(type, urns);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestUnpagedThingResponse.class);
    }

    @Override
    public ResponseEntity findByType(String type, Integer page, Integer size, String sortOrder, String sortBy, SmartCosmosUser user) {

        RequestEntity<Void> requestEntity = requestFactory.findByTypeRequest(type, page, size);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestPagedThingResponse.class);
    }
}
