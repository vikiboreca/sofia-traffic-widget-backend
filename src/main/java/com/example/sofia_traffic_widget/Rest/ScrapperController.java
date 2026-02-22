package com.example.sofia_traffic_widget.Rest;

import com.example.sofia_traffic_widget.Data.Bus;
import com.example.sofia_traffic_widget.Data.SimpleBus;
import com.example.sofia_traffic_widget.Rest.Models.StopRequest;
import com.example.sofia_traffic_widget.Service.GTFSRTService;
import com.example.sofia_traffic_widget.Service.MapperService;
import com.example.sofia_traffic_widget.Service.ScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scrap")
public class ScrapperController {

    @Autowired
    private ScrapperService scrapperService;
    @Autowired
    private MapperService mapperService;

    @Autowired
    private GTFSRTService gtfsRTService;

    @PostMapping
    public List<SimpleBus> GetData(@RequestBody StopRequest request) throws Exception {
        String RawJson = scrapperService.GetRawData(request);
        Map<String, Bus> busMap = mapperService.getBusMap(RawJson);
        List<SimpleBus> busList = new ArrayList<>();
        for (var bus : busMap.values()) {
            SimpleBus simpleBus = new SimpleBus(bus);
            if(!busList.contains(simpleBus)) {
                busList.add(simpleBus);
            }
            else{
                SimpleBus oldBus = busList.get(busList.indexOf(simpleBus));
                oldBus.getArriveTimes().addAll(simpleBus.getArriveTimes());
                oldBus.setArriveTimes(oldBus.getArriveTimes().stream().distinct().collect(Collectors.toList()));
                oldBus.getArriveTimes().sort(Integer::compareTo);
            }
        }
        busList = busList.stream().sorted(Comparator.comparing(SimpleBus::getType)).toList();
        busList = busList.stream()
                .sorted(Comparator.comparingInt(bus -> {
                    try {
                        return Integer.parseInt(bus.getName());
                    } catch (NumberFormatException e) {
                        return Integer.MAX_VALUE;
                    }
                }))
                .toList();

        return busList;
    }

    @PostMapping("/test")
    public Map<String, Bus> test(@RequestBody StopRequest request) throws Exception {
        String RawJson = scrapperService.GetRawData(request);
        return mapperService.getBusMap(RawJson);
    }
    @GetMapping("/new")
    public String getNewData(){
        gtfsRTService.getData();
        return "New Data";

    }
}
