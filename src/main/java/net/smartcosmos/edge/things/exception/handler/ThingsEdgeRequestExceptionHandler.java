package net.smartcosmos.edge.things.exception.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ThingsEdgeRequestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "message";

    /**
     * <p>Customize the response for {@link HttpStatusCodeException} that, e.g., is thrown when requests to other services fail and are not handled
     * .</p>
     * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}. The same response will be returned.</p>
     *
     * @param exception the exception
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(HttpStatusCodeException.class)
    protected ResponseEntity<?> handleHttpStatusCodeException(HttpStatusCodeException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = exception.getStatusCode();

        String exceptionResponseBody = exception.getResponseBodyAsString();

        String msg = String.format("Exception on request %s: %s: %s",
                                   request,
                                   exception.toString(),
                                   exceptionResponseBody);
        log.warn(msg);
        log.debug(msg, exception);

        return handleExceptionInternal(exception, exception.getResponseBodyAsByteArray(), headers, status, request);
    }

    /**
     * <p>Customize the response for {@link ResourceAccessException} that is thrown when a core service is not available, and the exception is not
     * handled elsewhere.</p>
     * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}. It will return a response with HTTP status <i>500 Internal
     * Server Error</i> and an informative message that nevertheless exposes details about the system.</p>
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceAccessException.class)
    protected ResponseEntity<?> handleConnectException(ResourceAccessException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        String exceptionResponseBody = exception.getMessage();

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put(MESSAGE, "Request processing failed due to an internal error. See the service logs for further details.");

        String msg = String.format("Exception on request %s: %s: %s",
                                   request,
                                   exception.toString(),
                                   exceptionResponseBody);
        log.warn(msg);
        log.debug(msg, exception);

        return handleExceptionInternal(exception, responseBody, headers, status, request);
    }
}
