package net.smartcosmos.edge.things.rest.request;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import net.smartcosmos.edge.things.config.SmartCosmosEdgeThingsProperties;
import net.smartcosmos.edge.things.domain.things.RestThingCreate;
import net.smartcosmos.edge.things.rest.request.SmartCosmosRequest;

import static net.smartcosmos.edge.things.util.UrlEncodingUtil.encode;

/**
 * Utility component for creating requests for the Metadata service.
 */
@Component
public class AuthRequestFactory {

    private final String serviceName;

    @Autowired
    public AuthRequestFactory(SmartCosmosEdgeThingsProperties edgeThingsProperties) {

        serviceName = edgeThingsProperties.getLocal()
            .getAuthServer();
    }

    /**
     * Creates a request to create a signed jwt (from arbitrary thing)
     *
     * @param json serves as the payload of the jwt being signed
     * @return the request
     */
    @SuppressWarnings("unchecked")
    public RequestEntity<Map<String, Object>> createSignRequest(String json) {
        return SmartCosmosRequest.<RestThingCreate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.POST)
            .url("sign")
            .requestBody(json)
            .build()
            .buildRequest();
    }
}
