package net.smartcosmos.edge.things.resource;

import java.util.HashMap;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
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
import net.smartcosmos.edge.things.domain.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;
import net.smartcosmos.test.config.ResourceTestConfig;
import net.smartcosmos.test.security.WithMockSmartCosmosUser;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE_URN;
import static net.smartcosmos.test.util.TestUtil.json;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { ThingEdgeService.class, ResourceTestConfig.class })
@ActiveProfiles("test")
@WithMockSmartCosmosUser
public class UpdateThingResourceTest {

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

        Mockito.reset(restTemplate);
    }

    @Test
    public void testThatUpdateThingSucceeds() throws Exception {

        final String urn = "urn";
        final String type = "someType";
        final Boolean expectedActive = false;

        final ResponseEntity<?> thingResponseEntity = ResponseEntity.noContent()
            .build();

        final RestMetadataCreateResponseDto metadataResponseBody = RestMetadataCreateResponseDto.builder()
            .uri("/" + type + "/" + urn)
            .build();
        final ResponseEntity<?> metadataResponseEntity = new ResponseEntity<>(metadataResponseBody, HttpStatus.OK);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", urn);
        requestBody.put("active", expectedActive);
        requestBody.put("name", "someName");
        requestBody.put("someKey", "someValue");

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Void.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            put(ENDPOINT_TYPE_URN, type, urn)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNoContent())
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(Void.class));
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void testThatUpdateNonexistentThingFails() throws Exception {

        final String urn = "urn";
        final String type = "someType";
        final Boolean expectedActive = false;

        final ResponseEntity<?> thingResponseEntity = ResponseEntity.notFound()
            .build();
        final ResponseEntity<?> metadataResponseEntity = ResponseEntity.notFound()
            .build();

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", urn);
        requestBody.put("active", expectedActive);
        requestBody.put("name", "someName");
        requestBody.put("someKey", "someValue");

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Void.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            put(ENDPOINT_TYPE_URN, type, urn)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNotFound())
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(Void.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void testThatUpdateThingConstraintViolationFails() throws Exception {

        final String urn = "urn";
        final String type = "someType";
        final Boolean expectedActive = false;

        final ResponseEntity<?> thingResponseEntity = ResponseEntity.noContent()
            .build();
        final ResponseEntity<?> metadataResponseEntity = ResponseEntity.badRequest()
            .build();

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", urn);
        requestBody.put("active", expectedActive);
        requestBody.put("name", "someName");
        requestBody.put("someKey", "someValue");

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Void.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            put(ENDPOINT_TYPE_URN, type, urn)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(Void.class));
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));

        verifyNoMoreInteractions(restTemplate);
    }
}
