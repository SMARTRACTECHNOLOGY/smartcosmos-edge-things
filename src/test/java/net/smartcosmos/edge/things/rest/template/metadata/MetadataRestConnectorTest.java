package net.smartcosmos.edge.things.rest.template.metadata;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import net.smartcosmos.edge.things.config.SmartCosmosEdgeThingsProperties;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.template.SmartCosmosRequest;

@RunWith(MockitoJUnitRunner.class)
public class MetadataRestConnectorTest {

    @Mock
    RestTemplateFactory restTemplateFactory;

    @Mock
    SmartCosmosEdgeThingsProperties edgeThingsProperties;

    @InjectMocks
    MetadataRestConnector metadataRestConnector;

    @Before
    public void setUp() {

        SmartCosmosEdgeThingsProperties.LocalServiceProperties mockLocalProperties = mock(
                SmartCosmosEdgeThingsProperties.LocalServiceProperties.class);

        doReturn(mockLocalProperties).when(edgeThingsProperties.getLocal());
        doReturn("service").when(mockLocalProperties.getMetadata());
    }

    @Test
    public void thatGetRequestBuildsCorrectUrlWithForceTrue() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        final Boolean force = true;
        Map<String, Object> body = new HashMap<>();

        final String expectedUrl = ownerType + "/" + ownerUrn + "?force=true";

        Method method = MetadataRestConnector.class.getDeclaredMethod("getRequest", String.class, String.class, Boolean.class, Map.class);
        method.setAccessible(true);

        Object methodResponse = method.invoke(metadataRestConnector, ownerType, ownerUrn, force, body);
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

        Method method = MetadataRestConnector.class.getDeclaredMethod("getRequest", String.class, String.class, Boolean.class, Map.class);
        method.setAccessible(true);

        Object methodResponse = method.invoke(metadataRestConnector, ownerType, ownerUrn, force, body);
        assertTrue(methodResponse instanceof SmartCosmosRequest);

        SmartCosmosRequest<?> request = (SmartCosmosRequest) methodResponse;

        assertEquals(expectedUrl, request.getUrl());
    }

    @Test
    public void thatGetFindByOwnerRequestBuildsCorrectUrlWithKeyNames() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        Set<String> keyNames = new HashSet<>();
        keyNames.add("name");
        keyNames.add("description");

        final String expectedUrl = ownerType + "/" + ownerUrn + "?keys=name,description";

        Method method = MetadataRestConnector.class.getDeclaredMethod("getFindByOwnerRequest", String.class, String.class, Set.class);
        method.setAccessible(true);

        Object methodResponse = method.invoke(metadataRestConnector, ownerType, ownerUrn, keyNames);
        assertTrue(methodResponse instanceof SmartCosmosRequest);

        SmartCosmosRequest<?> request = (SmartCosmosRequest) methodResponse;

        assertEquals(expectedUrl, request.getUrl());
    }

    @Test
    public void thatGetFindByOwnerRequestBuildsCorrectUrlWithEmptyKeyNames() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        Set<String> keyNames = new HashSet<>();

        final String expectedUrl = ownerType + "/" + ownerUrn;

        Method method = MetadataRestConnector.class.getDeclaredMethod("getFindByOwnerRequest", String.class, String.class, Set.class);
        method.setAccessible(true);

        Object methodResponse = method.invoke(metadataRestConnector, ownerType, ownerUrn, keyNames);
        assertTrue(methodResponse instanceof SmartCosmosRequest);

        SmartCosmosRequest<?> request = (SmartCosmosRequest) methodResponse;

        assertEquals(expectedUrl, request.getUrl());
    }

    @Test
    public void thatGetFindByOwnerRequestBuildsCorrectUrlWithNullKeyNames() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        Set<String> keyNames = null;

        final String expectedUrl = ownerType + "/" + ownerUrn;

        Method method = MetadataRestConnector.class.getDeclaredMethod("getFindByOwnerRequest", String.class, String.class, Set.class);
        method.setAccessible(true);

        Object methodResponse = method.invoke(metadataRestConnector, ownerType, ownerUrn, keyNames);
        assertTrue(methodResponse instanceof SmartCosmosRequest);

        SmartCosmosRequest<?> request = (SmartCosmosRequest) methodResponse;

        assertEquals(expectedUrl, request.getUrl());
    }
}
