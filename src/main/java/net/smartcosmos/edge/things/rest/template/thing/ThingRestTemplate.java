package net.smartcosmos.edge.things.rest.template.thing;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.edge.things.rest.template.AbstractRestTemplate;
import net.smartcosmos.edge.things.rest.template.SmartCosmosRequest;

@Slf4j
public class ThingRestTemplate extends AbstractRestTemplate {

    public ThingRestTemplate(OAuth2RestTemplate restOperations, String thingSeviceName) {
        super(restOperations, thingSeviceName);
    }

    public ResponseEntity<?> create(String type, RestThingCreate body) {

        SmartCosmosRequest<RestThingCreate> requestBody = getRequestBody(type, body);
        RequestEntity<RestThingCreate> requestEntity = requestBody.buildRequest();

        return restOperations.exchange(requestEntity, RestThingCreateResponseDto.class);
    }

    @SuppressWarnings("unchecked")
    private SmartCosmosRequest<RestThingCreate> getRequestBody(String type, RestThingCreate body) {
        return SmartCosmosRequest.<RestThingCreate>builder()
                    .serviceName(serviceName)
                    .httpMethod(HttpMethod.POST)
                    .url(type)
                    .requestBody(body)
                    .build();
    }
}
