package com.example.sofia_traffic_widget.Service;

import com.example.sofia_traffic_widget.Rest.Models.StopRequest;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class ScrapperService {
    public String GetRawData(StopRequest request) throws Exception {
        //Get token
        String baseUrl = "https://www.sofiatraffic.bg";
        String postUrl = baseUrl + "/bg/trip/getVirtualTable";
        Map<String, String> cookies = GetCookies(postUrl);
        String xToken = GetToken(cookies);
        String jsonBody = GetBody(request.getID(), request.getType());
        //Get Response
        return GetJson(postUrl, xToken, jsonBody, cookies);
    }

    //Just making it simpler
    private Map<String, String> GetCookies(String postUrl) throws Exception {
        Connection.Response getResponse = Jsoup.connect(postUrl)
                .method(Connection.Method.POST).execute();
        return getResponse.cookies();
    }
    private String GetToken(Map<String, String> cookies) throws Exception {
        String TOKEN = cookies.get("XSRF-TOKEN");
        if(TOKEN == null) throw new Exception("No XSRF-TOKEN found in cookies");
        return TOKEN.substring(0, TOKEN.indexOf('%'));
    }
    private String GetBody(String ID, int type){
        return "{\"stop\":\"" + ID + "\",\"type\":" + type + "}";
    }
    private String GetJson(String postUrl, String xToken, String jsonBody, Map<String, String> cookies) throws Exception {
        Connection.Response response =  Jsoup.connect(postUrl)
                .method(Connection.Method.POST)
                .header("Content-Type", "application/json")
                .header("X-XSRF-TOKEN", xToken)
                .requestBody(jsonBody)
                .cookies(cookies)
                .ignoreContentType(true)
                .execute();
        return response.body();
    }
}
