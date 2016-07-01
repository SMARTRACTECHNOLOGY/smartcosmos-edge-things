package net.smartcosmos.edge.things.client.metadata.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-07-01T11:34:16.398-04:00")
public class DeferredResultOfResponseEntity {

    private Object result = null;
    private Boolean setOrExpired = null;

    /**
     **/
    public DeferredResultOfResponseEntity result(Object result) {
        this.result = result;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("result")
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     **/
    public DeferredResultOfResponseEntity setOrExpired(Boolean setOrExpired) {
        this.setOrExpired = setOrExpired;
        return this;
    }

    @ApiModelProperty(example = "null", value = "")
    @JsonProperty("setOrExpired")
    public Boolean getSetOrExpired() {
        return setOrExpired;
    }

    public void setSetOrExpired(Boolean setOrExpired) {
        this.setOrExpired = setOrExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeferredResultOfResponseEntity deferredResultOfResponseEntity = (DeferredResultOfResponseEntity) o;
        return Objects.equals(this.result, deferredResultOfResponseEntity.result) &&
               Objects.equals(this.setOrExpired, deferredResultOfResponseEntity.setOrExpired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, setOrExpired);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeferredResultOfResponseEntity {\n");

        sb.append("    result: ").append(toIndentedString(result)).append("\n");
        sb.append("    setOrExpired: ").append(toIndentedString(setOrExpired)).append("\n");
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

