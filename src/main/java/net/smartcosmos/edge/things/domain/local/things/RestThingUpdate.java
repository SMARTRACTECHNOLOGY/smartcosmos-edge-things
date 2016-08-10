package net.smartcosmos.edge.things.domain.local.things;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for REST request to update a Thing.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestThingUpdate {

    private static final int VERSION = 1;
    private final int version = VERSION;

    private Boolean active;
}
