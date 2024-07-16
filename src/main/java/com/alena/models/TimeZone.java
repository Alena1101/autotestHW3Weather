package com.alena.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Code",
        "Name",
        "GmtOffset",
        "IsDaylightSaving",
        "NextOffsetChange"
})
public class TimeZone implements Serializable
{

    @JsonProperty("Code")
    private String code;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("GmtOffset")
    private Double gmtOffset;
    @JsonProperty("IsDaylightSaving")
    private Boolean isDaylightSaving;
    @JsonProperty("NextOffsetChange")
    private String nextOffsetChange;
    private final static long serialVersionUID = 829182597411402540L;

    @JsonProperty("Code")
    public String getCode() {
        return code;
    }

    @JsonProperty("Code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("GmtOffset")
    public Double getGmtOffset() {
        return gmtOffset;
    }

    @JsonProperty("GmtOffset")
    public void setGmtOffset(Double gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    @JsonProperty("IsDaylightSaving")
    public Boolean getIsDaylightSaving() {
        return isDaylightSaving;
    }

    @JsonProperty("IsDaylightSaving")
    public void setIsDaylightSaving(Boolean isDaylightSaving) {
        this.isDaylightSaving = isDaylightSaving;
    }

    @JsonProperty("NextOffsetChange")
    public String getNextOffsetChange() {
        return nextOffsetChange;
    }

    @JsonProperty("NextOffsetChange")
    public void setNextOffsetChange(String nextOffsetChange) {
        this.nextOffsetChange = nextOffsetChange;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TimeZone.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("gmtOffset");
        sb.append('=');
        sb.append(((this.gmtOffset == null)?"<null>":this.gmtOffset));
        sb.append(',');
        sb.append("isDaylightSaving");
        sb.append('=');
        sb.append(((this.isDaylightSaving == null)?"<null>":this.isDaylightSaving));
        sb.append(',');
        sb.append("nextOffsetChange");
        sb.append('=');
        sb.append(((this.nextOffsetChange == null)?"<null>":this.nextOffsetChange));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}