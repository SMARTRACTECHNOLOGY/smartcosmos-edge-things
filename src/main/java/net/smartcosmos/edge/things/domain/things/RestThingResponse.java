package net.smartcosmos.edge.things.domain.things;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "version" })
public class RestThingResponse {

    private static final int VERSION = 1;
    @Setter(AccessLevel.NONE)
    private int version = VERSION;
    private String urn;
    private String type;
    private String tenantUrn;
    private Boolean active;

    @Builder
    @ConstructorProperties({ "urn", "type", "tenantUrn", "active" })
    public RestThingResponse(String urn, String type, String tenantUrn, Boolean active) {

        this.urn = urn;
        this.type = type;
        this.active = active;
        this.tenantUrn = tenantUrn;

        this.version = VERSION;
    }
}
