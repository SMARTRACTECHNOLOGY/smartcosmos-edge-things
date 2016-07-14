package net.smartcosmos.edge.things.rest.template;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public abstract class AbstractRestTemplate {

    protected final RestOperations restOperations;
    protected final String serviceName;

    public AbstractRestTemplate(RestOperations restOperations, String serviceName) {
        this.restOperations = restOperations;
        this.serviceName = serviceName;
    }

    public ResponseEntity<?> delete(String ownerType, String ownerUrn) {

        SmartCosmosRequest<?> requestBody = getDeleteRequest(ownerType, ownerUrn);
        RequestEntity<?> requestEntity = requestBody.buildRequest();

        return restOperations.exchange(requestEntity, Void.class);
    }

    private SmartCosmosRequest<?> getDeleteRequest(String ownerType, String ownerUrn) {

        StringBuilder url = new StringBuilder(ownerType)
            .append("/")
            .append(ownerType)
            .append("/")
            .append(ownerUrn);

        return SmartCosmosRequest.builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.DELETE)
            .url(url.toString())
            .build();
    }
}
