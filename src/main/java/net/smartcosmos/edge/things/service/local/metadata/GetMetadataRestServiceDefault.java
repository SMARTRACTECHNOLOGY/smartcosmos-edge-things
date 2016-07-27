package net.smartcosmos.edge.things.service.local.metadata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.edge.things.rest.template.metadata.MetadataRestTemplate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST metadata endpoint to get Metadata.
 */
@Slf4j
@Service
public class GetMetadataRestServiceDefault implements GetMetadataRestService {

    private final ConversionService conversionService;
    private final MetadataRestTemplate restTemplate;

    @Inject
    public GetMetadataRestServiceDefault(ConversionService conversionService, MetadataRestTemplate restTemplate) {
        this.conversionService = conversionService;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> findByOwner(String ownerType, String ownerUrn, Set<String> keyNames, SmartCosmosUser user) {
        try {
            return restTemplate.findByTypeAndUrn(ownerType, ownerUrn, keyNames);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }

    @Override
    public ResponseEntity<?> findByOwner(String ownerType, String ownerUrn, SmartCosmosUser user) {
        return findByOwner(ownerType, ownerUrn, new HashSet<>(), user);
    }

    @Override
    public ResponseEntity<?> findByKeyValuePairs(
        Map<String, Object> keyValuePairs,
        Integer page,
        Integer size,
        String sortOrder,
        String sortBy,
        SmartCosmosUser user) {

        try {
            return restTemplate.findByKeyValuePairs(keyValuePairs, page, size, sortOrder, sortBy);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }
}
