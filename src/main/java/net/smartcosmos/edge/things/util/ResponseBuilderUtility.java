package net.smartcosmos.edge.things.util;

import java.net.URI;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.domain.RestBadRequestResponseDto;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.URN;

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

    /**
     * Builds a {@link ResponseEntity} based on the HTTP status code and body of another response.
     *
     * @param response the existing response
     * @return a new response with the same HTTP status code and body
     */
    public static ResponseEntity<?> buildForwardingResponse(ResponseEntity<?> response) {

        return ResponseEntity.status(response.getStatusCode())
            .body(response.getBody());
    }

    /**
     * Builds a success response with HTTP status code <i>201 Created</i> using the body of another response, usually from the Things core service.
     *
     * @param response the existing response
     * @return a new response with the same body
     */
    public static ResponseEntity<?> buildCreatedResponse(ResponseEntity<?> response) {

        String urn = "";
        if (response.hasBody() && response.getBody() instanceof Map && ((Map) response.getBody()).containsKey(URN)
            && ((Map) response.getBody()).get(URN) instanceof String) {
            urn = (String) ((Map) response.getBody()).get(URN);
        }

        return ResponseEntity.created(URI.create(urn))
            .body(response.getBody());
    }

}
