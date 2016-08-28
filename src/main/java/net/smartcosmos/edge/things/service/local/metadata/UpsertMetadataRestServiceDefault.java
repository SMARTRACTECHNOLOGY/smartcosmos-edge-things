package net.smartcosmos.edge.things.service.local.metadata;

import lombok.extern.slf4j.Slf4j;
import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.security.user.SmartCosmosUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * The default implementation to call the REST Metadata endpoint to upsert metadata.
 */
@Slf4j
@Service
public class UpsertMetadataRestServiceDefault implements UpsertMetadataRestService {

    private final RestTemplateFactory restTemplateFactory;
    private final MetadataRequestFactory requestFactory;

    @Autowired
    public UpsertMetadataRestServiceDefault(RestTemplateFactory restTemplateFactory, MetadataRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    @Override
    public ResponseEntity<?> upsert(String ownerType, String ownerUrn, Map<String, Object> metadataMap, SmartCosmosUser user) {

        RequestEntity<Map<String, Object>> requestEntity = requestFactory.createOrUpsertRequest(ownerType, ownerUrn, true, metadataMap);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, RestMetadataCreateResponseDto.class);
    }
}
