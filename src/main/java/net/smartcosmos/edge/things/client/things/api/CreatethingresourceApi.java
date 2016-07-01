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
import net.smartcosmos.edge.things.client.things.model.RestThingCreate;
import net.smartcosmos.edge.things.client.things.model.ThingResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T09:49:05.075-04:00")
public class CreatethingresourceApi {
    private ApiClient apiClient;

    public CreatethingresourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public CreatethingresourceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create a new Thing
     * This endpoint is idempotent and will respond with an appropriate HTTP status code to indicate the actual result.
     *
     * @param createObject The standard fields to create a new Thing. (required)
     * @param type Case-insensitive Thing type to create. (required)
     * @return ThingResponse
     * @throws ApiException if fails to make API call
     */
    public ThingResponse createObjectUsingPOST(RestThingCreate createObject, String type) throws ApiException {
        Object localVarPostBody = createObject;

        // verify the required parameter 'createObject' is set
        if (createObject == null) {
            throw new ApiException(400, "Missing the required parameter 'createObject' when calling createObjectUsingPOST");
        }

        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException(400, "Missing the required parameter 'type' when calling createObjectUsingPOST");
        }

        // create path and map variables
        String localVarPath = "/rest/things/{type}".replaceAll("\\{format\\}", "json")
            .replaceAll("\\{" + "type" + "\\}", apiClient.escapeString(type.toString()));

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

}
