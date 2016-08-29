package net.smartcosmos.edge.things.domain.things;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang.BooleanUtils;

/**
 * The create response DTO from the Thing REST service.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class RestThingCreateResponseDto {
    private static final int VERSION_1 = 1;
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private int version = VERSION_1;

    private String type;
    private String urn;
    private String tenantUrn;
    private Boolean active;

    @Builder
    @ConstructorProperties({ "urn", "type", "tenantUrn", "active"})
    public RestThingCreateResponseDto(String urn, String type, String tenantUrn, Boolean active) {
        this.urn = urn;
        this.type = type;
        this.tenantUrn = tenantUrn;
        this.active = BooleanUtils.toBoolean(active);
        this.version = VERSION_1;
    }
}
