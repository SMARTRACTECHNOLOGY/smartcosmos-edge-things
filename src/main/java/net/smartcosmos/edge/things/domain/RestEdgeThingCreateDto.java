package net.smartcosmos.edge.things.domain;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang.BooleanUtils;

/**
 * Data Transfer Object for REST request to create a new Thing.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Create a \"Thing\" with embedded metadata in the Objects Server.")
public class RestEdgeThingCreateDto {
    public static final int VERSION_1 = 1;
    @Setter(AccessLevel.NONE)
    private int version = VERSION_1;

    @ApiModelProperty(notes = "The programmer provided URN for the Thing. This must be unique to the tenant. If not provided, it will be + " +
                              "generated. Size is database implementation dependent.", required = false)
    private String urn;

    @ApiModelProperty(notes = "Default: true.")
    private Boolean isActive = true;

    @Builder // used in tests
    @ConstructorProperties({ "urn", "isActive" })
    public RestEdgeThingCreateDto(String urn, String type, Boolean isActive) {
        this.urn = urn;
        this.isActive = BooleanUtils.toBoolean(isActive);
        this.version = VERSION_1;
    }
}
