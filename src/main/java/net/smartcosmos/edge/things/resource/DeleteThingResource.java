package net.smartcosmos.edge.things.resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.annotation.SmartCosmosRdao;
import net.smartcosmos.edge.things.service.DeleteThingEdgeService;
import net.smartcosmos.security.user.SmartCosmosUser;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_PROPERTY_ENABLED;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS_DELETE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE_URN;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.URN;

@SmartCosmosRdao
@Slf4j
@ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
public class DeleteThingResource {

    DeleteThingEdgeService deleteThingService;

    @Autowired
    public DeleteThingResource(DeleteThingEdgeService deleteThingService) {

        this.deleteThingService = deleteThingService;
    }

    @ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS_DELETE, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
    @RequestMapping(value = ENDPOINT_TYPE_URN, method = RequestMethod.DELETE)
    public DeferredResult<ResponseEntity> delete( // @formatter:off
        @PathVariable(TYPE) String type,
        @PathVariable(URN) String urn,
        SmartCosmosUser user) { // @formatter:on

        DeferredResult<ResponseEntity> response = new DeferredResult<>();
        deleteThingService.delete(response, type, urn, user);
        return response;
    }
}
