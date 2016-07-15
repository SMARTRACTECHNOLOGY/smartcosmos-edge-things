package net.smartcosmos.edge.things.domain.local.things;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"version"})
public class RestThingResponse {

    private static final int VERSION = 1;
    @Setter(AccessLevel.NONE)
    private int version = VERSION;
    private final String urn;
    private final String type;
    private final String tenantUrn;
    private final Boolean active;

    @Builder
    @ConstructorProperties({"urn", "type", "tenantUrn", "active"})
    public RestThingResponse(String urn, String type, String tenantUrn, Boolean active) {
        this.urn = urn;
        this.type = type;
        this.active = active;
        this.tenantUrn = tenantUrn;

        this.version = VERSION;
    }
}
