package net.smartcosmos.edge.things.client.things.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Update a \&quot;Thing\&quot; in the Objects Server.
 **/

@ApiModel(description = "Update a \"Thing\" in the Objects Server.")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T09:49:05.075-04:00")
public class RestThingUpdate {

    private Boolean active = null;
    private Integer version = null;

    /**
     * Default: true
     **/
    public RestThingUpdate active(Boolean active) {
        this.active = active;
        return this;
    }

    @ApiModelProperty(example = "null", value = "Default: true")
    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     **/
    public RestThingUpdate version(Integer version) {
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
        RestThingUpdate restThingUpdate = (RestThingUpdate) o;
        return Objects.equals(this.active, restThingUpdate.active) &&
               Objects.equals(this.version, restThingUpdate.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, version);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestThingUpdate {\n");

        sb.append("    active: ").append(toIndentedString(active)).append("\n");
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

