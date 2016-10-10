package net.smartcosmos.edge.things.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.domain.things.RestThingCreateResponseDto;
import net.smartcosmos.edge.things.service.metadata.CreateMetadataRestService;
import net.smartcosmos.edge.things.service.things.CreateThingRestService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;

import static net.smartcosmos.edge.things.service.CreateThingEdgeServiceDefault.hasMetadataToCreate;
import static net.smartcosmos.edge.things.service.CreateThingEdgeServiceDefault.successfullyCreatedMetadata;
import static net.smartcosmos.edge.things.service.CreateThingEdgeServiceDefault.successfullyCreatedThing;

@RunWith(MockitoJUnitRunner.class)
public class CreateThingEdgeServiceDefaultTest {

    @Mock
    ConversionService conversionService;

    @Mock
    CreateMetadataRestService createMetadataService;

    @Mock
    CreateThingRestService createThingService;

    @InjectMocks
    CreateThingEdgeServiceDefault service;

    @After
    public void tearDown() {

        reset(conversionService, createMetadataService, createThingService);
    }

    @Test
    public void thatMockingWorks() {

        assertNotNull(conversionService);
        assertNotNull(createMetadataService);
        assertNotNull(createThingService);
        assertNotNull(service);
    }

    // region successfullyCreatedThing() {

    @Test
    public void thatSuccessfullyCreatedThingReturnsTrueFor201Created() {

        ResponseEntity thingCreationResponse = ResponseEntity.created(URI.create("uri"))
            .body(RestThingCreateResponseDto.builder()
                      .build());

        assertTrue(successfullyCreatedThing(thingCreationResponse, false));
    }

    @Test
    public void thatSuccessfullyCreatedThingReturnsTrueFor201CreatedAndForce() {

        ResponseEntity thingCreationResponse = ResponseEntity.created(URI.create("uri"))
            .body(RestThingCreateResponseDto.builder()
                      .build());

        assertTrue(successfullyCreatedThing(thingCreationResponse, true));
    }

    @Test
    public void thatSuccessfullyCreatedThingReturnsTrueFor201CreatedWithoutBody() {

        ResponseEntity thingCreationResponse = ResponseEntity.created(URI.create("uri"))
            .build();

        assertFalse(successfullyCreatedThing(thingCreationResponse, false));
    }

    @Test
    public void thatSuccessfullyCreatedThingReturnsTrueFor201CreatedAndForceWithoutBody() {

        ResponseEntity thingCreationResponse = ResponseEntity.created(URI.create("uri"))
            .build();

        assertFalse(successfullyCreatedThing(thingCreationResponse, true));
    }

    @Test
    public void thatSuccessfullyCreatedThingReturnsFalseFor409Conflict() {

        ResponseEntity thingCreationResponse = ResponseEntity.status(HttpStatus.CONFLICT)
            .body(RestThingCreateResponseDto.builder()
                      .build());

        assertFalse(successfullyCreatedThing(thingCreationResponse, false));
    }

    @Test
    public void thatSuccessfullyCreatedThingReturnsTrueFor409ConflictAndForce() {

        ResponseEntity thingCreationResponse = ResponseEntity.status(HttpStatus.CONFLICT)
            .body(RestThingCreateResponseDto.builder()
                      .build());

        assertTrue(successfullyCreatedThing(thingCreationResponse, true));
    }

    @Test
    public void thatSuccessfullyCreatedThingReturnsFalseForError() {

        ResponseEntity thingCreationResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("some Error");

        assertFalse(successfullyCreatedThing(thingCreationResponse, false));
    }

    @Test
    public void thatSuccessfullyCreatedThingReturnsFalseForErrorWithForce() {

        ResponseEntity thingCreationResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("some Error");

        assertFalse(successfullyCreatedThing(thingCreationResponse, true));
    }

    // endregion

    // region hasMetadataToCreate()

    @Test
    public void thatHasMetadataToCreateReturnsFalseForEmptyMap() {

        Map<String, Object> metadataMap = new HashMap<>();

        assertFalse(hasMetadataToCreate(metadataMap));
    }

    @Test
    public void thatHasMetadataToCreateReturnsTrueForMetadataMap() {

        Map<String, Object> metadataMap = new HashMap<>();
        metadataMap.put("key", "value");

        assertTrue(hasMetadataToCreate(metadataMap));
    }

    // endregion

    // region successfullyCreatedMetadata()

    @Test
    public void thatSuccessfullyCreatedMetadataReturnsTrueFor2xxResponse() {

        ResponseEntity metadataResponse = ResponseEntity.ok()
            .build();

        assertTrue(successfullyCreatedMetadata(metadataResponse));
    }

    @Test
    public void thatSuccessfullyCreatedMetadataReturnsFalseForBadRequest() {

        ResponseEntity metadataResponse = ResponseEntity.badRequest()
            .build();

        assertFalse(successfullyCreatedMetadata(metadataResponse));
    }

    @Test
    public void thatSuccessfullyCreatedMetadataReturnsFalseForInternalServerError() {

        ResponseEntity metadataResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .build();

        assertFalse(successfullyCreatedMetadata(metadataResponse));
    }

    // endregion
}
