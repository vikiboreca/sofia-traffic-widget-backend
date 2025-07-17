package com.example.sofia_traffic_widget.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Bus {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("ext_id")
    private String ext_id;
    @JsonProperty("type")
    private int type;
    @JsonProperty("color")
    private String color;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("route_name")
    private String route_name;
    @JsonProperty("route_id")
    private int route_id;
    @JsonProperty("route_ext_id")
    private String route_ext_id;
    @JsonProperty("st_name")
    private String st_name;
    @JsonProperty("st_name_en")
    private String st_name_en;
    @JsonProperty("st_code")
    private String st_code;
    @JsonProperty("details")
    private List<BusDetails> details;
    @JsonProperty("last_stop")
    private String last_stop;
}
