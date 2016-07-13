package net.smartcosmos.edge.things.domain;

import org.junit.*;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;

import static org.junit.Assert.*;

public class RestEdgeThingCreateDtoTest {
    @Test
    public void thatRestThingCreateAlwaysIsActiveWithBuilder() throws Exception {
        RestEdgeThingCreateDto thingCreate = RestEdgeThingCreateDto.builder()
            .urn("urn")
            .build();

        assertTrue(thingCreate.getActive());
    }

    @Test
    public void thatRestThingCreateAlwaysIsActiveWithNoArgsConstructor() throws Exception {
        RestEdgeThingCreateDto thingCreate = new RestEdgeThingCreateDto();

        assertTrue(thingCreate.getActive());
    }

    @Test
    public void thatVersionIsSet() {
        RestThingCreateResponseDto dto = RestThingCreateResponseDto.builder().build();

        assertNotNull(dto.getVersion());
        assertEquals(RestEdgeThingCreateDto.VERSION_1, dto.getVersion());
    }

    @Test(expected = NoSuchMethodException.class)
    public void thatVersionHasNoSetter() throws Exception {
        RestEdgeThingCreateDto.class.getDeclaredMethod("setVersion", int.class);
    }
}
