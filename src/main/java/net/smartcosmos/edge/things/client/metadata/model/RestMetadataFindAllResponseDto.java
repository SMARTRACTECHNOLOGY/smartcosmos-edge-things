package net.smartcosmos.edge.things.client.metadata.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class RestMetadataFindAllResponseDto {

    private CollectionOfRestMetadataFindAllDataItemDto data = null;
    private RestPageInformationDto page = null;
    private Integer version = null;

    /**
     **/
    public RestMetadataFindAllResponseDto data(CollectionOfRestMetadataFindAllDataItemDto data) {
        this.data = data;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("data")
    public CollectionOfRestMetadataFindAllDataItemDto getData() {
        return data;
    }

    public void setData(CollectionOfRestMetadataFindAllDataItemDto data) {
        this.data = data;
    }

    /**
     **/
    public RestMetadataFindAllResponseDto page(RestPageInformationDto page) {
        this.page = page;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("page")
    public RestPageInformationDto getPage() {
        return page;
    }

    public void setPage(RestPageInformationDto page) {
        this.page = page;
    }

    /**
     **/
    public RestMetadataFindAllResponseDto version(Integer version) {
        this.version = version;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestMetadataFindAllResponseDto restMetadataFindAllResponseDto = (RestMetadataFindAllResponseDto) o;
        return Objects.equals(this.data, restMetadataFindAllResponseDto.data) &&
               Objects.equals(this.page, restMetadataFindAllResponseDto.page) &&
               Objects.equals(this.version, restMetadataFindAllResponseDto.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, page, version);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestMetadataFindAllResponseDto {\n");

        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

