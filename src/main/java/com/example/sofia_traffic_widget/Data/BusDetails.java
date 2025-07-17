package com.example.sofia_traffic_widget.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BusDetails {
    @JsonProperty("t")
    private int time;
    @JsonProperty("ac")
    private boolean ac;
    @JsonProperty("wheelchairs")
    private boolean wheelchairs;
    @JsonProperty("bikes")
    private boolean bikes;
}
