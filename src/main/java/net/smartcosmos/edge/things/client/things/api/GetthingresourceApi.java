package net.smartcosmos.edge.things.client.things.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.GenericType;

import net.smartcosmos.edge.things.client.things.ApiClient;
import net.smartcosmos.edge.things.client.things.ApiException;
import net.smartcosmos.edge.things.client.things.Configuration;
import net.smartcosmos.edge.things.client.things.Pair;
import net.smartcosmos.edge.things.client.things.model.ThingResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T09:49:05.075-04:00")
public class GetthingresourceApi {
    private ApiClient apiClient;

    public GetthingresourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public GetthingresourceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Look up an array of all Things in the realm of the current logged in user
     *
     * @param page Defaults to 1. (optional, default to 1)
     * @param size Defaults to 20. (optional, default to 20)
     * @param sortOrder Defaults to &#39;asc&#39;. (optional, default to asc)
     * @param sortBy Defaults to chronological order. (optional, default to created)
     * @return Object
     * @throws ApiException if fails to make API call
     */
    public Object findAllUsingGET(Integer page, Integer size, String sortOrder, String sortBy) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/rest/things/{?page,size,sortOrder,sortBy}".replaceAll("\\{format\\}", "json");

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

        GenericType<Object> localVarReturnType = new GenericType<Object>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

    /**
     * Look up an array of matching Things
     * Takes an array of URNs, and returns the Things associated with those URNs. If any URN in the input array doesn&#39;t match a Thing, it will be
     * added to a notFound list that is returned along with the found Things.
     *
     * @param urns An array of urns. (required)
     * @return ThingResponse
     * @throws ApiException if fails to make API call
     */
    public ThingResponse findByIdsUsingPOST(List<String> urns) throws ApiException {
        Object localVarPostBody = urns;

        // verify the required parameter 'urns' is set
        if (urns == null) {
            throw new ApiException(400, "Missing the required parameter 'urns' when calling findByIdsUsingPOST");
        }

        // create path and map variables
        String localVarPath = "/rest/things/".replaceAll("\\{format\\}", "json");

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

        GenericType<ThingResponse> localVarReturnType = new GenericType<ThingResponse>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

    /**
     * Look up an array of all Things of a specific type in the realm of the current logged in user
     *
     * @param type Case-insensitive type to locate. (required)
     * @param page Defaults to 1. (optional, default to 1)
     * @param size Defaults to 20. (optional, default to 20)
     * @param sortOrder Defaults to &#39;asc&#39;. (optional, default to asc)
     * @param sortBy Defaults to chronological order. (optional, default to created)
     * @return Object
     * @throws ApiException if fails to make API call
     */
    public Object findByTypeUsingGET(String type, Integer page, Integer size, String sortOrder, String sortBy) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException(400, "Missing the required parameter 'type' when calling findByTypeUsingGET");
        }

        // create path and map variables
        String localVarPath = "/rest/things/{type}{?page,size,sortOrder,sortBy}".replaceAll("\\{format\\}", "json")
            .replaceAll("\\{" + "type" + "\\}", apiClient.escapeString(type.toString()));

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

        GenericType<Object> localVarReturnType = new GenericType<Object>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

    /**
     * Look up specific Thing by Thing type and URN
     * Look up a specific Thing by the type and the Thing URN key.
     *
     * @param type Case-insensitive Thing type to locate. (required)
     * @param urn Case-insensitive Thing URN to locate. (required)
     * @return ThingResponse
     * @throws ApiException if fails to make API call
     */
    public ThingResponse getThingByTypeAndUrnUsingGET(String type, String urn) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException(400, "Missing the required parameter 'type' when calling getThingByTypeAndUrnUsingGET");
        }

        // verify the required parameter 'urn' is set
        if (urn == null) {
            throw new ApiException(400, "Missing the required parameter 'urn' when calling getThingByTypeAndUrnUsingGET");
        }

        // create path and map variables
        String localVarPath = "/rest/things/{type}/{urn}".replaceAll("\\{format\\}", "json")
            .replaceAll("\\{" + "type" + "\\}", apiClient.escapeString(type.toString()))
            .replaceAll("\\{" + "urn" + "\\}", apiClient.escapeString(urn.toString()));

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

        GenericType<ThingResponse> localVarReturnType = new GenericType<ThingResponse>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

}
