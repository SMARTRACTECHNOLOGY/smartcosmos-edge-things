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
import net.smartcosmos.edge.things.client.metadata.model.RestMetadataUpdate;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class UpdatemetadataresourceApi {
    private ApiClient apiClient;

    public UpdatemetadataresourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public UpdatemetadataresourceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Update an existing metadata item by ownerType, ownerUrn, and metadata key
     * Update an existing metadata item by ownerType, ownerUrn, and metadata key. The ownerType/ownerUrn/key combination must already exist in the
     * datastore.
     *
     * @param ownerType ownerType (required)
     * @param ownerUrn ownerUrn (required)
     * @param key key (required)
     * @param updateMetadata updateMetadata (required)
     * @return MetadataResponse
     * @throws ApiException if fails to make API call
     */
    public MetadataResponse updateMetadataUsingPUT(String ownerType, String ownerUrn, String key, RestMetadataUpdate updateMetadata)
        throws ApiException {
        Object localVarPostBody = updateMetadata;

        // verify the required parameter 'ownerType' is set
        if (ownerType == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerType' when calling updateMetadataUsingPUT");
        }

        // verify the required parameter 'ownerUrn' is set
        if (ownerUrn == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerUrn' when calling updateMetadataUsingPUT");
        }

        // verify the required parameter 'key' is set
        if (key == null) {
            throw new ApiException(400, "Missing the required parameter 'key' when calling updateMetadataUsingPUT");
        }

        // verify the required parameter 'updateMetadata' is set
        if (updateMetadata == null) {
            throw new ApiException(400, "Missing the required parameter 'updateMetadata' when calling updateMetadataUsingPUT");
        }

        // create path and map variables
        String localVarPath = "/rest/metadata/{ownerType}/{ownerUrn}/{key}".replaceAll("\\{format\\}", "json")
            .replaceAll("\\{" + "ownerType" + "\\}", apiClient.escapeString(ownerType.toString()))
            .replaceAll("\\{" + "ownerUrn" + "\\}", apiClient.escapeString(ownerUrn.toString()))
            .replaceAll("\\{" + "key" + "\\}", apiClient.escapeString(key.toString()));

        // query params
        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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
            .invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

}
