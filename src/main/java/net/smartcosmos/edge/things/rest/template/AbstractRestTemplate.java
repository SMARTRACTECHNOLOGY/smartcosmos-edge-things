package net.smartcosmos.edge.things.rest.template;

import org.springframework.web.client.RestOperations;

public class AbstractRestTemplate {

    protected final RestOperations restOperations;
    protected final String serviceName;

    public AbstractRestTemplate(RestOperations restOperations, String serviceName) {
        this.restOperations = restOperations;
        this.serviceName = serviceName;
    }
}
