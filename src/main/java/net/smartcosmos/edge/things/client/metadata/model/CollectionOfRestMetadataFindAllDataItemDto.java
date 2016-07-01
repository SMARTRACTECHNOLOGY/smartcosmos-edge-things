package net.smartcosmos.edge.things.client.metadata.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class CollectionOfRestMetadataFindAllDataItemDto {

    private Boolean empty = null;

    /**
     **/
    public CollectionOfRestMetadataFindAllDataItemDto empty(Boolean empty) {
        this.empty = empty;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("empty")
    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionOfRestMetadataFindAllDataItemDto collectionOfRestMetadataFindAllDataItemDto = (CollectionOfRestMetadataFindAllDataItemDto) o;
        return Objects.equals(this.empty, collectionOfRestMetadataFindAllDataItemDto.empty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empty);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollectionOfRestMetadataFindAllDataItemDto {\n");

        sb.append("    empty: ").append(toIndentedString(empty)).append("\n");
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

