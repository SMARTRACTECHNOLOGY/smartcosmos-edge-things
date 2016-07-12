package net.smartcosmos.edge.things.rest.template.metadata;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestOperations;

import net.smartcosmos.edge.things.rest.template.AbstractRestTemplate;

@Slf4j
public class MetadataRestTemplate extends AbstractRestTemplate {

    public MetadataRestTemplate(RestOperations restOperations, String thingServiceName) {
        super(restOperations, thingServiceName);
    }
}
