package net.smartcosmos.edge.things.rest.request;

import java.net.URI;

import lombok.Data;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;

@Data
public class SmartCosmosRequest<T> {
    private String serviceName;
    private String url;
    private T requestBody;
    private HttpMethod httpMethod;

    @java.beans.ConstructorProperties({ "serviceName", "url", "requestBody", "httpMethod" })
    SmartCosmosRequest(String serviceName, String url, T requestBody, HttpMethod httpMethod) {
        this.serviceName = serviceName;
        this.url = url;
        this.requestBody = requestBody;
        this.httpMethod = httpMethod;
    }

    public static SmartCosmosRequestBuilder builder() {return new SmartCosmosRequestBuilder();}

    public RequestEntity<T> buildRequest() {

        if (url.startsWith("/")) {
            url = url.substring(1);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        URI uri = URI.create("http://" + serviceName + "/" + url);

        return new RequestEntity<>(requestBody, headers, httpMethod, uri);
    }

    /*
        The Lombok plugin in IntelliJ has some issues with generics, but Lombok itself is actually fine with that.
        It just needs some assistance on the right type.
        However, while Travis and the local machine were able to build the project and run tests, Jenkins wasn't for
        some reason. That's why the @Builder annotation is de-lomboked for now.

        (see https://reinhard.codes/2015/07/14/project-lomboks-builder-annotation-and-generics/)

        There is also a GitHub issue for the Lombok plugin:
        https://github.com/mplushnikov/lombok-intellij-plugin/issues/127
     */

    public static class SmartCosmosRequestBuilder<T> {
        private String serviceName;
        private String url;
        private T requestBody;
        private HttpMethod httpMethod;

        SmartCosmosRequestBuilder() {}

        public SmartCosmosRequest.SmartCosmosRequestBuilder<T> serviceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public SmartCosmosRequest.SmartCosmosRequestBuilder<T> url(String url) {
            this.url = url;
            return this;
        }

        public SmartCosmosRequest.SmartCosmosRequestBuilder<T> requestBody(T requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public SmartCosmosRequest.SmartCosmosRequestBuilder<T> httpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public SmartCosmosRequest<T> build() {
            return new SmartCosmosRequest<>(serviceName, url, requestBody, httpMethod);
        }

        public String toString() {

            return "net.smartcosmos.edge.things.rest.request.SmartCosmosRequest.SmartCosmosRequestBuilder(serviceName=" + this.serviceName +
                   ", url=" +
                   this.url + ", requestBody=" + this.requestBody + ", httpMethod=" + this.httpMethod + ")";
        }
    }
}
