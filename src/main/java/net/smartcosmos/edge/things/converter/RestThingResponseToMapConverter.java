package net.smartcosmos.edge.things.converter;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.domain.things.RestThingResponse;

@Component
public class RestThingResponseToMapConverter implements Converter<RestThingResponse, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(RestThingResponse thingResponse) {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("urn", thingResponse.getUrn());
        map.put("type", thingResponse.getType());
        map.put("tenantUrn", thingResponse.getTenantUrn());
        map.put("active", thingResponse.getActive());

        return map;
    }
}
