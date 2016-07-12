package net.smartcosmos.edge.things.service.local.things;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;
import net.smartcosmos.edge.things.rest.template.thing.ThingRestTemplate;

/**
 * The default implementation to call the REST things endpoint to create a thing.
 */
@Slf4j
@Service
public class CreateThingRestServiceDefault implements CreateThingRestService {
    private final ConversionService conversionService;
    private final ThingRestTemplate restTemplate;

    @Inject
    public CreateThingRestServiceDefault(ConversionService conversionService, ThingRestTemplate restTemplate) {
        this.conversionService = conversionService;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<?> create(String type, net.smartcosmos.edge.things.domain.local.things.RestThingCreate thingCreate) {
        try {
            return restTemplate.create(type, thingCreate);
        } catch (HttpClientErrorException e) {
            // if something goes wrong, forward the response
            return ResponseEntity
                .status(e.getStatusCode())
                .headers(e.getResponseHeaders())
                .body(e.getResponseBodyAsString());
        }
    }

    @Override
    public ResponseEntity<?> create(String type) {
        return create(type, new RestThingCreate());
    }
}
