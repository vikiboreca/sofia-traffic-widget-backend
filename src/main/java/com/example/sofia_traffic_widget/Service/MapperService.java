package com.example.sofia_traffic_widget.Service;

import com.example.sofia_traffic_widget.Data.Bus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MapperService {
    @Autowired
    private ObjectMapper objectMapper;

    public Map<String, Bus> getBusMap(String json) throws Exception {
        Map<String, Bus> busMap = objectMapper.readValue(json, new TypeReference<Map<String, Bus>>() {});
        return busMap;
    }
}
