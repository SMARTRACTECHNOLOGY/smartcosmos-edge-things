package net.smartcosmos.edge.things.exception;

import javax.xml.ws.http.HTTPException;

import lombok.Getter;

import org.springframework.http.ResponseEntity;

import static net.smartcosmos.edge.things.util.ResponseBuilderUtility.buildForwardingResponse;

/**
 * Exception to be thrown in case of unexpected HTTP responses on REST calls.
 */
public class RestException extends HTTPException {

    @Getter
    ResponseEntity<?> responseEntity;

    /**
     * Constructor for the HTTPException
     *
     * @param statusCode <code>int</code> for the HTTP status code
     **/
    private RestException(int statusCode) {

        super(statusCode);
    }

    public RestException(ResponseEntity<?> responseEntity) {

        super(responseEntity.getStatusCode()
                  .value());

        this.responseEntity = buildForwardingResponse(responseEntity);
    }
}
