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

        ResponseEntity<?> thingResponseEntity = ResponseEntity.noContent().build();
        ResponseEntity<?> metadataResponseEntity = ResponseEntity.noContent().build();

        willReturn(thingResponseEntity).given(thingRestTemplate).delete(anyString(), anyString());
        willReturn(metadataResponseEntity)
            .given(metadataRestTemplate).delete(anyString(), anyString());

        MvcResult mvcResult = mockMvc.perform(
            delete("/{type}/{urn}", ownerType, ownerUrn)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(request().asyncStarted())
            .andReturn();

        this.mockMvc.perform(asyncDispatch(mvcResult))
            .andExpect(status().isNoContent())
            .andReturn();
    }
}
