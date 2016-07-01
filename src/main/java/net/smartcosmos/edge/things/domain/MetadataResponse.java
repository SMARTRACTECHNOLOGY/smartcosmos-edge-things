package net.smartcosmos.edge.things.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties({ "version" })
public class MetadataResponse {

    private static final int VERSION = 1;

    private final int version = VERSION;

    private final String ownerType;

    private final String ownerUrn;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private final Map<String, Object> metadata;

    private final String tenantUrn;

    @Builder
    @java.beans.ConstructorProperties({ "ownerType", "ownerUrn", "metadata", "tenantUrn" })
    public MetadataResponse(String ownerType, String ownerUrn, Map<String, Object> metadata, String tenantUrn) {
        this.ownerType = ownerType;
        this.ownerUrn = ownerUrn;
        this.tenantUrn = tenantUrn;

        this.metadata = new HashMap<>();
        if (metadata != null) {
            this.metadata.putAll(metadata);
        }
    }
}

