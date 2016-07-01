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
public class DeletethingresourceApi {
    private ApiClient apiClient;

    public DeletethingresourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DeletethingresourceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Delete specific Thing by Thing type and URN
     * Delete a specific Thing by the type the URN key.
     *
     * @param urn Case-insensitive Thing URN to delete. (required)
     * @param type Case-insensitive Thing type to delete. (required)
     * @return ThingResponse
     * @throws ApiException if fails to make API call
     */
    public ThingResponse deleteUsingDELETE(String urn, String type) throws ApiException {
        Object localVarPostBody = null;

        // verify the required parameter 'urn' is set
        if (urn == null) {
            throw new ApiException(400, "Missing the required parameter 'urn' when calling deleteUsingDELETE");
        }

        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException(400, "Missing the required parameter 'type' when calling deleteUsingDELETE");
        }

        // create path and map variables
        String localVarPath = "/rest/things/{type}/{urn}".replaceAll("\\{format\\}", "json")
            .replaceAll("\\{" + "urn" + "\\}", apiClient.escapeString(urn.toString()))
            .replaceAll("\\{" + "type" + "\\}", apiClient.escapeString(type.toString()));

        // query params
        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {};

        GenericType<ThingResponse> localVarReturnType = new GenericType<ThingResponse>() {
        };
        return apiClient
            .invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

}
