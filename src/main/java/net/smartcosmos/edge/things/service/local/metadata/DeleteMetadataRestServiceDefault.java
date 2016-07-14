package net.smartcosmos.edge.things.service.local.metadata;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestTemplate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST metadata endpoint to delete a metadata by owner.
 */
@Slf4j
@Service
public class DeleteMetadataRestServiceDefault implements DeleteMetadataRestService {

    private final ConversionService conversionService;
    private final MetadataRestTemplate restTemplate;

    @Inject
    public DeleteMetadataRestServiceDefault(ConversionService conversionService, MetadataRestTemplate restTemplate) {
        this.conversionService = conversionService;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> delete(String ownerType, String ownerUrn, SmartCosmosUser user) {
        try {
            return restTemplate.delete(ownerType, ownerUrn);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }
}
