package net.smartcosmos.edge.things.client.metadata.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.GenericType;

import net.smartcosmos.edge.things.client.metadata.ApiClient;
import net.smartcosmos.edge.things.client.metadata.ApiException;
import net.smartcosmos.edge.things.client.metadata.Configuration;
import net.smartcosmos.edge.things.client.metadata.Pair;
import net.smartcosmos.edge.things.client.metadata.model.MetadataResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class CreatemetadataresourceApi {
    private ApiClient apiClient;

    public CreatemetadataresourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public CreatemetadataresourceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create (or upsert) a new set of metadata attached to a particular entity
     * This endpoint is idempotent and will respond with an appropriate HTTP status code to indicate the actual result, ONLY when used without the
     * force URL parameter, or when ?force=false. When the URL parameter ?force=true is set, this method behaves as an upsert, creating new metadata
     * or updating existing metadata as appropriate, and with no idempotency guarantees.
     *
     * @param ownerType ownerType (required)
     * @param ownerUrn ownerUrn (required)
     * @param metadataMap metadataMap (required)
     * @param force force (optional, default to false)
     * @return MetadataResponse
     * @throws ApiException if fails to make API call
     */
    public MetadataResponse createMetadataUsingPOST(String ownerType, String ownerUrn, Object metadataMap, Boolean force) throws ApiException {
        Object localVarPostBody = metadataMap;

        // verify the required parameter 'ownerType' is set
        if (ownerType == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerType' when calling createMetadataUsingPOST");
        }

        // verify the required parameter 'ownerUrn' is set
        if (ownerUrn == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerUrn' when calling createMetadataUsingPOST");
        }

        // verify the required parameter 'metadataMap' is set
        if (metadataMap == null) {
            throw new ApiException(400, "Missing the required parameter 'metadataMap' when calling createMetadataUsingPOST");
        }

        // create path and map variables
        String localVarPath = "/rest/metadata/{ownerType}/{ownerUrn}{?force}".replaceAll("\\{format\\}", "json")
            .replaceAll("\\{" + "ownerType" + "\\}", apiClient.escapeString(ownerType.toString()))
            .replaceAll("\\{" + "ownerUrn" + "\\}", apiClient.escapeString(ownerUrn.toString()));

        // query params
        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "force", force));

        final String[] localVarAccepts = {
            "application/json;charset=UTF-8"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
            "application/json;charset=UTF-8"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {};

        GenericType<MetadataResponse> localVarReturnType = new GenericType<MetadataResponse>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

}
