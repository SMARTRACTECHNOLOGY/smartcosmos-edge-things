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
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.annotation.SmartCosmosRdao;
import net.smartcosmos.edge.things.service.UpdateThingEdgeService;
import net.smartcosmos.security.user.SmartCosmosUser;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_PROPERTY_ENABLED;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS_UPDATE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE_URN;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.URN;

@SmartCosmosRdao
@Slf4j
@ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
public class UpdateThingResource {

    private UpdateThingEdgeService updateThingService;

    @Autowired
    public UpdateThingResource(UpdateThingEdgeService updateThingService) {

        this.updateThingService = updateThingService;
    }

    @ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS_UPDATE, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
    @RequestMapping(method = RequestMethod.PUT,
                    value = ENDPOINT_TYPE_URN,
                    produces = APPLICATION_JSON_UTF8_VALUE,
                    consumes = APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity> update(
        @PathVariable(TYPE) String type,
        @PathVariable(URN) String urn,
        @RequestBody @Valid Map<String, Object> requestBody,
        SmartCosmosUser user) {

        DeferredResult<ResponseEntity> response = new DeferredResult<>();
        updateThingService.update(response, type, urn, requestBody, user);
        return response;
    }
}
