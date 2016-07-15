package net.smartcosmos.edge.things.rest.template.thing;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.edge.things.domain.local.things.RestThingUpdate;
import net.smartcosmos.edge.things.rest.template.AbstractRestTemplate;
import net.smartcosmos.edge.things.rest.template.SmartCosmosRequest;

@Slf4j
public class ThingRestTemplate extends AbstractRestTemplate {

    public ThingRestTemplate(OAuth2RestTemplate restOperations, String thingSeviceName) {
        super(restOperations, thingSeviceName);
    }

    public ResponseEntity<?> create(String type, RestThingCreate body) {

        SmartCosmosRequest<RestThingCreate> requestBody = getCreateRequestBody(type, body);
        RequestEntity<RestThingCreate> requestEntity = requestBody.buildRequest();

        return restOperations.exchange(requestEntity, RestThingCreateResponseDto.class);
    }

    @SuppressWarnings("unchecked")
    private SmartCosmosRequest<RestThingCreate> getCreateRequestBody(String type, RestThingCreate body) {
        return SmartCosmosRequest.<RestThingCreate>builder()
                    .serviceName(serviceName)
                    .httpMethod(HttpMethod.POST)
                    .url(type)
                    .requestBody(body)
                    .build();
    }

    public ResponseEntity<?> update(String type, String urn, RestThingUpdate body) {

        SmartCosmosRequest<RestThingUpdate> requestBody = getUpdateRequestBody(type, urn, body);
        RequestEntity<RestThingUpdate> requestEntity = requestBody.buildRequest();

        return restOperations.exchange(requestEntity, Void.class);
    }

    @SuppressWarnings("unchecked")
    private SmartCosmosRequest<RestThingUpdate> getUpdateRequestBody(String type, String urn, RestThingUpdate body) {

        StringBuilder url = new StringBuilder(type)
            .append("/")
            .append(urn);

        return SmartCosmosRequest.<RestThingUpdate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.PUT)
            .url(url.toString())
            .requestBody(body)
            .build();
    }
}
