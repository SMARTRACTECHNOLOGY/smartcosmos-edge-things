package net.smartcosmos.edge.things.client.metadata.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class RestMetadataFindAllDataItemDto {

    private String dataType = null;
    private String key = null;
    private String ownerType = null;
    private String ownerUrn = null;
    private String tenantUrn = null;
    private Object value = null;
    private Integer version = null;

    /**
     **/
    public RestMetadataFindAllDataItemDto dataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("dataType")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     **/
    public RestMetadataFindAllDataItemDto key(String key) {
        this.key = key;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     **/
    public RestMetadataFindAllDataItemDto ownerType(String ownerType) {
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
    public RestMetadataFindAllDataItemDto ownerUrn(String ownerUrn) {
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
    public RestMetadataFindAllDataItemDto tenantUrn(String tenantUrn) {
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
    public RestMetadataFindAllDataItemDto value(Object value) {
        this.value = value;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("value")
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     **/
    public RestMetadataFindAllDataItemDto version(Integer version) {
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
        RestMetadataFindAllDataItemDto restMetadataFindAllDataItemDto = (RestMetadataFindAllDataItemDto) o;
        return Objects.equals(this.dataType, restMetadataFindAllDataItemDto.dataType) &&
               Objects.equals(this.key, restMetadataFindAllDataItemDto.key) &&
               Objects.equals(this.ownerType, restMetadataFindAllDataItemDto.ownerType) &&
               Objects.equals(this.ownerUrn, restMetadataFindAllDataItemDto.ownerUrn) &&
               Objects.equals(this.tenantUrn, restMetadataFindAllDataItemDto.tenantUrn) &&
               Objects.equals(this.value, restMetadataFindAllDataItemDto.value) &&
               Objects.equals(this.version, restMetadataFindAllDataItemDto.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataType, key, ownerType, ownerUrn, tenantUrn, value, version);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RestMetadataFindAllDataItemDto {\n");

        sb.append("    dataType: ").append(toIndentedString(dataType)).append("\n");
        sb.append("    key: ").append(toIndentedString(key)).append("\n");
        sb.append("    ownerType: ").append(toIndentedString(ownerType)).append("\n");
        sb.append("    ownerUrn: ").append(toIndentedString(ownerUrn)).append("\n");
        sb.append("    tenantUrn: ").append(toIndentedString(tenantUrn)).append("\n");
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

