
package com.alena.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Metric",
        "Imperial"
})
public class Elevation implements Serializable
{

    @JsonProperty("Metric")
    private Metric metric;
    @JsonProperty("Imperial")
    private Imperial imperial;
    private final static long serialVersionUID = -2908027158428671275L;

    @JsonProperty("Metric")
    public Metric getMetric() {
        return metric;
    }

    @JsonProperty("Metric")
    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    @JsonProperty("Imperial")
    public Imperial getImperial() {
        return imperial;
    }

    @JsonProperty("Imperial")
    public void setImperial(Imperial imperial) {
        this.imperial = imperial;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Elevation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("metric");
        sb.append('=');
        sb.append(((this.metric == null)?"<null>":this.metric));
        sb.append(',');
        sb.append("imperial");
        sb.append('=');
        sb.append(((this.imperial == null)?"<null>":this.imperial));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}