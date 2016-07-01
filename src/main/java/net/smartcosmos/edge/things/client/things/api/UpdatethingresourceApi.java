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
import net.smartcosmos.edge.things.client.things.model.RestThingUpdate;
import net.smartcosmos.edge.things.client.things.model.ThingResponse;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T09:49:05.075-04:00")
public class UpdatethingresourceApi {
    private ApiClient apiClient;

    public UpdatethingresourceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public UpdatethingresourceApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Update an existing Thing by type and urn
     * The request body must include a valid thingUrn field or a valid urn field. Only those fields being updated need to be included in the JSON
     * body. For example, if you only want to update the name of the thing, only include that field in the request body.
     *
     * @param type Case-insensitive Thing type to update. (required)
     * @param urn Case-insensitive Thing URN to update. (required)
     * @param updateThing JSON with a valid thingUrn and the field(s) to be updated. (optional)
     * @return ThingResponse
     * @throws ApiException if fails to make API call
     */
    public ThingResponse updateThingUsingPUT(String type, String urn, RestThingUpdate updateThing) throws ApiException {
        Object localVarPostBody = updateThing;

        // verify the required parameter 'type' is set
        if (type == null) {
            throw new ApiException(400, "Missing the required parameter 'type' when calling updateThingUsingPUT");
        }

        // verify the required parameter 'urn' is set
        if (urn == null) {
            throw new ApiException(400, "Missing the required parameter 'urn' when calling updateThingUsingPUT");
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
            .invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept,
                       localVarContentType, localVarAuthNames, localVarReturnType);

    }

}
