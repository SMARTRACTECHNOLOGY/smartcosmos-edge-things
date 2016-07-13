package net.smartcosmos.edge.things.rest.template.metadata;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.edge.things.rest.template.AbstractRestTemplate;
import net.smartcosmos.edge.things.rest.template.SmartCosmosRequest;

@Slf4j
public class MetadataRestTemplate extends AbstractRestTemplate {

    public MetadataRestTemplate(RestOperations restOperations, String thingServiceName) {
        super(restOperations, thingServiceName);
    }

    public ResponseEntity<?> create(String ownerType, String ownerUrn, Boolean force, Map<String, Object> metadataMap) {

        SmartCosmosRequest<Map<String, Object>> requestBody = getRequestBody(ownerType, ownerUrn, force, metadataMap);
        RequestEntity<Map<String, Object>> requestEntity = requestBody.buildRequest();

        return restOperations.exchange(requestEntity, RestMetadataCreateResponseDto.class);
    }

    public ResponseEntity<?> create(String ownerType, String ownerUrn, Map<String, Object> metadataMap) {
        return create(ownerType, ownerUrn, false, metadataMap);
    }

    @SuppressWarnings("unchecked")
    private SmartCosmosRequest<Map<String, Object>> getRequestBody(String ownerType, String ownerUrn, Boolean force, Map<String, Object> body) {

        StringBuilder url = new StringBuilder(ownerType)
            .append("/")
            .append(ownerUrn);

        if (BooleanUtils.isTrue(force)) {
            url.append("?force").append(String.valueOf(force));
        }

        return SmartCosmosRequest.<RestThingCreate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.POST)
            .url(url.toString())
            .requestBody(body)
            .build();
    }
}
