package com.example.sofia_traffic_widget.Data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
public class SimpleBus {
    private String name;
    private int type;
    @EqualsAndHashCode.Exclude private List<Integer> arriveTimes;
    private String lastStop;

    public SimpleBus(Bus bus) {
        name = bus.getName();
        type = bus.getType();
        arriveTimes = bus.getDetails().stream().flatMap(k -> bus.getDetails().stream()).map(BusDetails::getTime).distinct().sorted().collect(Collectors.toList());
        lastStop = Station_Splitter(bus.getRoute_name());
    }

    private String Station_Splitter(String route){
        String[] parts = route.split("\\s-\\s");
        return parts[parts.length-1];
    }
}
