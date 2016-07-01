package net.smartcosmos.edge.things.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.domain.RestEdgeThingCreateDto;
import net.smartcosmos.edge.things.domain.RestThingCreateDto;

/**
 * Converter for {@link RestEdgeThingCreateDto} to {@link RestThingCreateDto} conversion.
 */
@Component
public class RestEdgeThingCreateToRestThingCreateDtoConverter implements Converter<RestEdgeThingCreateDto, RestThingCreateDto> {

    @Override
    public RestThingCreateDto convert(RestEdgeThingCreateDto source) {

        return RestThingCreateDto.builder()
            .urn(source.getUrn())
            .isActive(source.getIsActive())
            .urn(source.getUrn())
            .build();
    }

    // testing if the WebMcvcAutoConfig will actually pick this thing up as documented in Spring Docs
    //    @Override
    //    public void registerFormatters(FormatterRegistry registry) {
    //        registry.addConverter(this);
    //    }
}
