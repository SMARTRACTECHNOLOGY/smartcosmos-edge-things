package net.smartcosmos.edge.things.resource;

import java.util.Set;
import javax.inject.Inject;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.smartcosmos.edge.things.service.GetThingEdgeService;
import net.smartcosmos.security.user.SmartCosmosUser;
import net.smartcosmos.spring.SmartCosmosRdao;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SmartCosmosRdao
@Slf4j
@ConditionalOnProperty(prefix = "smartcosmos.endpoints.edge.things", name = "enabled", matchIfMissing = true)
public class GetThingResource {

    GetThingEdgeService getThingService;

    @Inject
    public GetThingResource(GetThingEdgeService getThingService) {
        this.getThingService = getThingService;
    }

    @RequestMapping(value = "{type}/{urn}", method = GET, produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findSpecific(
        @PathVariable("type") String type,
        @PathVariable("urn") String urn,
        @RequestParam(required = false) Set<String> fields,
        SmartCosmosUser user) {

        return getThingService.getByTypeAndUrn(type, urn, fields, user);
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findByType(
        @PathVariable("type") String type,
        @RequestParam(required = false) Set<String> fields,
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "20") Integer size,
        @Valid @RequestParam(required = false, defaultValue = "asc") String sortOrder,
        @RequestParam(required = false, defaultValue = "created") String sortBy,
        SmartCosmosUser user) {

        return getThingService.getByType(type, fields, page, size, sortOrder, sortBy, user);
    }
}
