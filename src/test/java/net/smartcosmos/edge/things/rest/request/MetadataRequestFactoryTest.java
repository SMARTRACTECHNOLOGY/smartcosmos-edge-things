package net.smartcosmos.edge.things.rest.request;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.RequestEntity;

import net.smartcosmos.edge.things.config.SmartCosmosEdgeThingsProperties;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MetadataRequestFactoryTest {

    @Spy
    SmartCosmosEdgeThingsProperties edgeThingsProperties;

    @InjectMocks
    MetadataRequestFactory metadataRequestFactory;

    private String serviceName;

    @Before
    public void setUp() {

        serviceName = edgeThingsProperties.getLocal()
            .getMetadata();
    }

    @Test
    public void thatGetRequestBuildsCorrectUrlWithForceTrue() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        final Boolean force = true;
        Map<String, Object> body = new HashMap<>();

        final String expectedUrl = "http://" + serviceName + "/" + ownerType + "/" + ownerUrn + "?force=true";

        RequestEntity requestEntity = metadataRequestFactory.createOrUpsertRequest(ownerType, ownerUrn, force, body);
        assertNotNull(requestEntity);

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void thatGetRequestBuildsCorrectUrlWithForceFalse() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        final Boolean force = false;
        Map<String, Object> body = new HashMap<>();

        final String expectedUrl = "http://" + serviceName + "/" + ownerType + "/" + ownerUrn + "?force=";

        RequestEntity requestEntity = metadataRequestFactory.createOrUpsertRequest(ownerType, ownerUrn, force, body);
        assertNotNull(requestEntity);

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void thatGetFindByOwnerRequestBuildsCorrectUrlWithKeyNames() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        Set<String> keyNames = new HashSet<>();
        keyNames.add("name");
        keyNames.add("description");

        final String expectedUrl = "http://" + serviceName + "/" + ownerType + "/" + ownerUrn + "?keys=name,description";

        RequestEntity requestEntity = metadataRequestFactory.findByOwnerRequest(ownerType, ownerUrn, keyNames);
        assertNotNull(requestEntity);

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void thatFindByOwnerRequestBuildsCorrectUrlWithEmptyKeyNames() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        Set<String> keyNames = new HashSet<>();

        final String expectedUrl = "http://" + serviceName + "/" + ownerType + "/" + ownerUrn + "?keys=";

        RequestEntity requestEntity = metadataRequestFactory.findByOwnerRequest(ownerType, ownerUrn, keyNames);
        assertNotNull(requestEntity);

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }

    @Test
    public void thatGetFindByOwnerRequestBuildsCorrectUrlWithNullKeyNames() throws Exception {

        final String ownerType = "ownerType";
        final String ownerUrn = "ownerUrn";
        Set<String> keyNames = null;

        final String expectedUrl = "http://" + serviceName + "/" + ownerType + "/" + ownerUrn + "?keys=";

        RequestEntity requestEntity = metadataRequestFactory.findByOwnerRequest(ownerType, ownerUrn, keyNames);
        assertNotNull(requestEntity);

        String url = requestEntity.getUrl()
            .toString();

        assertEquals(expectedUrl, url);
    }
}
