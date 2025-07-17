package com.example.sofia_traffic_widget.Rest;

import com.example.sofia_traffic_widget.Rest.Models.StopRequest;
import com.example.sofia_traffic_widget.Service.ScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scrap")
public class ScrapperController {
    @Autowired
    ScrapperService scrapperService;

    @PostMapping
    public String test(@RequestBody StopRequest request) throws Exception {
        System.out.println(request);
        return scrapperService.GetRawData(request);
    }
}
