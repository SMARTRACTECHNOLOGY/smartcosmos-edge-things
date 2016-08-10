package net.smartcosmos.edge.things.domain.local.things;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.lang.BooleanUtils;

/**
 * Data Transfer Object for REST request to create a new Thing.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestThingCreate {

    public static final Boolean ACTIVE_DEFAUL_VALUE = true;

    private static final int VERSION = 1;
    private final int version = VERSION;

    private String urn;
    private Boolean active = ACTIVE_DEFAUL_VALUE;

    @Builder // used in tests
    @ConstructorProperties({ "urn", "type", "active" })
    public RestThingCreate(String urn, String type, Boolean active) {

        this.urn = urn;
        this.active = BooleanUtils.toBooleanDefaultIfNull(active, ACTIVE_DEFAUL_VALUE);
    }
}
