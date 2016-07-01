package net.smartcosmos.edge.things.domain;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * The create response DTO from the Thing REST service.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "version" })
public class RestThingCreateResponseDto {
    public static final int VERSION_1 = 1;
    @Setter(AccessLevel.NONE)
    private int version = VERSION_1;
    private final String urn;
    private final String type;
    private final String tenantUrn;
    private final Boolean active;

    @Builder
    @ConstructorProperties({ "urn", "type", "tenantUrn", "active" })
    public RestThingCreateResponseDto(String urn, String type, String tenantUrn, Boolean active) {
        this.urn = urn;
        this.type = type;
        this.active = active;
        this.tenantUrn = tenantUrn;

        this.version = VERSION_1;
    }

}
