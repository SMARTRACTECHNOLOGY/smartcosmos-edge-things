package net.smartcosmos.edge.things.rest.request;

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

        final String expectedUrl = "http://" + serviceName + "/" + type;

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

        final String expectedUrl = "http://" + serviceName + "/" + type + "?page=&size=";

        RequestEntity requestEntity = thingRequestFactory.findByTypeRequest(type, null, null);
        assertNotNull(requestEntity);

        assertEquals(HttpMethod.GET, requestEntity.getMethod());
        assertFalse(requestEntity.hasBody());

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void findSpecificRequest() throws Exception {

        final String type = "myType";
        final String urn = "myUrn";

        final String expectedUrl = "http://" + serviceName + "/" + type + "/" + urn;

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

        final String expectedUrl = "http://" + serviceName + "/" + type + "/" + urn;

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

        final String expectedUrl = "http://" + serviceName + "/" + type + "/" + urn;

        RequestEntity requestEntity = thingRequestFactory.deleteRequest(type, urn);
        assertNotNull(requestEntity);

        assertEquals(HttpMethod.DELETE, requestEntity.getMethod());
        assertFalse(requestEntity.hasBody());

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }
}
