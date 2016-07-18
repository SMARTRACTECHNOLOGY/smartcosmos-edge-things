package net.smartcosmos.edge.things.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.domain.RestPageInformationDto;
import net.smartcosmos.edge.things.domain.local.things.RestPagedThingResponse;
import net.smartcosmos.edge.things.domain.local.things.RestThingResponse;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.anySet;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("Duplicates")
public class GetThingResourceTest extends AbstractTestResource {

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

        willReturn(thingResponseEntity).given(thingRestTemplate).findByTypeAndUrn(anyString(), anyString());
        willReturn(metadataResponseEntity).given(metadataRestTemplate).findByTypeAndUrn(anyString(), anyString(), anySet());

        mockMvc.perform(
            get("/{type}/{urn}", type, urn)
                .param("fields", "name,description")
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

        verify(thingRestTemplate, times(1)).findByTypeAndUrn(anyString(), anyString());
        verify(metadataRestTemplate, times(1)).findByTypeAndUrn(anyString(), anyString(), anySet());

        verifyNoMoreInteractions(metadataRestTemplate);
        verifyNoMoreInteractions(thingRestTemplate);
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

        ResponseEntity<?> metadataResponseEntity = ResponseEntity.notFound().build();

        willReturn(thingResponseEntity).given(thingRestTemplate).findByTypeAndUrn(anyString(), anyString());
        willReturn(metadataResponseEntity).given(metadataRestTemplate).findByTypeAndUrn(anyString(), anyString(), anySet());

        mockMvc.perform(
            get("/{type}/{urn}", type, urn)
                .param("fields", "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.urn", is(urn)))
            .andExpect(jsonPath("$.type", is(type)))
            .andExpect(jsonPath("$.tenantUrn", is(tenantUrn)))
            .andExpect(jsonPath("$.active", is(active)))
            .andReturn();

        verify(thingRestTemplate, times(1)).findByTypeAndUrn(anyString(), anyString());
        verify(metadataRestTemplate, times(1)).findByTypeAndUrn(anyString(), anyString(), anySet());

        verifyNoMoreInteractions(metadataRestTemplate);
        verifyNoMoreInteractions(thingRestTemplate);
    }

    @Test
    public void thatLookUpSpecificFailsForNonexistentThing() throws Exception {

        String type = "ownerType";
        String urn = "ownerUrn";

        ResponseEntity<?> thingResponseEntity = ResponseEntity.notFound().build();
        ResponseEntity<?> metadataResponseEntity = ResponseEntity.notFound().build();

        willReturn(thingResponseEntity).given(thingRestTemplate).findByTypeAndUrn(anyString(), anyString());
        willReturn(metadataResponseEntity).given(metadataRestTemplate).findByTypeAndUrn(anyString(), anyString(), anySet());

        mockMvc.perform(
            get("/{type}/{urn}", type, urn)
                .param("fields", "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound())
            .andReturn();

        verify(thingRestTemplate, times(1)).findByTypeAndUrn(anyString(), anyString());

        verifyNoMoreInteractions(metadataRestTemplate);
        verifyNoMoreInteractions(thingRestTemplate);
    }

    @Test
    public void thatLookUpByTypeSucceeds() throws Exception {

        String type = "ownerType";

        List<RestThingResponse> data = new ArrayList<>();
        for (int i=1; i <= 10; i++) {
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

        willReturn(thingResponseEntity).given(thingRestTemplate).findByType(anyString());
        willReturn(metadataResponseEntity).given(metadataRestTemplate).findByTypeAndUrn(anyString(), anyString(), anySet());

        mockMvc.perform(
            get("/{type}", type)
                .param("fields", "name,description")
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

        verify(thingRestTemplate, times(1)).findByType(anyString());
        verify(metadataRestTemplate, times(10)).findByTypeAndUrn(anyString(), anyString(), anySet());

        verifyNoMoreInteractions(metadataRestTemplate);
        verifyNoMoreInteractions(thingRestTemplate);
    }

    @Test
    public void thatLookUpByTypeThingNotFoundFails() throws Exception {

        String type = "ownerType";
                
        ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        willReturn(thingResponseEntity).given(thingRestTemplate).findByType(anyString());

        mockMvc.perform(
            get("/{type}", type)
                .param("fields", "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound())
            .andReturn();

        verify(thingRestTemplate, times(1)).findByType(anyString());
        verify(metadataRestTemplate, times(0)).findByTypeAndUrn(anyString(), anyString(), anySet());

        verifyNoMoreInteractions(metadataRestTemplate);
        verifyNoMoreInteractions(thingRestTemplate);
    }

    @Test
    public void thatLookUpByTypeMetadataNotFoundSucceeds() throws Exception {

        String type = "ownerType";

        List<RestThingResponse> data = new ArrayList<>();
        for (int i=1; i <= 10; i++) {
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

        willReturn(thingResponseEntity).given(thingRestTemplate).findByType(anyString());
        willReturn(metadataResponseEntity).given(metadataRestTemplate).findByTypeAndUrn(anyString(), anyString(), anySet());

        mockMvc.perform(
            get("/{type}", type)
                .param("fields", "name,description")
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

        verify(thingRestTemplate, times(1)).findByType(anyString());
        verify(metadataRestTemplate, times(10)).findByTypeAndUrn(anyString(), anyString(), anySet());

        verifyNoMoreInteractions(metadataRestTemplate);
        verifyNoMoreInteractions(thingRestTemplate);
    }

    @Test
    public void thatLookUpByTypeMetadataBadRequestFromMetadataFails() throws Exception {

        String type = "ownerType";

        List<RestThingResponse> data = new ArrayList<>();
        for (int i=1; i <= 10; i++) {
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

        willReturn(thingResponseEntity).given(thingRestTemplate).findByType(anyString());
        willReturn(metadataResponseEntity).given(metadataRestTemplate).findByTypeAndUrn(anyString(), anyString(), anySet());

        mockMvc.perform(
            get("/{type}", type)
                .param("fields", "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(thingRestTemplate, times(1)).findByType(anyString());
        verify(metadataRestTemplate, times(1)).findByTypeAndUrn(anyString(), anyString(), anySet());

        verifyNoMoreInteractions(metadataRestTemplate);
        verifyNoMoreInteractions(thingRestTemplate);
    }

    @Test
    public void thatLookUpByTypeMetadataBadRequestFromThingsFails() throws Exception {

        String type = "ownerType";

        ResponseEntity<?> thingResponseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        willReturn(thingResponseEntity).given(thingRestTemplate).findByType(anyString());

        mockMvc.perform(
            get("/{type}", type)
                .param("fields", "name,description")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andReturn();

        verify(thingRestTemplate, times(1)).findByType(anyString());

        verifyNoMoreInteractions(metadataRestTemplate);
        verifyNoMoreInteractions(thingRestTemplate);
    }
}