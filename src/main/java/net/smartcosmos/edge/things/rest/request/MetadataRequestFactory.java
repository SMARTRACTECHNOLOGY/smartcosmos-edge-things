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

import static net.smartcosmos.edge.things.util.UrlEncodingUtil.encode;

/**
 * Utility component for creating requests for the Metadata service.
 */
@Component
public class MetadataRequestFactory {

    private final String serviceName;

    @Autowired
    public MetadataRequestFactory(SmartCosmosEdgeThingsProperties edgeThingsProperties) {

        serviceName = edgeThingsProperties.getLocal()
            .getMetadata();
    }

    /**
     * Creates a request to create or upsert Metadata entries associated with a given Metadata owner.
     *
     * @param ownerType the Metadata owner type
     * @param ownerUrn the Metadata owner URN
     * @param force if set to {@code true}, Metadata will be upserted
     * @param keyValuePairs the Metadata entries
     * @return the request
     */
    @SuppressWarnings("unchecked")
    public RequestEntity<Map<String, Object>> createOrUpsertRequest(
        String ownerType,
        String ownerUrn,
        Boolean force,
        Map<String, Object> keyValuePairs) {

        Assert.isTrue(StringUtils.isNotBlank(ownerType), "owner type may not be empty");
        Assert.isTrue(StringUtils.isNotBlank(ownerUrn), "owner URN may not be empty");

        String url = UriComponentsBuilder.fromPath(encode(ownerType))
            .pathSegment(encode(ownerUrn))
            .queryParam("force", BooleanUtils.isTrue(force) ? String.valueOf(force) : "")
            .build()
            .toUriString();

        return SmartCosmosRequest.<RestThingCreate>builder()
            .serviceName(serviceName)
            .httpMethod(HttpMethod.POST)
            .url(url)
            .requestBody(keyValuePairs)
            .build()
            .buildRequest();
    }

    /**
     * Creates a request to find Metadata entries associated with a given Metadata owner.
     *
     * @param ownerType the Metadata owner type
     * @param ownerUrn the Metadata owner URN
     * @param keyNames the keys of the Metadata entries to return (optional)
     * @return the request
     */
    @SuppressWarnings("unchecked")
    public RequestEntity<Void> findByOwnerRequest(String ownerType, String ownerUrn, Set<String> keyNames) {

        Assert.isTrue(StringUtils.isNotBlank(ownerType), "owner type may not be empty");
        Assert.isTrue(StringUtils.isNotBlank(ownerUrn), "owner URN may not be empty");

        String url = UriComponentsBuilder.fromPath(encode(ownerType))
            .pathSegment(encode(ownerUrn))
            .queryParam("keys", keyNames != null && !keyNames.isEmpty() ? StringUtils.join(keyNames, ',') : "")
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
     * Creates a request to delete all Metadata entries for a given owner.
     *
     * @param ownerType the Metadata owner ownerType
     * @param ownerUrn the Metadata owner URN
     * @return the request
     */
    public RequestEntity<?> deleteAllForOwnerRequest(String ownerType, String ownerUrn) {

        Assert.isTrue(StringUtils.isNotBlank(ownerType), "owner type may not be empty");
        Assert.isTrue(StringUtils.isNotBlank(ownerUrn), "owner URN may not be empty");

        String url = UriComponentsBuilder.fromPath(encode(ownerType))
            .pathSegment(encode(ownerUrn))
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
