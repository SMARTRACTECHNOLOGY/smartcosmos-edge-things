package net.smartcosmos.edge.things.domain;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;

@Data
@Builder
public class RestThingMetadataCreateContainer {

    private RestThingCreate thingRequestBody;
    private Map<String, Object> metadataRequestBody;
}
