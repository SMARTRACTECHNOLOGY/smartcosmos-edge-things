package net.smartcosmos.edge.things.rest.errorhandler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.http.OAuth2ErrorHandler;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.stereotype.Component;

/**
 * Error handler component that accepts all HTTP error responses and does not throw any exceptions.
 */
@Component
public class ProxyOauth2ErrorHandler extends OAuth2ErrorHandler {

    @Autowired
    public ProxyOauth2ErrorHandler(OAuth2ProtectedResourceDetails resource) {

        super(resource);
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        return false;
    }
}
