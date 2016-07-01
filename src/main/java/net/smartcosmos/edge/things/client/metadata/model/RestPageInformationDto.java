package net.smartcosmos.edge.things.client.metadata.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class RestPageInformationDto {

    private Integer number = null;
    private Integer size = null;
    private Long totalElements = null;
    private Integer totalPages = null;
    private Integer version = null;

    /**
     **/
    public RestPageInformationDto number(Integer number) {
        this.number = number;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     **/
    public RestPageInformationDto size(Integer size) {
        this.size = size;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     **/
    public RestPageInformationDto totalElements(Long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("totalElements")
    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    /**
     **/
    public RestPageInformationDto totalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("totalPages")
    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     **/
    public RestPageInformationDto version(Integer version) {
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
        RestPageInformationDto restPageInformationDto = (RestPageInformationDto) o;
        return Objects.equals(this.number, restPageInformationDto.number) &&
               Objects.equals(this.size, restPageInformationDto.size) &&
               Objects.equals(this.totalElements, restPageInformationDto.totalElements) &&
               Objects.equals(this.totalPages, restPageInformationDto.totalPages) &&
               Objects.equals(this.version, restPageInformationDto.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size, totalElements, totalPages, version);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestPageInformationDto {\n");

        sb.append("    number: ").append(toIndentedString(number)).append("\n");
        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
        sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
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

