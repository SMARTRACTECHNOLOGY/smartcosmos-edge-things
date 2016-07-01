package net.smartcosmos.edge.things.client.things.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Create a \&quot;Thing\&quot; in the Things Server.
 **/

@ApiModel(description = "Create a \"Thing\" in the Things Server.")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T09:49:05.075-04:00")
public class RestThingCreate {

    private Boolean active = null;
    private String urn = null;
    private Integer version = null;

    /**
     * Default: true.
     **/
    public RestThingCreate active(Boolean active) {
        this.active = active;
        return this;
    }

    @ApiModelProperty(example = "null", value = "Default: true.")
    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * The programmer provided URN for the Thing. This must be unique to the tenant. If not provided, it will be + generated. Size is database
     * implementation dependent.
     **/
    public RestThingCreate urn(String urn) {
        this.urn = urn;
        return this;
    }

    @ApiModelProperty(example = "null",
                      value = "The programmer provided URN for the Thing. This must be unique to the tenant. If not provided, it will be + " +
                              "generated. Size is database implementation dependent.")
    @JsonProperty("urn")
    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    /**
     **/
    public RestThingCreate version(Integer version) {
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
        RestThingCreate restThingCreate = (RestThingCreate) o;
        return Objects.equals(this.active, restThingCreate.active) &&
               Objects.equals(this.urn, restThingCreate.urn) &&
               Objects.equals(this.version, restThingCreate.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, urn, version);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestThingCreate {\n");

        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    urn: ").append(toIndentedString(urn)).append("\n");
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

