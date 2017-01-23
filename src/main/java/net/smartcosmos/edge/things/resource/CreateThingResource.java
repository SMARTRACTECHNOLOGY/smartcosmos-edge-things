package net.smartcosmos.edge.things.resource;

import java.util.Map;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.annotation.SmartCosmosRdao;
import net.smartcosmos.edge.things.service.CreateThingEdgeService;
import net.smartcosmos.security.user.SmartCosmosUser;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_PROPERTY_ENABLED;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS_CREATE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_FORCE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.TYPE;

@SmartCosmosRdao
@Slf4j
@ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
public class CreateThingResource {

    CreateThingEdgeService createThingService;

    @Autowired
    public CreateThingResource(CreateThingEdgeService createThingService) {

        this.createThingService = createThingService;
    }

    @RequestMapping(value = ENDPOINT_TYPE,
                    method = RequestMethod.POST,
                    produces = APPLICATION_JSON_UTF8_VALUE,
                    consumes = APPLICATION_JSON_UTF8_VALUE)
    @ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS_CREATE, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
    public DeferredResult<ResponseEntity> create( // @formatter:off
        @PathVariable(TYPE) String type,
        @RequestParam(name = PARAM_FORCE, required = false, defaultValue = "false") boolean force,
        @RequestBody @Valid Map<String, Object> metadataMap,
        SmartCosmosUser user) { // @formatter:on

        DeferredResult<ResponseEntity> response = new DeferredResult<>();
        createThingService.create(response, type, metadataMap, force, user);
        return response;
    }

}
