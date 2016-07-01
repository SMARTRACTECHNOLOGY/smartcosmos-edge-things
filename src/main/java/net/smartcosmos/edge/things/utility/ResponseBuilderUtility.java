package net.smartcosmos.edge.things.utility;

import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.domain.MetadataResponse;
import net.smartcosmos.edge.things.domain.RestBadRequestResponseDto;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Utility class for building responses.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseBuilderUtility {

    public static ResponseEntity buildBadRequestResponse(int errorCode, String errorMessage) {

        return ResponseEntity.badRequest()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(RestBadRequestResponseDto.builder()
                      .message(errorMessage)
                      .build());
    }

    public static MetadataResponse buildMetadataFailureEventPayload(
        String ownerType, String ownerUrn, Map<String, Object> metadata,
        SmartCosmosUser user) {
        return MetadataResponse.builder()
            .ownerType(ownerType)
            .ownerUrn(ownerUrn)
            .metadata(metadata)
            .tenantUrn(user.getAccountUrn())
            .build();
    }

}
