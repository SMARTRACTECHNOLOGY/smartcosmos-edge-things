package net.smartcosmos.edge.things.rest.request;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import net.smartcosmos.edge.things.config.SmartCosmosEdgeThingsProperties;
import net.smartcosmos.edge.things.domain.things.RestThingCreate;
import net.smartcosmos.edge.things.domain.things.RestThingUpdate;

import static net.smartcosmos.edge.things.util.UrlEncodingUtil.encode;

/**
 * Utility component for creating requests for the Thing service.
 */
@Component
public class ThingRequestFactory {

    private final String serviceName;

    @Autowired
    public ThingRequestFactory(SmartCosmosEdgeThingsProperties edgeThingsProperties) {

        serviceName = edgeThingsProperties.getLocal()
            .getThings();
    }

    /**
     * Creates a request to create a Thing.
     *
     * @param type the Thing type
     * @param body the body of the create request
     * @return the request
     */
    @SuppressWarnings("unchecked")
    public RequestEntity<RestThingCreate> createRequest(String type, RestThingCreate body) {

        Assert.isTrue(StringUtils.isNotBlank(type), "type may not be empty");

        String url = UriComponentsBuilder.fromPath(encode(type))
            .build()
            .toUriString();

        return SmartCosmosRequest.<RestThingCreate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.POST)
            .url(url)
            .requestBody(body)
            .build()
            .buildRequest();
    }

    /**
     * Creates a request to find Things of a given type.
     *
     * @param type the Thing type
     * @param page the number of the page to return
     * @param size the size of the response page
     * @return the request
     */
    @SuppressWarnings("unchecked")
    public RequestEntity<Void> findByTypeRequest(String type, Integer page, Integer size) {

        Assert.isTrue(StringUtils.isNotBlank(type), "type may not be empty");

        String url = UriComponentsBuilder.fromPath(encode(type))
            .queryParam("page", page != null ? page : "")
            .queryParam("size", size != null ? size : "")
            .build()
            .toUriString();

        return SmartCosmosRequest.<RestThingUpdate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.GET)
            .url(url)
            .build()
            .buildRequest();
    }

    /**
     * Creates a request to find a specific Thing by Type and URN.
     *
     * @param type the Thing type
     * @param urn the Thing URN
     * @return the request
     */
    @SuppressWarnings("unchecked")
    public RequestEntity<Void> findSpecificRequest(String type, String urn) {

        Assert.isTrue(StringUtils.isNotBlank(type), "type may not be empty");
        Assert.isTrue(StringUtils.isNotBlank(urn), "URN may not be empty");

        String url = UriComponentsBuilder.fromPath(encode(type))
            .pathSegment(encode(urn))
            .build()
            .toUriString();

        return SmartCosmosRequest.<Void>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.GET)
            .url(url)
            .build()
            .buildRequest();
    }

    /**
     * Creates a request to update a specific Thing.
     *
     * @param type the Thing type
     * @param urn the Thing URN
     * @param body the request body containing the fields to update
     * @return the request
     */
    @SuppressWarnings("unchecked")
    public RequestEntity<RestThingUpdate> updateRequest(String type, String urn, RestThingUpdate body) {

        Assert.isTrue(StringUtils.isNotBlank(type), "type may not be empty");
        Assert.isTrue(StringUtils.isNotBlank(urn), "URN may not be empty");

        String url = UriComponentsBuilder.fromPath(encode(type))
            .pathSegment(encode(urn))
            .build()
            .toUriString();

        return SmartCosmosRequest.<RestThingUpdate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.PUT)
            .url(url)
            .requestBody(body)
            .build()
            .buildRequest();
    }

    /**
     * Creates a request to delete a specific Thing.
     *
     * @param type the Thing type
     * @param urn the Thing URN
     * @return the request
     */
    public RequestEntity<?> deleteRequest(String type, String urn) {

        Assert.isTrue(StringUtils.isNotBlank(type), "type may not be empty");
        Assert.isTrue(StringUtils.isNotBlank(urn), "URN may not be empty");

        String url = UriComponentsBuilder.fromPath(encode(type))
            .pathSegment(encode(urn))
            .build()
            .toUriString();

        return SmartCosmosRequest.builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.DELETE)
            .url(url)
            .build()
            .buildRequest();
    }
}
