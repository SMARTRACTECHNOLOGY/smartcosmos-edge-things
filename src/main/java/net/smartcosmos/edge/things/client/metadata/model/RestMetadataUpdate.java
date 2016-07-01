package net.smartcosmos.edge.things.client.metadata.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Metadata to update.
 **/

@ApiModel(description = "Metadata to update.")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class RestMetadataUpdate {

    private Object value = null;
    private Integer version = null;

    /**
     * The raw value being upserted.
     **/
    public RestMetadataUpdate value(Object value) {
        this.value = value;
        return this;
    }

    @ApiModelProperty(example = "null", required = true, value = "The raw value being upserted.")
    @JsonProperty("value")
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     **/
    public RestMetadataUpdate version(Integer version) {
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
        RestMetadataUpdate restMetadataUpdate = (RestMetadataUpdate) o;
        return Objects.equals(this.value, restMetadataUpdate.value) &&
               Objects.equals(this.version, restMetadataUpdate.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, version);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestMetadataUpdate {\n");

        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

