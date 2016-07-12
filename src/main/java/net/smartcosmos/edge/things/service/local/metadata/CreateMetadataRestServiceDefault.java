package net.smartcosmos.edge.things.service.local.metadata;

import java.util.Map;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.edge.things.ResultCode;
import net.smartcosmos.edge.things.client.metadata.ApiException;
import net.smartcosmos.edge.things.client.metadata.api.CreatemetadataresourceApi;
import net.smartcosmos.edge.things.utility.ResponseBuilderUtility;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Default implementation of the metadata service.
 */
@Slf4j
@Service
public class CreateMetadataRestServiceDefault implements CreateMetadataRestService {
    private final ConversionService conversionService;

    @Inject
    public CreateMetadataRestServiceDefault(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ResponseEntity<?> create(String ownerType, String ownerUrn, boolean force, Map<String, Object> metadataMap, SmartCosmosUser user) {
        if (metadataMap.isEmpty()) {
            return ResponseBuilderUtility.buildBadRequestResponse(ResultCode.ERR_EMPTY_REQUEST, NO_METADATA_SPECIFIED_IN_REQUEST_BODY);
        }

        try {
            return conversionService.convert(getCreateMetadataClient(user).createMetadataUsingPOST(ownerType, ownerUrn, metadataMap, force),
                                             ResponseEntity.class);

        } catch (ApiException e) {
            String msg = String.format("Exception creating metadata, ownerType: '%s', ownerUrn: '%s', metadata: '%s', force: %b, cause: '%s'.",
                                       ownerType, ownerUrn, metadataMap.toString(), force, e.toString());
            log.error(msg);
            log.debug(msg, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    private CreatemetadataresourceApi getCreateMetadataClient(SmartCosmosUser user) {
        return new CreatemetadataresourceApi();
    }
}
