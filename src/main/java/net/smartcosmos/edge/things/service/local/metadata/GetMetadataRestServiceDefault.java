package net.smartcosmos.edge.things.service.local.metadata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST metadata endpoint to get Metadata.
 */
@Slf4j
@Service
public class GetMetadataRestServiceDefault implements GetMetadataRestService {

    private final RestTemplateFactory restTemplateFactory;
    private final MetadataRequestFactory requestFactory;

    @Autowired
    public GetMetadataRestServiceDefault(RestTemplateFactory restTemplateFactory, MetadataRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    @Override
    public ResponseEntity<?> findByOwner(String ownerType, String ownerUrn, Set<String> keyNames, SmartCosmosUser user) {

        RequestEntity<Void> requestEntity = requestFactory.findByOwnerRequest(ownerType, ownerUrn, keyNames);

        return restTemplateFactory.getRestTemplate()
            .exchange(requestEntity, Map.class);
    }

    @Override
    public ResponseEntity<?> findByOwner(String ownerType, String ownerUrn, SmartCosmosUser user) {

        return findByOwner(ownerType, ownerUrn, new HashSet<>(), user);
    }
}
