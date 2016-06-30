package net.smartcosmos.edge.things.service;

import java.util.Map;
import javax.inject.Inject;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.context.request.async.DeferredResult;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation for {@Link net.smartcosmos.edge.things.service.CreateThingService}
 */
public class CreateThingServiceDefault implements CreateThingService {
    private final EventSendingService eventSendingService;
    private final ConversionService conversionService;

    @Inject
    public CreateThingServiceDefault(EventSendingService eventSendingService, ConversionService conversionService) {
        this.eventSendingService = eventSendingService;
        this.conversionService = conversionService;
    }

    @Override
    @Async
    public void create(DeferredResult<ResponseEntity> response, String type, Map<String, Object> metadataMap, Boolean force, SmartCosmosUser user) {
        
    }
}
