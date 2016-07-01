package net.smartcosmos.edge.things.converter;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.domain.RestCreateMetadataResponseDto;

@Component
public class RestMetadataCreateResponseDtoToResponseEntityConverter
    implements Converter<RestCreateMetadataResponseDto, ResponseEntity> {

    public ResponseEntity convert(RestCreateMetadataResponseDto dto) {

        if (StringUtils.isNotBlank(dto.getUri())) {
            return ResponseEntity.ok().body(dto);
        }

        return ResponseEntity.notFound().build();
    }

}
