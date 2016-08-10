package net.smartcosmos.edge.things.converter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import net.smartcosmos.edge.things.domain.RestThingMetadataCreateContainer;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;

@Component
public class MapToRestThingMetadataCreateContainerConverter implements Converter<Map<String, Object>, RestThingMetadataCreateContainer> {

    private final String URN_FIELD_NAME = "urn";
    private final String ACTIVE_FIELD_NAME = "active";
    private final Boolean ACTIVE_DEFAULT_VALUE = true;

    @Override
    public RestThingMetadataCreateContainer convert(Map<String, Object> map) {

        Map<String, Object> metadataMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(map)) {
            metadataMap.putAll(map);
        }

        RestThingCreate thingCreate = new RestThingCreate();

        if (metadataMap.containsKey(URN_FIELD_NAME)) {
            Object urnObject = metadataMap.remove(URN_FIELD_NAME);
            if (urnObject instanceof String) {
                thingCreate.setUrn(String.valueOf(urnObject));
            }
        }

        if (metadataMap.containsKey(ACTIVE_FIELD_NAME)) {
            Object activeObject = metadataMap.remove(ACTIVE_FIELD_NAME);

            Boolean active;
            if (activeObject instanceof Boolean) {
                active = (Boolean) activeObject;
            } else if (activeObject instanceof String) {
                active = Boolean.parseBoolean(String.valueOf(activeObject));
            } else {
                active = ACTIVE_DEFAULT_VALUE;
            }
            thingCreate.setActive(active);
        }

        return RestThingMetadataCreateContainer.builder()
            .thingRequestBody(thingCreate)
            .metadataRequestBody(metadataMap)
            .build();
    }
}
