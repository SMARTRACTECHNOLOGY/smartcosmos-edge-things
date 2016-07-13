package net.smartcosmos.edge.things.domain;

import org.junit.*;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;

import static org.junit.Assert.*;

public class RestThingCreateResponseDtoTest {

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
