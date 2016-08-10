package net.smartcosmos.edge.things.resource;

import java.util.HashMap;

import org.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;

import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.testutil.Testutility;

import static org.mockito.BDDMockito.anyMap;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateThingResourceTest extends AbstractTestResource {

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

        willReturn(thingResponseEntity).given(thingRestConnector)
            .update(anyString(), anyString(), anyObject());
        willReturn(metadataResponseEntity).given(metadataRestConnector)
            .upsert(anyString(), anyString(), anyMap());

        byte[] jsonDto = Testutility.convertObjectToJsonBytes(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            put("/{type}/{urn}", type, urn).content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNoContent())
            .andReturn();

        verify(thingRestConnector, times(1)).update(anyString(), anyString(), anyObject());
        verifyNoMoreInteractions(thingRestConnector);

        verify(metadataRestConnector, times(1)).upsert(anyString(), anyString(), anyMap());
        verifyNoMoreInteractions(metadataRestConnector);
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

        willReturn(thingResponseEntity).given(thingRestConnector)
            .update(anyString(), anyString(), anyObject());
        willReturn(metadataResponseEntity).given(metadataRestConnector)
            .upsert(anyString(), anyString(), anyMap());

        byte[] jsonDto = Testutility.convertObjectToJsonBytes(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            put("/{type}/{urn}", type, urn).content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNotFound())
            .andReturn();

        verify(thingRestConnector, times(1)).update(anyString(), anyString(), anyObject());
        verifyNoMoreInteractions(thingRestConnector);

        verifyNoMoreInteractions(metadataRestConnector);
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

        willReturn(thingResponseEntity).given(thingRestConnector)
            .update(anyString(), anyString(), anyObject());
        willReturn(metadataResponseEntity).given(metadataRestConnector)
            .upsert(anyString(), anyString(), anyMap());

        byte[] jsonDto = Testutility.convertObjectToJsonBytes(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            put("/{type}/{urn}", type, urn).content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(thingRestConnector, times(1)).update(anyString(), anyString(), anyObject());
        verifyNoMoreInteractions(thingRestConnector);

        verify(metadataRestConnector, times(1)).upsert(anyString(), anyString(), anyMap());
        verifyNoMoreInteractions(metadataRestConnector);
    }
}
