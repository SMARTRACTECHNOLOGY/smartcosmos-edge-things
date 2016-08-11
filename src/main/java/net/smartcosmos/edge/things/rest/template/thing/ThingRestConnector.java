package net.smartcosmos.edge.things.rest.template.thing;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.config.SmartCosmosEdgeThingsProperties;
import net.smartcosmos.edge.things.domain.local.things.RestPagedThingResponse;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingResponse;
import net.smartcosmos.edge.things.domain.local.things.RestThingUpdate;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.template.SmartCosmosRequest;

@Component
@Slf4j
public class ThingRestConnector {

    private final RestTemplateFactory restTemplateFactory;
    private final String serviceName;

    @Autowired
    public ThingRestConnector(RestTemplateFactory restTemplateFactory, SmartCosmosEdgeThingsProperties edgeThingsProperties) {

        this.restTemplateFactory = restTemplateFactory;
        serviceName = edgeThingsProperties.getLocal()
            .getThings();
    }

    public ResponseEntity<?> create(String type, RestThingCreate body) {

        SmartCosmosRequest<RestThingCreate> requestBody = getCreateRequestBody(type, body);
        RequestEntity<RestThingCreate> requestEntity = requestBody.buildRequest();

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestThingCreateResponseDto.class);
    }

    private SmartCosmosRequest<RestThingCreate> getCreateRequestBody(String type, RestThingCreate body) {

        StringBuilder url = new StringBuilder("things/")
            .append(type);

        return SmartCosmosRequest.<RestThingCreate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.POST)
            .url(url.toString())
            .requestBody(body)
            .build();
    }

    public ResponseEntity<?> update(String type, String urn, RestThingUpdate body) {

        SmartCosmosRequest<RestThingUpdate> requestBody = getUpdateRequestBody(type, urn, body);
        RequestEntity<RestThingUpdate> requestEntity = requestBody.buildRequest();

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, Void.class);
    }

    private SmartCosmosRequest<RestThingUpdate> getUpdateRequestBody(String type, String urn, RestThingUpdate body) {

        StringBuilder url = new StringBuilder("things/")
            .append(type)
            .append("/")
            .append(urn);

        return SmartCosmosRequest.<RestThingUpdate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.PUT)
            .url(url.toString())
            .requestBody(body)
            .build();
    }

    public ResponseEntity<?> findByTypeAndUrn(String type, String urn) {

        SmartCosmosRequest<Void> requestBody = getFindSpecificRequestBody(type, urn);
        RequestEntity<Void> requestEntity = requestBody.buildRequest();

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestThingResponse.class);
    }

    private SmartCosmosRequest<Void> getFindSpecificRequestBody(String type, String urn) {

        StringBuilder url = new StringBuilder("things/")
            .append(type)
            .append("/")
            .append(urn);

        return SmartCosmosRequest.<Void>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.GET)
            .url(url.toString())
            .build();
    }

    public ResponseEntity<?> findByType(String type) {

        SmartCosmosRequest<Void> requestBody = getFindByTypeRequest(type);
        RequestEntity<Void> requestEntity = requestBody.buildRequest();

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestPagedThingResponse.class);
    }

    private SmartCosmosRequest<Void> getFindByTypeRequest(String type) {

        StringBuilder url = new StringBuilder("things/")
            .append(type);

        return SmartCosmosRequest.<RestThingUpdate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.GET)
            .url(url.toString())
            .build();
    }

    public ResponseEntity<?> delete(String ownerType, String ownerUrn) {

        SmartCosmosRequest<?> requestBody = getDeleteRequest(ownerType, ownerUrn);
        RequestEntity<?> requestEntity = requestBody.buildRequest();

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, Void.class);
    }

    private SmartCosmosRequest<?> getDeleteRequest(String type, String urn) {

        StringBuilder url = new StringBuilder("things/")
            .append(type)
            .append("/")
            .append(urn);

        return SmartCosmosRequest.builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.DELETE)
            .url(url.toString())
            .build();
    }
}
