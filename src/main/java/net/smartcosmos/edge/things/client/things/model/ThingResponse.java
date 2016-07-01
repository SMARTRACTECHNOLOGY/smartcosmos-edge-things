package net.smartcosmos.edge.things.client.things.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T09:49:05.075-04:00")
public class ThingResponse {

    private Boolean active = null;
    private String tenantUrn = null;
    private String type = null;
    private String urn = null;
    private Integer version = null;

    /**
     **/
    public ThingResponse active(Boolean active) {
        this.active = active;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     **/
    public ThingResponse tenantUrn(String tenantUrn) {
        this.tenantUrn = tenantUrn;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("tenantUrn")
    public String getTenantUrn() {
        return tenantUrn;
    }

    public void setTenantUrn(String tenantUrn) {
        this.tenantUrn = tenantUrn;
    }

    /**
     **/
    public ThingResponse type(String type) {
        this.type = type;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     **/
    public ThingResponse urn(String urn) {
        this.urn = urn;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("urn")
    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    /**
     **/
    public ThingResponse version(Integer version) {
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
        ThingResponse thingResponse = (ThingResponse) o;
        return Objects.equals(this.active, thingResponse.active) &&
               Objects.equals(this.tenantUrn, thingResponse.tenantUrn) &&
               Objects.equals(this.type, thingResponse.type) &&
               Objects.equals(this.urn, thingResponse.urn) &&
               Objects.equals(this.version, thingResponse.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, tenantUrn, type, urn, version);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ThingResponse {\n");

        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    tenantUrn: ").append(toIndentedString(tenantUrn)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

