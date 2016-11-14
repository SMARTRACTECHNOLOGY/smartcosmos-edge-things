package net.smartcosmos.edge.things.domain.things;

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
public class RestThingSignResponseDto {

    private String jwt;
}
