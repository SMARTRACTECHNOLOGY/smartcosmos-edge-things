package net.smartcosmos.edge.things.resource;

import static java.net.HttpURLConnection.HTTP_CONFLICT;
import static java.net.HttpURLConnection.HTTP_CREATED;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_PROPERTY_ENABLED;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS_CREATE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_FORCE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.TYPE;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import net.smartcosmos.annotation.SmartCosmosRdao;
import net.smartcosmos.edge.things.domain.RestEdgeThingCreateResponseDto;
import net.smartcosmos.edge.things.service.CreateThingEdgeService;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * REST endpoints for creating things with embedded metadata.
 */
@SmartCosmosRdao
@Api
@Slf4j
@ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
public class CreateThingResource {

    CreateThingEdgeService createThingService;

    @Autowired
    public CreateThingResource(CreateThingEdgeService createThingService) {
        this.createThingService = createThingService;
    }

    /**
     * REST endpoint for Thing upsertion with embedded metadata.
     *
     * @param type the programmer/user defined type of the thing
     * @param metadataMap
     * @param user the {@Link SmartCosmosUser} injected by Spring
     * @return
     */
    @ApiOperation(value = "Create (or upsert) a new thing with an embedded set of metadata", notes = "This endpoint is idempotent and will respond with an appropriate HTTP status code to indicate the actual result, "
            + "ONLY when used without the force URL parameter, or when ?force=false. When the URL parameter ?force=true is set, this "
            + "method behaves as an upsert, creating new thing and metadata or updating an existing thing and its metadata as "
            + "appropriate, and with no idempotency guarantees.", response = RestEdgeThingCreateResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_CONFLICT, message = "A Thing with the given urn already exists. No data is merged; existing record is left as-is."),
            @ApiResponse(code = HTTP_CREATED, message = "A new Thing was added successfully.") })
    @RequestMapping(value = ENDPOINT_TYPE, method = RequestMethod.POST, produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    @ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS_CREATE, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
    public DeferredResult<ResponseEntity> create( // @formatter:off
        @ApiParam(value = "Case-insensitive Thing type to create.", required = true, example = "building")
        @PathVariable(TYPE) String type,
        @ApiParam(value = "Force API to behave as an upsert and update data if a thing already exists.", required = false, defaultValue = "false")
        @RequestParam(name = PARAM_FORCE, required = false, defaultValue = "false") boolean force,
        @ApiParam(value = "The standard fields to create a new Thing.", required = true)
        @RequestBody @Valid Map<String, Object> metadataMap,
        SmartCosmosUser user) { // @formatter:on

        DeferredResult<ResponseEntity> response = new DeferredResult<>();
        createThingService.create(response, type, metadataMap, force, user);
        return response;
    }

}
