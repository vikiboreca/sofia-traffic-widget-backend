package com.example.sofia_traffic_widget.Rest;

import com.example.sofia_traffic_widget.Data.Bus;
import com.example.sofia_traffic_widget.Data.SimpleBus;
import com.example.sofia_traffic_widget.Rest.Models.StopRequest;
import com.example.sofia_traffic_widget.Service.MapperService;
import com.example.sofia_traffic_widget.Service.ScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scrap")
public class ScrapperController {

    @Autowired
    private ScrapperService scrapperService;
    @Autowired
    private MapperService mapperService;

    @PostMapping
    public List<SimpleBus> test(@RequestBody StopRequest request) throws Exception {
        String RawJson = scrapperService.GetRawData(request);
        Map<String, Bus> busMap = mapperService.getBusMap(RawJson);
        List<SimpleBus> busList = new ArrayList<>();
        for(var bus : busMap.values()) {
            busList.add(new SimpleBus(bus));
        }
        return busList;
    }
}
