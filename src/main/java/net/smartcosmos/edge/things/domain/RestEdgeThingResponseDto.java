package net.smartcosmos.edge.things.domain;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "version" })
@ApiModel(description = "Unpaged response received when querying for Things.")
public class RestEdgeThingResponseDto<T> {

    private final List<T> data;

    @ConstructorProperties({ "data" })
    protected RestEdgeThingResponseDto(List<T> data) {

        this.data = new ArrayList<>();
        if (data != null) {
            this.data.addAll(data);
        }

    }

    public String toString() {

        return "net.smartcosmos.dto.things.Page.PageBuilder(data=" + this.data + ")";
    }

}
