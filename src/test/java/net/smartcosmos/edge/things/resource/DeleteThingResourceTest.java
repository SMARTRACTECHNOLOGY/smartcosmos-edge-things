package net.smartcosmos.edge.things.resource;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import net.smartcosmos.edge.things.ThingEdgeService;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;
import net.smartcosmos.test.config.ResourceTestConfig;
import net.smartcosmos.test.security.WithMockSmartCosmosUser;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE_URN;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { ThingEdgeService.class, ResourceTestConfig.class })
@ActiveProfiles("test")
@WithMockSmartCosmosUser
public class DeleteThingResourceTest {

    @Autowired
    RestTemplateFactory restTemplateFactory;

    @Autowired
    ThingRequestFactory thingRequestFactory;

    @Autowired
    MetadataRequestFactory metadataRequestFactory;

    @Mock
    RestTemplate restTemplate;

    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        when(restTemplateFactory.getRestTemplate()).thenReturn(restTemplate);

        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply(springSecurity())
            .build();
    }

    @After
    public void tearDown() {

        Mockito.reset(restTemplate, thingRequestFactory, metadataRequestFactory);
    }

    /**
     * Test that deleting a Thing is successful.
     *
     * @throws Exception
     */
    @Test
    public void thatDeleteThingSucceeds() throws Exception {

        String ownerType = "ownerType";
        String ownerUrn = "ownerUrn";

        ResponseEntity thingResponseEntity = ResponseEntity.noContent()
            .build();
        ResponseEntity metadataResponseEntity = ResponseEntity.noContent()
            .build();

        RequestEntity thingRequestEntity = mock(RequestEntity.class);
        when(thingRequestFactory.deleteRequest(anyString(), anyString())).thenReturn(thingRequestEntity);
        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(eq(thingRequestEntity), eq(Void.class));

        RequestEntity metadataRequestEntity = mock(RequestEntity.class);
        when(metadataRequestFactory.deleteAllForOwnerRequest(anyString(), anyString())).thenReturn(metadataRequestEntity);
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Void.class));

        MvcResult mvcResult = mockMvc.perform(
            delete(ENDPOINT_TYPE_URN, ownerType, ownerUrn)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNoContent())
            .andReturn();

        verify(restTemplate, times(1)).exchange(eq(thingRequestEntity), eq(Void.class));
        verify(restTemplate, times(1)).exchange(eq(metadataRequestEntity), eq(Void.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatDeleteThingFailsIfThingNonexistent() throws Exception {

        String ownerType = "ownerType";
        String ownerUrn = "ownerUrn";

        ResponseEntity<?> thingResponseEntity = ResponseEntity.notFound()
            .build();

        RequestEntity thingRequestEntity = mock(RequestEntity.class);
        when(thingRequestFactory.deleteRequest(anyString(), anyString())).thenReturn(thingRequestEntity);
        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(eq(thingRequestEntity), eq(Void.class));

        MvcResult mvcResult = mockMvc.perform(
            delete(ENDPOINT_TYPE_URN, ownerType, ownerUrn)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNotFound())
            .andReturn();

        verify(restTemplate, times(1)).exchange(eq(thingRequestEntity), eq(Void.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatDeleteThingSucceedsWithoutMetadata() throws Exception {

        String ownerType = "ownerType";
        String ownerUrn = "ownerUrn";

        ResponseEntity<?> thingResponseEntity = ResponseEntity.noContent()
            .build();
        ResponseEntity<?> metadataResponseEntity = ResponseEntity.notFound()
            .build();

        RequestEntity thingRequestEntity = mock(RequestEntity.class);
        when(thingRequestFactory.deleteRequest(anyString(), anyString())).thenReturn(thingRequestEntity);
        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(eq(thingRequestEntity), eq(Void.class));

        RequestEntity metadataRequestEntity = mock(RequestEntity.class);
        when(metadataRequestFactory.deleteAllForOwnerRequest(anyString(), anyString())).thenReturn(metadataRequestEntity);
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(eq(metadataRequestEntity), eq(Void.class));

        MvcResult mvcResult = mockMvc.perform(
            delete(ENDPOINT_TYPE_URN, ownerType, ownerUrn)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNoContent())
            .andReturn();

        verify(restTemplate, times(1)).exchange(eq(thingRequestEntity), eq(Void.class));
        verify(restTemplate, times(1)).exchange(eq(metadataRequestEntity), eq(Void.class));

        verifyNoMoreInteractions(restTemplate);
    }
}
