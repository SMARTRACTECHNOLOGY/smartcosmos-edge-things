package net.smartcosmos.edge.things.converter;

import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;

@Component
public class MapToRestThingCreateConverter implements Converter<Map<String, Object>, RestThingCreate> {

    private final String URN_FIELD_NAME = "urn";
    private final String ACTIVE_FIELD_NAME = "active";
    private final Boolean ACTIVE_DEFAULT_VALUE = true;

    @Override
    public RestThingCreate convert(Map<String, Object> map) {

        RestThingCreate thingCreate = new RestThingCreate();

        if (map.containsKey(URN_FIELD_NAME)) {
            Object urnObject = map.remove(URN_FIELD_NAME);
            if (urnObject instanceof String)
            thingCreate.setUrn(String.valueOf(urnObject));
        }

        if (map.containsKey(ACTIVE_FIELD_NAME)) {
            Object activeObject = map.remove(ACTIVE_FIELD_NAME);

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

        return thingCreate;
    }
}
