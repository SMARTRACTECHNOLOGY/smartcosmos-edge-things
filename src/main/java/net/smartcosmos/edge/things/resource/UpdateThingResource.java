package net.smartcosmos.edge.things.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

import net.smartcosmos.edge.things.service.UpdateThingEdgeService;
import net.smartcosmos.security.user.SmartCosmosUser;
import net.smartcosmos.spring.SmartCosmosRdao;

@SmartCosmosRdao
@Slf4j
@ConditionalOnProperty(prefix = "smartcosmos.endpoints.edge.things", name = "enabled", matchIfMissing = true)
public class UpdateThingResource {

    private UpdateThingEdgeService updateThingService;

    @Inject
    public UpdateThingResource(UpdateThingEdgeService updateThingService) {
        this.updateThingService = updateThingService;
    }

    @RequestMapping(method = RequestMethod.PUT,
                    value = "{type}/{urn}",
                    produces = APPLICATION_JSON_UTF8_VALUE,
                    consumes = APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity> update(
        @PathVariable String type,
        @PathVariable String urn,
        @RequestBody @Valid Map<String, Object> requestBody,
        SmartCosmosUser user) {

        DeferredResult<ResponseEntity> response = new DeferredResult<>();
        updateThingService.update(response, type, urn, requestBody, user);
        return response;
    }
}
