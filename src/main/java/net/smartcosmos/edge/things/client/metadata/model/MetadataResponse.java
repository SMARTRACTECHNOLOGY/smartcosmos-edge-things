package net.smartcosmos.edge.things.client.metadata.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class MetadataResponse {

    private Object metadata = null;
    private String ownerType = null;
    private String ownerUrn = null;
    private String tenantUrn = null;
    private Integer version = null;

    /**
     **/
    public MetadataResponse metadata(Object metadata) {
        this.metadata = metadata;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("metadata")
    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    /**
     **/
    public MetadataResponse ownerType(String ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("ownerType")
    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    /**
     **/
    public MetadataResponse ownerUrn(String ownerUrn) {
        this.ownerUrn = ownerUrn;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("ownerUrn")
    public String getOwnerUrn() {
        return ownerUrn;
    }

    public void setOwnerUrn(String ownerUrn) {
        this.ownerUrn = ownerUrn;
    }

    /**
     **/
    public MetadataResponse tenantUrn(String tenantUrn) {
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
    public MetadataResponse version(Integer version) {
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
        MetadataResponse metadataResponse = (MetadataResponse) o;
        return Objects.equals(this.metadata, metadataResponse.metadata) &&
               Objects.equals(this.ownerType, metadataResponse.ownerType) &&
               Objects.equals(this.ownerUrn, metadataResponse.ownerUrn) &&
               Objects.equals(this.tenantUrn, metadataResponse.tenantUrn) &&
               Objects.equals(this.version, metadataResponse.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, ownerType, ownerUrn, tenantUrn, version);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MetadataResponse {\n");

        sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
        sb.append("    ownerType: ").append(toIndentedString(ownerType)).append("\n");
        sb.append("    ownerUrn: ").append(toIndentedString(ownerUrn)).append("\n");
        sb.append("    tenantUrn: ").append(toIndentedString(tenantUrn)).append("\n");
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

