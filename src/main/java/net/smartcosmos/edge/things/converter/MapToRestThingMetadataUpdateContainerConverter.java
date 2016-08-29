package net.smartcosmos.edge.things.converter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import net.smartcosmos.edge.things.domain.RestThingMetadataUpdateContainer;
import net.smartcosmos.edge.things.domain.things.RestThingUpdate;

@Component
public class MapToRestThingMetadataUpdateContainerConverter implements Converter<Map<String, Object>, RestThingMetadataUpdateContainer> {

    private final String ACTIVE_FIELD_NAME = "active";

    @Override
    public RestThingMetadataUpdateContainer convert(Map<String, Object> map) {

        Map<String, Object> metadataMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(map)) {
            metadataMap.putAll(map);
        }

        RestThingUpdate thingUpdate = null;

        if (metadataMap.containsKey(ACTIVE_FIELD_NAME)) {
            Object activeObject = metadataMap.remove(ACTIVE_FIELD_NAME);

            Boolean active = null;
            if (activeObject instanceof Boolean) {
                active = (Boolean) activeObject;
            } else if (activeObject instanceof String) {
                active = Boolean.parseBoolean(String.valueOf(activeObject));
            }
            thingUpdate = new RestThingUpdate(active);
        }

        return RestThingMetadataUpdateContainer.builder()
            .thingRequestBody(thingUpdate)
            .metadataRequestBody(metadataMap)
            .build();
    }
}
