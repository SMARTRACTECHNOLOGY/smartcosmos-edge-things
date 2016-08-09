package net.smartcosmos.edge.things.resource;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.anyMap;
import static org.mockito.BDDMockito.verifyNoMoreInteractions;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.anyBoolean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;

import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.testutil.Testutility;

public class CreateThingResourceTest extends AbstractTestResource {

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

        willReturn(thingResponseEntity).given(thingRestConnector).create(anyString(), anyObject());
        willReturn(metadataResponseEntity)
                .given(metadataRestConnector).create(anyString(), anyString(), anyBoolean(), anyMap());

        byte[] jsonDto = Testutility.convertObjectToJsonBytes(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post("/someType").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
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

        willReturn(thingResponseEntity).given(thingRestConnector).create(anyString(), anyObject());

        byte[] jsonDto = Testutility.convertObjectToJsonBytes(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post("/someType").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
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

        verifyNoMoreInteractions(metadataRestConnector);
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

        final ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);

        willReturn(thingResponseEntity).given(thingRestConnector).create(anyString(), anyObject());

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("urn", expectedUrn);

        byte[] jsonDto = Testutility.convertObjectToJsonBytes(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post("/someType").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isConflict());

        verifyNoMoreInteractions(metadataRestConnector);
    }
}
