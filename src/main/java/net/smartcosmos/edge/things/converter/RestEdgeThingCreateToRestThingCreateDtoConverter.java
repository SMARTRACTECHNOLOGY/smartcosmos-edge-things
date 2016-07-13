package net.smartcosmos.edge.things.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.domain.RestEdgeThingCreateDto;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreateResponseDto;

/**
 * Converter for {@link RestEdgeThingCreateDto} to {@link RestThingCreateResponseDto} conversion.
 */
@Component
public class RestEdgeThingCreateToRestThingCreateDtoConverter implements Converter<RestEdgeThingCreateDto, RestThingCreateResponseDto> {

    @Override
    public RestThingCreateResponseDto convert(RestEdgeThingCreateDto source) {

        return RestThingCreateResponseDto.builder()
            .urn(source.getUrn())
            .active(source.getIsActive())
            .urn(source.getUrn())
            .build();
    }

    // testing if the WebMcvcAutoConfig will actually pick this thing up as documented in Spring Docs
    //    @Override
    //    public void registerFormatters(FormatterRegistry registry) {
    //        registry.addConverter(this);
    //    }
}
