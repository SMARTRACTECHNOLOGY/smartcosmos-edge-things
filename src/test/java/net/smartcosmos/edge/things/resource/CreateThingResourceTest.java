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
import net.smartcosmos.edge.things.domain.CodeMessageErrorResponse;
import net.smartcosmos.edge.things.domain.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.domain.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;
import net.smartcosmos.test.config.ResourceTestConfig;
import net.smartcosmos.test.security.WithMockSmartCosmosUser;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_FORCE;
import static net.smartcosmos.test.util.TestUtil.json;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { ThingEdgeService.class, ResourceTestConfig.class })
@ActiveProfiles("test")
@WithMockSmartCosmosUser
public class CreateThingResourceTest {

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

    /**
     * Test that creating a Thing is successful.
     *
     * @throws Exception
     */
    @Test
    public void thatCreateThingSucceeds() throws Exception {

        final String expectedUrn = "urn";
        final String expectedType = "someType";
        final String expectedTenantUrn = "tenantUrn";
        final Boolean expectedActive = false;

        final RestThingCreateResponseDto thingResponseBody = RestThingCreateResponseDto.builder()
            .urn(expectedUrn)
            .type(expectedType)
            .tenantUrn(expectedTenantUrn)
            .active(expectedActive)
            .build();
        final ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(thingResponseBody, HttpStatus.CREATED);

        final RestMetadataCreateResponseDto metadataResponseBody = RestMetadataCreateResponseDto.builder()
            .uri("/" + expectedType + "/" + expectedUrn)
            .build();
        final ResponseEntity<?> metadataResponseEntity = new ResponseEntity<>(metadataResponseBody, HttpStatus.OK);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", expectedUrn);
        requestBody.put("active", expectedActive);
        requestBody.put("name", "someName");

        HashMap<String, Object> metadataMap = new HashMap<>();
        requestBody.put("name", "someName");

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post(ENDPOINT_TYPE, "someType")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.urn", is(expectedUrn)))
            .andExpect(jsonPath("$.type", is(expectedType)))
            .andExpect(jsonPath("$.tenantUrn", is(expectedTenantUrn)))
            .andExpect(jsonPath("$.active", is(expectedActive)));

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));
        verifyNoMoreInteractions(restTemplate);
    }

    /**
     * Test that creating a Thing without metadata is successful, but doesn't call the metadata service.
     *
     * @throws Exception
     */
    @Test
    public void thatCreateThingWithoutMetadataSucceeds() throws Exception {

        final String expectedUrn = "urn";
        final String expectedType = "someType";
        final String expectedTenantUrn = "tenantUrn";
        final Boolean expectedActive = false;

        final RestThingCreateResponseDto thingResponseBody = RestThingCreateResponseDto.builder()
            .urn(expectedUrn)
            .type(expectedType)
            .tenantUrn(expectedTenantUrn)
            .active(expectedActive)
            .build();
        final ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(thingResponseBody, HttpStatus.CREATED);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", expectedUrn);
        requestBody.put("active", expectedActive);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post(ENDPOINT_TYPE, "someType")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.urn", is(expectedUrn)))
            .andExpect(jsonPath("$.type", is(expectedType)))
            .andExpect(jsonPath("$.tenantUrn", is(expectedTenantUrn)))
            .andExpect(jsonPath("$.active", is(expectedActive)));

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));
        verifyNoMoreInteractions(restTemplate);
    }

    /**
     * Tests that creating an already existent thing fails.
     *
     * @throws Exception
     */
    @Test
    public void thatCreateDuplicateThingFails() throws Exception {

        final String expectedUrn = "urn";
        final String expectedType = "someType";

        final RestThingCreateResponseDto thingResponseBody = RestThingCreateResponseDto.builder()
            .urn("someOtherUrn")
            .type(expectedType)
            .tenantUrn("someTenantUrn")
            .active(true)
            .build();
        final ResponseEntity<?> thingResponseEntity = ResponseEntity.status(HttpStatus.CONFLICT)
            .body(thingResponseBody);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", expectedUrn);

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post(ENDPOINT_TYPE, "someType")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isConflict());

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatCreateDuplicateThingWithMetadataForceSucceeds() throws Exception {

        final String expectedUrn = "urn";
        final String expectedType = "someType";

        final RestThingCreateResponseDto thingResponseBody = RestThingCreateResponseDto.builder()
            .urn("someOtherUrn")
            .type(expectedType)
            .tenantUrn("someTenantUrn")
            .active(true)
            .build();
        final ResponseEntity<?> thingResponseEntity = ResponseEntity.status(HttpStatus.CONFLICT)
            .body(thingResponseBody);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));

        final RestMetadataCreateResponseDto metadataResponseBody = RestMetadataCreateResponseDto.builder()
            .uri("/" + expectedType + "/" + expectedUrn)
            .build();
        final ResponseEntity<?> metadataResponseEntity = new ResponseEntity<>(metadataResponseBody, HttpStatus.OK);

        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", expectedUrn);
        requestBody.put("name", "someName");

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post(ENDPOINT_TYPE, "someType")
                .param(PARAM_FORCE, "true")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isCreated());

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatCreateDuplicateThingWithMetadataNoForceFails() throws Exception {

        final String expectedUrn = "urn";
        final String expectedType = "someType";

        final RestThingCreateResponseDto thingResponseBody = RestThingCreateResponseDto.builder()
            .urn("someOtherUrn")
            .type(expectedType)
            .tenantUrn("someTenantUrn")
            .active(true)
            .build();
        final ResponseEntity<?> thingResponseEntity = ResponseEntity.status(HttpStatus.CONFLICT)
            .body(thingResponseBody);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", expectedUrn);
        requestBody.put("name", "someName");

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post(ENDPOINT_TYPE, "someType")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isConflict());

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatCreateThingWithInvalidMetadataReturnsErrorMessage() throws Exception {

        final String expectedUrn = "urn";
        final String expectedType = "someType";
        final String expectedTenantUrn = "tenantUrn";
        final Boolean expectedActive = false;

        final int errorCode = -1;
        final String errorMessage = "some error message";

        final RestThingCreateResponseDto thingResponseBody = RestThingCreateResponseDto.builder()
            .urn(expectedUrn)
            .type(expectedType)
            .tenantUrn(expectedTenantUrn)
            .active(expectedActive)
            .build();
        final ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(thingResponseBody, HttpStatus.CREATED);

        final CodeMessageErrorResponse metadataResponseBody = CodeMessageErrorResponse.builder()
            .code(errorCode)
            .message(errorMessage)
            .build();
        final ResponseEntity<?> metadataResponseEntity = new ResponseEntity<>(metadataResponseBody, HttpStatus.BAD_REQUEST);

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", expectedUrn);
        requestBody.put("active", expectedActive);
        requestBody.put("invalid", "someInvalid");

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));

        byte[] jsonDto = json(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post(ENDPOINT_TYPE, "someType")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonDto))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.code", is(errorCode)))
            .andExpect(jsonPath("$.message", is(errorMessage)));

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingCreateResponseDto.class));
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestMetadataCreateResponseDto.class));
        verifyNoMoreInteractions(restTemplate);
    }
}
