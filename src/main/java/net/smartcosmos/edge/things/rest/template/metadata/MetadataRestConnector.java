package net.smartcosmos.edge.things.rest.template.metadata;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import net.smartcosmos.edge.things.config.SmartCosmosEdgeThingsProperties;
import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.template.SmartCosmosRequest;

@Component
@Slf4j
public class MetadataRestConnector {

    private final RestTemplateFactory restTemplateFactory;
    private final String serviceName;

    @Autowired
    public MetadataRestConnector(RestTemplateFactory restTemplateFactory, SmartCosmosEdgeThingsProperties edgeThingsProperties) {

        this.restTemplateFactory = restTemplateFactory;
        serviceName = edgeThingsProperties.getLocal().getMetadata();
    }

    public ResponseEntity<?> create(String ownerType, String ownerUrn, Boolean force, Map<String, Object> metadataMap) {

        SmartCosmosRequest<Map<String, Object>> requestBody = getRequest(ownerType, ownerUrn, force, metadataMap);
        RequestEntity<Map<String, Object>> requestEntity = requestBody.buildRequest();

        return restTemplateFactory.getRestTemplate().exchange(requestEntity, RestMetadataCreateResponseDto.class);
    }

    public ResponseEntity<?> create(String ownerType, String ownerUrn, Map<String, Object> metadataMap) {
        return create(ownerType, ownerUrn, false, metadataMap);
    }

    public ResponseEntity<?> upsert(String ownerType, String ownerUrn, Map<String, Object> metadataMap) {
        return create(ownerType, ownerUrn, true, metadataMap);
    }

    private SmartCosmosRequest<Map<String, Object>> getRequest(String ownerType, String ownerUrn, Boolean force, Map<String, Object> body) {

        StringBuilder url = new StringBuilder(ownerType)
            .append("/")
            .append(ownerUrn);

        if (BooleanUtils.isTrue(force)) {
            url.append("?force=").append(String.valueOf(force));
        }

        return SmartCosmosRequest.<RestThingCreate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.POST)
            .url(url.toString())
            .requestBody(body)
            .build();
    }

    public ResponseEntity<?> findByTypeAndUrn(String ownerType, String ownerUrn, Set<String> keyNames) {

        SmartCosmosRequest<Void> requestBody = getFindByOwnerRequest(ownerType, ownerUrn, keyNames);
        RequestEntity<Void> requestEntity = requestBody.buildRequest();

        return restTemplateFactory.getRestTemplate().exchange(requestEntity, Map.class);
    }

    private SmartCosmosRequest<Void> getFindByOwnerRequest(String ownerType, String ownerUrn, Set<String> keyNames) {

        StringBuilder url = new StringBuilder(ownerType)
            .append("/")
            .append(ownerUrn);

        if (keyNames != null && !keyNames.isEmpty()) {
            url.append("?keys=").append(StringUtils.join(keyNames, ','));
        }

        return SmartCosmosRequest.<Void>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.GET)
            .url(url.toString())
            .build();
    }

    public ResponseEntity<?> delete(String ownerType, String ownerUrn) {

        SmartCosmosRequest<?> requestBody = getDeleteRequest(ownerType, ownerUrn);
        RequestEntity<?> requestEntity = requestBody.buildRequest();

        return restTemplateFactory.getRestTemplate().exchange(requestEntity, Void.class);
    }

    private SmartCosmosRequest<?> getDeleteRequest(String type, String urn) {

        StringBuilder url = new StringBuilder(type).append("/").append(urn);

        return SmartCosmosRequest.builder().serviceName(serviceName).httpMethod(HttpMethod.DELETE).url(url.toString()).build();
    }
}
