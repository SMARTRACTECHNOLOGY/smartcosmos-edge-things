package net.smartcosmos.edge.things.resource;

import org.junit.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static net.smartcosmos.edge.things.resource.ThingEdgeEndpointConstants.ENDPOINT_TYPE_URN;

public class DeleteThingResourceTest extends AbstractTestResource {

    /**
     * Test that deleting a Thing is successful.
     *
     * @throws Exception
     */
    @Test
    public void thatDeleteThingSucceeds() throws Exception {

        String ownerType = "ownerType";
        String ownerUrn = "ownerUrn";

        ResponseEntity<?> thingResponseEntity = ResponseEntity.noContent()
            .build();
        ResponseEntity<?> metadataResponseEntity = ResponseEntity.noContent()
            .build();

        willReturn(thingResponseEntity).given(thingRestConnector)
            .delete(anyString(), anyString());
        willReturn(metadataResponseEntity)
            .given(metadataRestConnector)
            .delete(anyString(), anyString());

        MvcResult mvcResult = mockMvc.perform(
            delete(ENDPOINT_TYPE_URN, ownerType, ownerUrn)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNoContent())
            .andReturn();

        verify(thingRestConnector, times(1)).delete(anyString(), anyString());
        verify(metadataRestConnector, times(1)).delete(anyString(), anyString());

        verifyNoMoreInteractions(metadataRestConnector);
        verifyNoMoreInteractions(thingRestConnector);
    }

    @Test
    public void thatDeleteThingFailsIfThingNonexistent() throws Exception {

        String ownerType = "ownerType";
        String ownerUrn = "ownerUrn";

        ResponseEntity<?> thingResponseEntity = ResponseEntity.notFound()
            .build();

        willReturn(thingResponseEntity).given(thingRestConnector)
            .delete(anyString(), anyString());

        MvcResult mvcResult = mockMvc.perform(
            delete(ENDPOINT_TYPE_URN, ownerType, ownerUrn)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNotFound())
            .andReturn();

        verify(thingRestConnector, times(1)).delete(anyString(), anyString());
        verify(metadataRestConnector, times(0)).delete(anyString(), anyString());

        verifyNoMoreInteractions(metadataRestConnector);
        verifyNoMoreInteractions(thingRestConnector);
    }

    @Test
    public void thatDeleteThingSucceedsWithoutMetadata() throws Exception {

        String ownerType = "ownerType";
        String ownerUrn = "ownerUrn";

        ResponseEntity<?> thingResponseEntity = ResponseEntity.noContent()
            .build();
        ResponseEntity<?> metadataResponseEntity = ResponseEntity.notFound()
            .build();

        willReturn(thingResponseEntity).given(thingRestConnector)
            .delete(anyString(), anyString());
        willReturn(metadataResponseEntity)
            .given(metadataRestConnector)
            .delete(anyString(), anyString());

        MvcResult mvcResult = mockMvc.perform(
            delete(ENDPOINT_TYPE_URN, ownerType, ownerUrn)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNoContent())
            .andReturn();

        verify(thingRestConnector, times(1)).delete(anyString(), anyString());
        verify(metadataRestConnector, times(1)).delete(anyString(), anyString());

        verifyNoMoreInteractions(metadataRestConnector);
        verifyNoMoreInteractions(thingRestConnector);
    }
}
