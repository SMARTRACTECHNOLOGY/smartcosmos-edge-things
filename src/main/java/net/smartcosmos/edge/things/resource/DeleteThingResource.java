package net.smartcosmos.edge.things.resource;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.edge.things.service.DeleteThingEdgeService;
import net.smartcosmos.security.user.SmartCosmosUser;
import net.smartcosmos.spring.SmartCosmosRdao;

@SmartCosmosRdao
@Slf4j
@ConditionalOnProperty(prefix = "smartcosmos.endpoints.edge.things", name = "enabled", matchIfMissing = true)
public class DeleteThingResource {

    DeleteThingEdgeService deleteThingService;

    @Inject
    public DeleteThingResource(DeleteThingEdgeService deleteThingService) {
        this.deleteThingService = deleteThingService;
    }

    @RequestMapping(value = "{type}/{urn}", method = RequestMethod.DELETE)
    public DeferredResult<ResponseEntity> delete(
        @PathVariable("type") String type,
        @PathVariable("urn") String urn,
        SmartCosmosUser user) {

        DeferredResult<ResponseEntity> response = new DeferredResult<>();
        deleteThingService.delete(response, type, urn, user);
        return response;
    }
}
