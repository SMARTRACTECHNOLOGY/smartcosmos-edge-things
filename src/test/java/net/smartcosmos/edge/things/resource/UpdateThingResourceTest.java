package net.smartcosmos.edge.things.resource;

import java.util.HashMap;

import org.junit.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;

import net.smartcosmos.edge.things.domain.local.metadata.RestMetadataCreateResponseDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.testutil.Testutility;

import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateThingResourceTest extends AbstractTestResource {

    @Test
    public void testThatUpdateThingSucceeds() throws Exception {

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
        requestBody.put("someKey", "someValue");

        willReturn(thingResponseEntity).given(thingRestTemplate).update(anyString(), anyString(), anyObject());
        willReturn(metadataResponseEntity).given(metadataRestTemplate).upsert(anyString(), anyString(), anyMap());

        byte[] jsonDto = Testutility.convertObjectToJsonBytes(requestBody);
        MvcResult mvcResult = this.mockMvc.perform(
            post("/someType").content(jsonDto)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNoContent())
            .andReturn();

        verifyNoMoreInteractions(thingRestTemplate);
        verifyNoMoreInteractions(metadataRestTemplate);
    }
}
