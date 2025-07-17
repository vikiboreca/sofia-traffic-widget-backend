package com.example.sofia_traffic_widget.Data;

import lombok.Data;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class SimpleBus {
    private String name;
    private int type;
    private Set<Integer> arriveTimes;
    private String lastStop;

    public SimpleBus(Bus bus) {
        name = bus.getName();
        type = bus.getType();
        arriveTimes = bus.getDetails().stream().map(BusDetails::getTime).collect(Collectors.toSet());
        lastStop = bus.getRoute_name().substring(bus.getRoute_name().indexOf('-')+2);
    }
}
