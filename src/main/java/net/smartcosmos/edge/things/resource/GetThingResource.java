package net.smartcosmos.edge.things.resource;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_PROPERTY_ENABLED;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS_READ_TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_ENABLEMENT_THINGS_READ_URN;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE_URN;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_FIELDS;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_PAGE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_SIZE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_SORT_BY;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_SORT_ORDER;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.URN;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import net.smartcosmos.annotation.SmartCosmosRdao;
import net.smartcosmos.edge.things.service.GetThingEdgeService;
import net.smartcosmos.security.user.SmartCosmosUser;

@SmartCosmosRdao
@Slf4j
@ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
public class GetThingResource {

    GetThingEdgeService getThingService;

    @Autowired
    public GetThingResource(GetThingEdgeService getThingService) {
        this.getThingService = getThingService;
    }

    @ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS_READ_URN, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
    @RequestMapping(value = ENDPOINT_TYPE_URN, method = GET, produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findSpecific( // @formatter:off
        @PathVariable(TYPE) String type,
        @PathVariable(URN) String urn,
        @RequestParam(name = PARAM_FIELDS, required = false) Set<String> fields,
        SmartCosmosUser user) { // @formatter:on

        return getThingService.getByTypeAndUrn(type, urn, fields, user);
    }

    @ConditionalOnProperty(prefix = ENDPOINT_ENABLEMENT_THINGS_READ_TYPE, name = ENDPOINT_ENABLEMENT_PROPERTY_ENABLED, matchIfMissing = true)
    @RequestMapping(value = ENDPOINT_TYPE, method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findByType( // @formatter:off
        @PathVariable(TYPE) String type,
        @RequestParam(name = PARAM_FIELDS, required = false) Set<String> fields,
        @RequestParam(name = PARAM_PAGE, required = false, defaultValue = "1") Integer page,
        @RequestParam(name = PARAM_SIZE, required = false, defaultValue = "20") Integer size,
        @Valid @RequestParam(name = PARAM_SORT_ORDER, required = false, defaultValue = "asc") String sortOrder,
        @RequestParam(name = PARAM_SORT_BY, required = false, defaultValue = "created") String sortBy,
        SmartCosmosUser user) { // @formatter:on

        return getThingService.getByType(type, fields, page, size, sortOrder, sortBy, user);
    }
}
