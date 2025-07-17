package com.example.sofia_traffic_widget.Rest.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StopRequest {
    @JsonProperty("stop")
    private String ID;
    @JsonProperty("type")
    private int Type;
}
