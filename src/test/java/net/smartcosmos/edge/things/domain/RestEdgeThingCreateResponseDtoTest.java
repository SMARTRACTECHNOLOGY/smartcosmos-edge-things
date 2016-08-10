package net.smartcosmos.edge.things.domain;

import org.junit.*;

import static org.junit.Assert.*;

public class RestEdgeThingCreateResponseDtoTest {

    @Test
    public void thatVersionIsSet() {

        RestEdgeThingCreateResponseDto dto = RestEdgeThingCreateResponseDto.builder()
            .build();

        assertNotNull(dto.getVersion());
        assertEquals(RestEdgeThingCreateResponseDto.VERSION_1, dto.getVersion());
    }

    @Test(expected = NoSuchMethodException.class)
    public void thatVersionHasNoSetter() throws Exception {

        RestEdgeThingCreateResponseDto.class.getDeclaredMethod("setVersion", int.class);
    }

}
