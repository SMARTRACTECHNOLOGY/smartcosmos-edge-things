package net.smartcosmos.edge.things.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.domain.RestBadRequestResponseDto;

import static org.springframework.http.HttpStatus.CONFLICT;

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
     * @param force
     * @return a new response with the same body
     */
    public static ResponseEntity<?> buildCreateSuccessResponse(ResponseEntity<?> response, Boolean force) {

        if (force && CONFLICT.equals(response.getStatusCode())) {
            // if we got a 409 CONFLICT from Things Core, but forced Metadata upsertion, this is fine - just make it a 200 OK
            return ResponseEntity.ok(response.getBody());
        }

        // if not, we just want to return what we got -- it's usually either a 201 CREATED or 409 CONFLICT
        // (everything else should have ended up with an exception in the handler)
        return buildForwardingResponse(response);
    }

}
