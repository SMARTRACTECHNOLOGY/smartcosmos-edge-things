package net.smartcosmos.edge.things.service;

import java.util.Map;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.client.things.ApiException;
import net.smartcosmos.edge.things.client.things.api.CreatethingresourceApi;
import net.smartcosmos.edge.things.client.things.model.RestThingCreate;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST things endpoitn to create a thing.
 */
@Slf4j
@Service
public class CreateThingRestServiceDefault implements CreateThingRestService {
    private final ConversionService conversionService;

    @Inject
    public CreateThingRestServiceDefault(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ResponseEntity<?> create(String type, String urn, SmartCosmosUser user) {
        try {
            RestThingCreate createDto = new RestThingCreate().urn(urn);
            return conversionService.convert(getCreateThingClient(user).createObjectUsingPOST(createDto, type)),
                                             ResponseEntity.class);

        } catch (ApiException e) {
            String msg = String.format("Exception creating thing, type: '%s', urn: '%s', cause: '%s'.",
                                       type, urn, e.toString());
            log.error(msg);
            log.debug(msg, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    private CreatethingresourceApi getCreateThingClient(SmartCosmosUser user) {
        return new CreatethingresourceApi();
    }
}
