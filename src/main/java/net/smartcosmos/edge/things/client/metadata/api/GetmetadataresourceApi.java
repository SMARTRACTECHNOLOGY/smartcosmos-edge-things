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
import net.smartcosmos.edge.things.client.metadata.model.RestMetadataFindAllResponseDto;
import net.smartcosmos.edge.things.client.metadata.model.RestMetadataKeyResponseDto;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class GetmetadataresourceApi {
    private ApiClient apiClient;

    public GetmetadataresourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public GetmetadataresourceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Return all metadata
     * Look up Metadata by Metadata owner type, owner URN and optional metadata key filter
     *
     * @param page Page number (optional, default to 1)
     * @param size Page size (optional, default to 20)
     * @param sortOrder Sort order (optional, default to ASC)
     * @param sortBy Page size (optional, default to orderType)
     * @return RestMetadataFindAllResponseDto
     * @throws ApiException if fails to make API call
     */
    public RestMetadataFindAllResponseDto findAllUsingGET(Integer page, Integer size, String sortOrder, String sortBy) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/rest/metadata/findAll{?page,size,sortOrder,sortBy}".replaceAll("\\{format\\}", "json");

        // query params
        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", page));

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "size", size));

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "sortOrder", sortOrder));

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "sortBy", sortBy));

        final String[] localVarAccepts = {
            "application/json;charset=UTF-8"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
            "application/json;charset=UTF-8"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {};

        GenericType<RestMetadataFindAllResponseDto> localVarReturnType = new GenericType<RestMetadataFindAllResponseDto>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

    /**
     * Look up Metadata by Metadata owner type, owner URN and optional metadata key filter
     * Look up Metadata by Metadata owner type, owner URN and optional metadata key filter
     *
     * @param ownerType Case-insensitive Metadata owner type to locate. (required)
     * @param ownerUrn Case-insensitive Metadata owner URN to locate. (required)
     * @param keys Case-insensitive Metadata keys to locate. (optional)
     * @return RestMetadataKeyResponseDto
     * @throws ApiException if fails to make API call
     */
    public RestMetadataKeyResponseDto lookupMetadataByOwnerUsingGET(String ownerType, String ownerUrn, String keys) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'ownerType' is set
        if (ownerType == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerType' when calling lookupMetadataByOwnerUsingGET");
        }

        // verify the required parameter 'ownerUrn' is set
        if (ownerUrn == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerUrn' when calling lookupMetadataByOwnerUsingGET");
        }

        // create path and map variables
        String localVarPath = "/rest/metadata/{ownerType}/{ownerUrn}{?keys}".replaceAll("\\{format\\}", "json")
            .replaceAll("\\{" + "ownerType" + "\\}", apiClient.escapeString(ownerType.toString()))
            .replaceAll("\\{" + "ownerUrn" + "\\}", apiClient.escapeString(ownerUrn.toString()));

        // query params
        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        localVarQueryParams.addAll(apiClient.parameterToPairs("", "keys", keys));

        final String[] localVarAccepts = {
            "application/json;charset=UTF-8"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
            "application/json;charset=UTF-8"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {};

        GenericType<RestMetadataKeyResponseDto> localVarReturnType = new GenericType<RestMetadataKeyResponseDto>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

    /**
     * Look up specific Metadata by Metadata owner type, owner URN and metadata key
     * Look up specific Metadata by Metadata owner type, owner URN and metadata key
     *
     * @param ownerType Case-insensitive Metadata owner type to locate. (required)
     * @param ownerUrn Case-insensitive Metadata owner URN to locate. (required)
     * @param key Case-insensitive Metadata key to locate. (required)
     * @return RestMetadataKeyResponseDto
     * @throws ApiException if fails to make API call
     */
    public RestMetadataKeyResponseDto readMetadataUsingGET(String ownerType, String ownerUrn, String key) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'ownerType' is set
        if (ownerType == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerType' when calling readMetadataUsingGET");
        }

        // verify the required parameter 'ownerUrn' is set
        if (ownerUrn == null) {
            throw new ApiException(400, "Missing the required parameter 'ownerUrn' when calling readMetadataUsingGET");
        }

        // verify the required parameter 'key' is set
        if (key == null) {
            throw new ApiException(400, "Missing the required parameter 'key' when calling readMetadataUsingGET");
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

        GenericType<RestMetadataKeyResponseDto> localVarReturnType = new GenericType<RestMetadataKeyResponseDto>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

}
