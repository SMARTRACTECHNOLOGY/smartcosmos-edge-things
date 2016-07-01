package net.smartcosmos.edge.things.domain;

import org.junit.*;

import static org.junit.Assert.*;

public class RestEdgeThingCreateDtoTest {
    @Test
    public void thatRestThingCreateAlwaysIsActiveWithBuilder() throws Exception {
        RestThingCreateDto thingCreate = RestThingCreateDto.builder()
            .urn("urn")
            .build();

        assertTrue(thingCreate.getIsActive());
    }

    @Test
    public void thatRestThingCreateAlwaysIsActiveWithNoArgsConstructor() throws Exception {
        RestThingCreateDto thingCreate = new RestThingCreateDto();

        assertTrue(thingCreate.getIsActive());
    }

    @Test
    public void thatVersionIsSet() {
        RestThingCreateDto dto = RestThingCreateDto.builder().build();

        assertNotNull(dto.getVersion());
        assertEquals(RestEdgeThingCreateDto.VERSION_1, dto.getVersion());
    }

    @Test(expected = NoSuchMethodException.class)
    public void thatVersionHasNoSetter() throws Exception {
        RestEdgeThingCreateDto.class.getDeclaredMethod("setVersion", int.class);
    }
}
