package net.smartcosmos.edge.things.service.local.metadata;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation of the metadata service.
 */
@Slf4j
@Service
public class CreateMetadataRestServiceDefault implements CreateMetadataRestService {

    private final RestTemplateFactory restTemplateFactory;
    private final MetadataRequestFactory requestFactory;

    @Autowired
    public CreateMetadataRestServiceDefault(RestTemplateFactory restTemplateFactory, MetadataRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    @Override
    public ResponseEntity<?> create(String ownerType, String ownerUrn, Boolean force, Map<String, Object> metadataMap, SmartCosmosUser user) {

        RequestEntity<Map<String, Object>> requestEntity = requestFactory.createOrUpsertRequest(ownerType, ownerUrn, force, metadataMap);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestMetadataCreateResponseDto.class);
    }
}
