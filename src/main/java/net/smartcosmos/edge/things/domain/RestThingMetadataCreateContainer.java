package net.smartcosmos.edge.things.domain;

import lombok.Builder;
import lombok.Data;
import net.smartcosmos.edge.things.domain.local.things.RestThingCreate;

import java.util.Map;

@Data
@Builder
public class RestThingMetadataCreateContainer {
    private RestThingCreate thingRequestBody;
    private Map<String, Object> metadataRequestBody;
}
