package net.smartcosmos.edge.things.rest.errorhandler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.http.OAuth2ErrorHandler;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.stereotype.Component;

/**
 * Error handler component that, in addition to the typical "No Error" HTTP response status codes, also accepts 409 Conflict as no error.
 */
@Component
public class ConflictOauth2ErrorHandler extends OAuth2ErrorHandler {

    @Autowired
    public ConflictOauth2ErrorHandler(OAuth2ProtectedResourceDetails resource) {

        super(resource);
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        HttpStatus status = response.getStatusCode();
        return super.hasError(response) && !HttpStatus.CONFLICT.equals(status);
    }
}
