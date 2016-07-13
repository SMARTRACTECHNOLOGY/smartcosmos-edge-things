package net.smartcosmos.edge.things.domain;

import java.beans.ConstructorProperties;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import org.apache.commons.lang.BooleanUtils;

/**
 * The information returned when a Thing with embedded metadata is created.
 */
@Data
@JsonIgnoreProperties({ "version" })
@ApiModel(description = "Create a \"Thing\" with embedded metadata in the Objects Server.")
public class RestEdgeThingCreateResponseDto {
    public static final int VERSION_1 = 1;
    private final int version = VERSION_1;

    private final String type;
    private final String urn;
    private final Boolean isActive;
    private final String tenantUrn;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private final Map<String, Object> metadata;

    @Builder
    @ConstructorProperties({ "type", "urn", "active", "tenantUrn", "metadata" })
    public RestEdgeThingCreateResponseDto(String type, String urn, Boolean isActive, String tenantUrn, Map<String, Object> metadata) {
        this.type = type;
        this.urn = urn;
        this.tenantUrn = tenantUrn;
        this.isActive = BooleanUtils.toBoolean(isActive);

        this.metadata = new HashMap<>();
        if (metadata != null) {
            this.metadata.putAll(metadata);
        }
    }
}
