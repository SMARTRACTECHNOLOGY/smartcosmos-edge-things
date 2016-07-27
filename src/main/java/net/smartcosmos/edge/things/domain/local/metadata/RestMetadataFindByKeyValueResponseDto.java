package net.smartcosmos.edge.things.domain.local.metadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.smartcosmos.edge.things.domain.RestPageInformationDto;

import java.util.ArrayList;
import java.util.Collection;


@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@JsonIgnoreProperties({ "version" })
public class RestMetadataFindByKeyValueResponseDto {

    private static final int VERSION_1 = 1;
    private final int version = VERSION_1;

    private Collection<RestMetadataOwnerResponseDto> data = new ArrayList<>();

    private RestPageInformationDto page;
}
