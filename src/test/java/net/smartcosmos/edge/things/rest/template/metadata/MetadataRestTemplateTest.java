package net.smartcosmos.edge.things.rest.template.metadata;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.mockito.*;
import org.springframework.web.client.RestOperations;

import net.smartcosmos.edge.things.rest.template.SmartCosmosRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MetadataRestTemplateTest {

    private final String serviceName = "service";
    private final RestOperations mockRestOperations = Mockito.mock(RestOperations.class);
    MetadataRestTemplate restTemplate = new MetadataRestTemplate(mockRestOperations, serviceName);

    @Test
    public void thatGetRequestBuildsCorrectUrlWithForceTrue() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        final Boolean force = true;
        Map<String, Object> body = new HashMap<>();

        final String expectedUrl = ownerType + "/" + ownerUrn + "?force=true";

        Method method = MetadataRestTemplate.class.getDeclaredMethod("getRequest", String.class, String.class, Boolean.class, Map.class);
        method.setAccessible(true);

        Object methodResponse = method.invoke(restTemplate, ownerType, ownerUrn, force, body);
        assertTrue(methodResponse instanceof SmartCosmosRequest);

        SmartCosmosRequest<?> request = (SmartCosmosRequest) methodResponse;

        assertEquals(expectedUrl, request.getUrl());
    }

    @Test
    public void thatGetRequestBuildsCorrectUrlWithForceFalse() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        final Boolean force = false;
        Map<String, Object> body = new HashMap<>();

        final String expectedUrl = ownerType + "/" + ownerUrn;

        Method method = MetadataRestTemplate.class.getDeclaredMethod("getRequest", String.class, String.class, Boolean.class, Map.class);
        method.setAccessible(true);

        Object methodResponse = method.invoke(restTemplate, ownerType, ownerUrn, force, body);
        assertTrue(methodResponse instanceof SmartCosmosRequest);

        SmartCosmosRequest<?> request = (SmartCosmosRequest) methodResponse;

        assertEquals(expectedUrl, request.getUrl());
    }
}
