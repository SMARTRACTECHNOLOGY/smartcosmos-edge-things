package net.smartcosmos.edge.things.domain;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"version"})
@ApiModel(description = "Paged response received when querying for Things.")
public class RestEdgePagedThingResponseDto<T> {

    private final List<T> data;
    private final RestPageInformationDto page;

    @ConstructorProperties({"data", "page"})
    protected RestEdgePagedThingResponseDto(List<T> data, RestPageInformationDto page) {
        this.data = new ArrayList<>();
        if (data != null) {
            this.data.addAll(data);
        }

        this.page = page;
    }

    /*
        The Lombok plugin in IntelliJ has some issues with generics, but Lombok itself is actually fine with that.
        It just needs some assistance on the right type.
        However, while Travis and the local machine were able to build the project and run tests, Jenkins wasn't for
        some reason. That's why the @Builder annotation is de-lomboked for now.

        (see https://reinhard.codes/2015/07/14/project-lomboks-builder-annotation-and-generics/)

        There is also a GitHub issue for the Lombok plugin:
        https://github.com/mplushnikov/lombok-intellij-plugin/issues/127
     */

    public static <T>PageBuilder<T> builder() {
        return new PageBuilder<>();
    }

    public static class PageBuilder<T> {
        private List<T> data;
        private RestPageInformationDto page;

        PageBuilder() {
        }

        public RestEdgePagedThingResponseDto.PageBuilder<T> data(List<T> data) {
            this.data = data;
            return this;
        }

        public RestEdgePagedThingResponseDto.PageBuilder<T> page(RestPageInformationDto page) {
            this.page = page;
            return this;
        }

        public RestEdgePagedThingResponseDto<T> build() {
            return new RestEdgePagedThingResponseDto<>(data, page);
        }

        public String toString() {
            return "net.smartcosmos.dto.things.Page.PageBuilder(data=" + this.data + ", page=" + this.page + ")";
        }
    }
}
