package net.smartcosmos.edge.things.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import net.smartcosmos.edge.things.ThingEdgeService;
import net.smartcosmos.edge.things.domain.RestPageInformationDto;
import net.smartcosmos.edge.things.domain.things.RestPagedThingResponse;
import net.smartcosmos.edge.things.domain.things.RestThingResponse;
import net.smartcosmos.edge.things.rest.RestTemplateFactory;
import net.smartcosmos.edge.things.rest.request.MetadataRequestFactory;
import net.smartcosmos.edge.things.rest.request.ThingRequestFactory;
import net.smartcosmos.test.config.ThingsEdgeTestConfig;
import net.smartcosmos.test.security.WithMockSmartCosmosUser;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE_URN;
import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.PARAM_FIELDS;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { ThingEdgeService.class, ThingsEdgeTestConfig.class })
@ActiveProfiles("test")
@WithMockSmartCosmosUser
public class GetThingResourceTest {

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
    public void thatLookUpSpecificSucceeds() throws Exception {

        String type = "ownerType";
        String urn = "ownerUrn";
        String tenantUrn = "ownerUrn";
        Boolean active = true;

        RestThingResponse thingResponseBody = RestThingResponse.builder()
            .urn(urn)
            .type(type)
            .tenantUrn(tenantUrn)
            .active(active)
            .build();
        ResponseEntity<RestThingResponse> thingResponseEntity = new ResponseEntity<>(thingResponseBody, HttpStatus.OK);

        Map<String, Object> metadataResponseBody = new HashMap<>();
        metadataResponseBody.put("name", "someName");
        metadataResponseBody.put("description", "someDescription");
        ResponseEntity<Map<String, Object>> metadataResponseEntity = new ResponseEntity<>(metadataResponseBody, HttpStatus.OK);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingResponse.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Map.class));

        mockMvc.perform(
            get(ENDPOINT_TYPE_URN, type, urn)
                .param(PARAM_FIELDS, "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.urn", is(urn)))
            .andExpect(jsonPath("$.type", is(type)))
            .andExpect(jsonPath("$.tenantUrn", is(tenantUrn)))
            .andExpect(jsonPath("$.name", is("someName")))
            .andExpect(jsonPath("$.description", is("someDescription")))
            .andExpect(jsonPath("$.active", is(active)))
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingResponse.class));
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(Map.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatLookUpSpecificSucceedsWithNoAdditionalMetadata() throws Exception {

        String type = "ownerType";
        String urn = "ownerUrn";
        String tenantUrn = "ownerUrn";
        Boolean active = true;

        RestThingResponse thingResponseBody = RestThingResponse.builder()
            .urn(urn)
            .type(type)
            .tenantUrn(tenantUrn)
            .active(active)
            .build();
        ResponseEntity<RestThingResponse> thingResponseEntity = new ResponseEntity<>(thingResponseBody, HttpStatus.OK);

        ResponseEntity<?> metadataResponseEntity = ResponseEntity.notFound()
            .build();

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingResponse.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Map.class));

        mockMvc.perform(
            get(ENDPOINT_TYPE_URN, type, urn)
                .param(PARAM_FIELDS, "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.urn", is(urn)))
            .andExpect(jsonPath("$.type", is(type)))
            .andExpect(jsonPath("$.tenantUrn", is(tenantUrn)))
            .andExpect(jsonPath("$.active", is(active)))
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingResponse.class));
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(Map.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatLookUpSpecificFailsForNonexistentThing() throws Exception {

        String type = "ownerType";
        String urn = "ownerUrn";

        ResponseEntity<?> thingResponseEntity = ResponseEntity.notFound()
            .build();
        ResponseEntity<?> metadataResponseEntity = ResponseEntity.notFound()
            .build();

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestThingResponse.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Map.class));

        mockMvc.perform(
            get(ENDPOINT_TYPE_URN, type, urn)
                .param(PARAM_FIELDS, "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound())
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestThingResponse.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatLookUpByTypeSucceeds() throws Exception {

        String type = "ownerType";

        List<RestThingResponse> data = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            RestThingResponse thingResponseBody = RestThingResponse.builder()
                .urn("urn" + i)
                .type(type)
                .tenantUrn("tenantUrn")
                .active(BooleanUtils.toBoolean(Math.floorMod(i, 2)))
                .build();
            data.add(thingResponseBody);
        }

        RestPageInformationDto pageInformationDto = RestPageInformationDto.builder()
            .size(10)
            .number(1)
            .totalElements(10)
            .totalPages(1)
            .build();

        RestPagedThingResponse thingResponsePage = RestPagedThingResponse.builder()
            .data(data)
            .page(pageInformationDto)
            .build();
        ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(thingResponsePage, HttpStatus.OK);

        Map<String, Object> metadataResponseBody = new HashMap<>();
        metadataResponseBody.put("name", "someName");
        metadataResponseBody.put("description", "someDescription");
        ResponseEntity<Map<String, Object>> metadataResponseEntity = new ResponseEntity<>(metadataResponseBody, HttpStatus.OK);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Map.class));

        mockMvc.perform(
            get(ENDPOINT_TYPE, type)
                .param(PARAM_FIELDS, "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data[*]", hasSize(10)))
            .andExpect(jsonPath("$.data[0].urn", is("urn1")))
            .andExpect(jsonPath("$.data[0].active", is(true)))
            .andExpect(jsonPath("$.data[1].active", is(false)))
            .andExpect(jsonPath("$.data[0].type", is(type)))
            .andExpect(jsonPath("$.page").exists())
            .andExpect(jsonPath("$.page.number", is(1)))
            .andExpect(jsonPath("$.page.size", is(10)))
            .andExpect(jsonPath("$.page.totalPages", is(1)))
            .andExpect(jsonPath("$.page.totalElements", is(10)))
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));
        verify(restTemplate, times(10)).exchange(any(RequestEntity.class), eq(Map.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatLookUpByTypeThingNotFoundFails() throws Exception {

        String type = "ownerType";

        ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));

        mockMvc.perform(
            get(ENDPOINT_TYPE, type)
                .param(PARAM_FIELDS, "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound())
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatLookUpByTypeMetadataNotFoundSucceeds() throws Exception {

        String type = "ownerType";

        List<RestThingResponse> data = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            RestThingResponse thingResponseBody = RestThingResponse.builder()
                .urn("urn" + i)
                .type(type)
                .tenantUrn("tenantUrn")
                .active(BooleanUtils.toBoolean(Math.floorMod(i, 2)))
                .build();
            data.add(thingResponseBody);
        }

        RestPageInformationDto pageInformationDto = RestPageInformationDto.builder()
            .size(10)
            .number(1)
            .totalElements(10)
            .totalPages(1)
            .build();

        RestPagedThingResponse thingResponsePage = RestPagedThingResponse.builder()
            .data(data)
            .page(pageInformationDto)
            .build();
        ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(thingResponsePage, HttpStatus.OK);

        ResponseEntity<Map<String, Object>> metadataResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Map.class));

        mockMvc.perform(
            get(ENDPOINT_TYPE, type)
                .param(PARAM_FIELDS, "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data[*]", hasSize(10)))
            .andExpect(jsonPath("$.data[0].urn", is("urn1")))
            .andExpect(jsonPath("$.data[0].active", is(true)))
            .andExpect(jsonPath("$.data[1].active", is(false)))
            .andExpect(jsonPath("$.data[0].type", is(type)))
            .andExpect(jsonPath("$.page").exists())
            .andExpect(jsonPath("$.page.number", is(1)))
            .andExpect(jsonPath("$.page.size", is(10)))
            .andExpect(jsonPath("$.page.totalPages", is(1)))
            .andExpect(jsonPath("$.page.totalElements", is(10)))
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));
        verify(restTemplate, times(10)).exchange(any(RequestEntity.class), eq(Map.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatLookUpByTypeMetadataBadRequestFromMetadataFails() throws Exception {

        String type = "ownerType";

        List<RestThingResponse> data = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            RestThingResponse thingResponseBody = RestThingResponse.builder()
                .urn("urn" + i)
                .type(type)
                .tenantUrn("tenantUrn")
                .active(BooleanUtils.toBoolean(Math.floorMod(i, 2)))
                .build();
            data.add(thingResponseBody);
        }

        RestPageInformationDto pageInformationDto = RestPageInformationDto.builder()
            .size(10)
            .number(1)
            .totalElements(10)
            .totalPages(1)
            .build();

        RestPagedThingResponse thingResponsePage = RestPagedThingResponse.builder()
            .data(data)
            .page(pageInformationDto)
            .build();
        ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(thingResponsePage, HttpStatus.OK);

        ResponseEntity<Map<String, Object>> metadataResponseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));
        willReturn(metadataResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(Map.class));

        mockMvc.perform(
            get(ENDPOINT_TYPE, type)
                .param(PARAM_FIELDS, "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));
        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(Map.class));

        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void thatLookUpByTypeMetadataBadRequestFromThingsFails() throws Exception {

        String type = "ownerType";

        ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        willReturn(thingResponseEntity).given(restTemplate)
            .exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));

        mockMvc.perform(
            get(ENDPOINT_TYPE, type)
                .param(PARAM_FIELDS, "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(restTemplate, times(1)).exchange(any(RequestEntity.class), eq(RestPagedThingResponse.class));

        verifyNoMoreInteractions(restTemplate);
    }
}
