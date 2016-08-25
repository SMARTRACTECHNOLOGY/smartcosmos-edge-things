package net.smartcosmos.edge.things.rest.connector;

import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;

@Component
@Slf4j
public class MetadataRestConnector {

    private final RestTemplateFactory restTemplateFactory;
    private final MetadataRequestFactory requestFactory;

    @Autowired
    public MetadataRestConnector(RestTemplateFactory restTemplateFactory, MetadataRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;

    }

    public ResponseEntity<?> create(String ownerType, String ownerUrn, Boolean force, Map<String, Object> metadataMap) {

        RequestEntity<Map<String, Object>> requestEntity = requestFactory.createOrUpsertRequest(ownerType, ownerUrn, force, metadataMap);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestMetadataCreateResponseDto.class);
    }

    public ResponseEntity<?> create(String ownerType, String ownerUrn, Map<String, Object> metadataMap) {

        return create(ownerType, ownerUrn, false, metadataMap);
    }

    public ResponseEntity<?> upsert(String ownerType, String ownerUrn, Map<String, Object> metadataMap) {

        return create(ownerType, ownerUrn, true, metadataMap);
    }

    public ResponseEntity<?> findByTypeAndUrn(String ownerType, String ownerUrn, Set<String> keyNames) {

        RequestEntity<Void> requestEntity = requestFactory.findByOwnerRequest(ownerType, ownerUrn, keyNames);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, Map.class);
    }

    public ResponseEntity<?> delete(String ownerType, String ownerUrn) {

        RequestEntity<?> requestEntity = requestFactory.deleteAllForOwnerRequest(ownerType, ownerUrn);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, Void.class);
    }
}
