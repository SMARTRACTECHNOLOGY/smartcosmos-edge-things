package net.smartcosmos.edge.things.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@JsonIgnoreProperties({ "version" })
public class RestPageInformationDto {

    private static final int VERSION_1 = 1;
    private final int version = VERSION_1;

    private int size;
    private long totalElements;
    private int totalPages;
    private int number;
}
