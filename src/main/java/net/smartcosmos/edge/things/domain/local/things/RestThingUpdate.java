package net.smartcosmos.edge.things.domain.local.things;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for REST local to update a Thing.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Update a \"Thing\" in the Objects Server.")
public class RestThingUpdate
{
    private static final int VERSION = 1;
    @Setter(AccessLevel.NONE)
    private int version = VERSION;

    @ApiModelProperty(notes = "Default: true")
    private Boolean active;

    @Builder
    @ConstructorProperties({"active"})
    public RestThingUpdate(Boolean active)
    {
        this.active = active != null ? active : true;

        this.version = VERSION;
    }
}
