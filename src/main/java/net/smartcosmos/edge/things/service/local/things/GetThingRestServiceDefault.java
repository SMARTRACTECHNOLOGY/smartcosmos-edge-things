package net.smartcosmos.edge.things.service.local.things;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.edge.things.rest.template.thing.ThingRestTemplate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST thing endpoint to get a Thing.
 */
@Slf4j
@Service
public class GetThingRestServiceDefault implements GetThingRestService {

    private final ConversionService conversionService;
    private final ThingRestTemplate restTemplate;

    @Inject
    public GetThingRestServiceDefault(ConversionService conversionService, ThingRestTemplate restTemplate) {
        this.conversionService = conversionService;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> findByTypeAndUrn(String type, String urn, SmartCosmosUser user) {
        try {
            return restTemplate.findByTypeAndUrn(type, urn);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }

    @Override
    public ResponseEntity findByType(String type, Integer page, Integer size, String sortOrder, String sortBy, SmartCosmosUser user) {
        try {
            return restTemplate.findByType(type);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }
}
