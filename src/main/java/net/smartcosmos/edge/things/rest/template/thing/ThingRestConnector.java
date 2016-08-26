package net.smartcosmos.edge.things.rest.template.thing;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.domain.local.things.RestPagedThingResponse;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingResponse;
import net.smartcosmos.edge.things.domain.local.things.RestThingUpdate;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.template.SmartCosmosRequest;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;

@Component
@Slf4j
@Deprecated
public class ThingRestConnector {

    private final RestTemplateFactory restTemplateFactory;
    private final ThingRequestFactory requestFactory;

    @Autowired
    public ThingRestConnector(RestTemplateFactory restTemplateFactory, ThingRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    public ResponseEntity<?> create(String type, RestThingCreate body) {

        RequestEntity<RestThingCreate> requestEntity = requestFactory.createRequest(type, body);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestThingCreateResponseDto.class);
    }

    public ResponseEntity<?> update(String type, String urn, RestThingUpdate body) {

        RequestEntity<RestThingUpdate> requestEntity = requestFactory.updateRequest(type, urn, body);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, Void.class);
    }

    public ResponseEntity<?> findByTypeAndUrn(String type, String urn) {

        RequestEntity<Void> requestEntity = requestFactory.findSpecificRequest(type, urn);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestThingResponse.class);
    }

    public ResponseEntity<?> findByType(String type, Integer page, Integer size) {

        RequestEntity<Void> requestEntity = requestFactory.findByTypeRequest(type, page, size);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestPagedThingResponse.class);
    }

    public ResponseEntity<?> delete(String ownerType, String ownerUrn) {

        RequestEntity<?> requestEntity = requestFactory.deleteRequest(ownerType, ownerUrn);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, Void.class);
    }
}
