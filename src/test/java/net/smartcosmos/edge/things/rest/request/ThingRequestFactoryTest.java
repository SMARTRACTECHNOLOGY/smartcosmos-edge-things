package net.smartcosmos.edge.things.rest.request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import net.smartcosmos.edge.things.config.SmartCosmosEdgeThingsProperties;
import net.smartcosmos.edge.things.domain.things.RestThingCreate;
import net.smartcosmos.edge.things.domain.things.RestThingUpdate;

import static org.junit.Assert.*;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.FIND_BY_URNS;

@RunWith(MockitoJUnitRunner.class)
public class ThingRequestFactoryTest {

    @Spy
    SmartCosmosEdgeThingsProperties edgeThingsProperties;

    @InjectMocks
    ThingRequestFactory thingRequestFactory;

    private String serviceName;

    @Before
    public void setUp() {

        serviceName = edgeThingsProperties.getLocal()
            .getThings();
    }

    @Test
    public void createRequest() throws Exception {

        final String type = "myType";
        final RestThingCreate restThingCreate = RestThingCreate.builder()
            .build();

        final String expectedUrl = serviceName + "/" + type;

        RequestEntity requestEntity = thingRequestFactory.createRequest(type, restThingCreate);
        assertNotNull(requestEntity);

        assertEquals(HttpMethod.POST, requestEntity.getMethod());
        assertTrue(requestEntity.hasBody());
        assertEquals(restThingCreate, requestEntity.getBody());

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void findByTypeRequest() throws Exception {

        final String type = "myType";

        final String expectedUrl = serviceName + "/" + type + "?page=&size=";

        RequestEntity requestEntity = thingRequestFactory.findByTypeRequest(type, null, null);
        assertNotNull(requestEntity);

        assertEquals(HttpMethod.GET, requestEntity.getMethod());
        assertFalse(requestEntity.hasBody());

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void findByTypeAndUrnsRequest() throws Exception {

        final String type = "myType";

        final String expectedUrl = serviceName + "/" + type + FIND_BY_URNS;

        Set<String> urnStrings = new HashSet<>(Arrays.asList("urn1", "urn2"));
        Map<String, Set<String>> urns = new HashMap<>();
        urns.put("urns", urnStrings);
        RequestEntity requestEntity = thingRequestFactory.findByTypeAndUrnsRequest(type,urns);
        assertNotNull(requestEntity);

        assertEquals(HttpMethod.POST, requestEntity.getMethod());
        assertTrue(requestEntity.hasBody());

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void findSpecificRequest() throws Exception {

        final String type = "myType";
        final String urn = "myUrn";

        final String expectedUrl = serviceName + "/" + type + "/" + urn;

        RequestEntity requestEntity = thingRequestFactory.findSpecificRequest(type, urn);
        assertNotNull(requestEntity);

        assertEquals(HttpMethod.GET, requestEntity.getMethod());
        assertFalse(requestEntity.hasBody());

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void updateRequest() throws Exception {

        final String type = "myType";
        final String urn = "myUrn";
        final RestThingUpdate body = RestThingUpdate.builder()
            .build();

        final String expectedUrl = serviceName + "/" + type + "/" + urn;

        RequestEntity requestEntity = thingRequestFactory.updateRequest(type, urn, body);
        assertNotNull(requestEntity);

        assertEquals(HttpMethod.PUT, requestEntity.getMethod());
        assertTrue(requestEntity.hasBody());
        assertEquals(body, requestEntity.getBody());

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void deleteRequest() throws Exception {

        final String type = "myType";
        final String urn = "myUrn";

        final String expectedUrl = serviceName + "/" + type + "/" + urn;

        RequestEntity requestEntity = thingRequestFactory.deleteRequest(type, urn);
        assertNotNull(requestEntity);

        assertEquals(HttpMethod.DELETE, requestEntity.getMethod());
        assertFalse(requestEntity.hasBody());

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }
}
