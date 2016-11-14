package net.smartcosmos.edge.things.converter;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.domain.things.RestThingSignResponseDto;

@Component
public class SignThingResponseToMapConverter implements Converter<RestThingSignResponseDto, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(RestThingSignResponseDto signResponse) {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("jwt", signResponse.getJwt());

        return map;
    }
}
