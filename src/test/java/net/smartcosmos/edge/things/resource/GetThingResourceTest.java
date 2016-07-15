package net.smartcosmos.edge.things.resource;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.domain.local.things.RestThingResponse;

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
}
