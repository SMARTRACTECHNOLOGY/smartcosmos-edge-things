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
import net.smartcosmos.edge.things.client.metadata.model.DeferredResultOfResponseEntity;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class DeletemetadataresourceApi {
    private ApiClient apiClient;

    public DeletemetadataresourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DeletemetadataresourceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Delete all metadata for a particular owner.
     * Delete all metadata for a particular owner, specified by ownerType and ownerUrn
     *
     * @param ownerType ownerType (required)
     * @param ownerUrn ownerUrn (required)
     * @return DeferredResultOfResponseEntity
     * @throws ApiException if fails to make API call
     */
    public DeferredResultOfResponseEntity deleteAllMetadataForOwnerUsingDELETE(String ownerType, String ownerUrn) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'ownerType' is set
        if (ownerType == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerType' when calling deleteAllMetadataForOwnerUsingDELETE");
        }

        // verify the required parameter 'ownerUrn' is set
        if (ownerUrn == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerUrn' when calling deleteAllMetadataForOwnerUsingDELETE");
        }

        // create path and map variables
        String localVarPath = "/rest/metadata/{ownerType}/{ownerUrn}".replaceAll("\\{format\\}", "json")
            .replaceAll("\\{" + "ownerType" + "\\}", apiClient.escapeString(ownerType.toString()))
            .replaceAll("\\{" + "ownerUrn" + "\\}", apiClient.escapeString(ownerUrn.toString()));

        // query params
        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
            "application/json;charset=UTF-8"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {};

        GenericType<DeferredResultOfResponseEntity> localVarReturnType = new GenericType<DeferredResultOfResponseEntity>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

    /**
     * Delete a specific metadata item by ownerType, ownerUrn, and metadata key
     * Delete a specific metadata item by ownerType, ownerUrn, and metadata key.
     *
     * @param ownerType ownerType (required)
     * @param ownerUrn ownerUrn (required)
     * @param key key (required)
     * @return DeferredResultOfResponseEntity
     * @throws ApiException if fails to make API call
     */
    public DeferredResultOfResponseEntity deleteMetadataUsingDELETE(String ownerType, String ownerUrn, String key) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'ownerType' is set
        if (ownerType == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerType' when calling deleteMetadataUsingDELETE");
        }

        // verify the required parameter 'ownerUrn' is set
        if (ownerUrn == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerUrn' when calling deleteMetadataUsingDELETE");
        }

        // verify the required parameter 'key' is set
        if (key == null) {
            throw new ApiException(400, "Missing the required parameter 'key' when calling deleteMetadataUsingDELETE");
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
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
            "application/json;charset=UTF-8"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {};

        GenericType<DeferredResultOfResponseEntity> localVarReturnType = new GenericType<DeferredResultOfResponseEntity>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

}
