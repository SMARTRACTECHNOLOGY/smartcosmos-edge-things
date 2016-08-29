package net.smartcosmos.edge.things.domain;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

import net.smartcosmos.edge.things.domain.things.RestThingUpdate;

@Data
@Builder
public class RestThingMetadataUpdateContainer {

    private RestThingUpdate thingRequestBody;
    private Map<String, Object> metadataRequestBody;
}
