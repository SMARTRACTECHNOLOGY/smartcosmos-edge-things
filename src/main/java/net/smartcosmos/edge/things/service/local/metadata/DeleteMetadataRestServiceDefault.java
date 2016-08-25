package net.smartcosmos.edge.things.service.local.metadata;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST metadata endpoint to delete a metadata by owner.
 */
@Slf4j
@Service
public class DeleteMetadataRestServiceDefault implements DeleteMetadataRestService {

    private final RestTemplateFactory restTemplateFactory;
    private final MetadataRequestFactory requestFactory;

    @Autowired
    public DeleteMetadataRestServiceDefault(RestTemplateFactory restTemplateFactory, MetadataRequestFactory requestFactory) {

        this.restTemplateFactory = restTemplateFactory;
        this.requestFactory = requestFactory;
    }

    @Override
    public ResponseEntity<?> delete(String ownerType, String ownerUrn, SmartCosmosUser user) {

        RequestEntity<?> requestEntity = requestFactory.deleteAllForOwnerRequest(ownerType, ownerUrn);

        try {
            return restTemplateFactory.getRestTemplate()
                .exchange(requestEntity, Void.class);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity.status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }
}
