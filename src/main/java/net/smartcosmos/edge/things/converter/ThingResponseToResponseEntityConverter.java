package net.smartcosmos.edge.things.converter;

import java.net.URI;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.smartcosmos.edge.things.client.things.model.ThingResponse;
import net.smartcosmos.edge.things.domain.RestCreateMetadataResponseDto;

/**
 * Convert ThingResponse into ResponseEntity
 */
public class ThingResponseToResponseEntityConverter implements Converter<ThingResponse, ResponseEntity> {}


        public ResponseEntity convert(ThingResponse response) {

        if (StringUtils.isNotBlank(response.getUrn())) {
            return ResponseEntity.created(URI.create(response.getUrn())).body(response);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    }
