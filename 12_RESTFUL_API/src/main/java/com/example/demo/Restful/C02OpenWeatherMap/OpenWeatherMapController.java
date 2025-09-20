package com.example.demo.Restful.C02OpenWeatherMap;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/OPEN_WEATHER")
public class OpenWeatherMapController {
    private String server = "https://api.openweathermap.org/data/2.5/weather";
    private String appid = "c5bb9374dfc278f2dc9281f66ed7f0af"; //인증키

    @GetMapping("/{lat}/{lon}")
    public String get(
            @PathVariable("lat") String lat,
            @PathVariable("lon") String lon,
            @PathVariable("units") String units,
            Model model
    ) throws UnsupportedEncodingException { // 예외처리
        log.info("GET /OPEN_WEATHER...");
        // 파라미터 설정( service Key 포함)
        String url = UriComponentsBuilder.fromHttpUrl(server)
                .queryParam("appid",URLEncoder.encode(appid, "UTF-8"))
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", "units")
                .toUriString();
        System.out.println(url);
        // 요청 헤더X
        // 요청 바디X

        RestTemplate rt = new RestTemplate();

        ResponseEntity<Root> response =
                rt.exchange(url, HttpMethod.GET,null,Root.class);

        System.out.println(response.getBody());

        Root root = response.getBody();
        System.out.println("도시명: " + root.getName());
        System.out.println("현재 온도: " + root.getMain().getTemp());
        System.out.println("날씨: " + root.getWeather().get(0).getDescription());

        // 모델에 담아서 템플릿으로 전달
        model.addAttribute("temp", root.getMain().getTemp());
        model.addAttribute("weather", root.getWeather().get(0).getDescription());
        model.addAttribute("city", root.getName());

        return "Opendata/index2";
    }
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    @Data
    private static class Clouds{
        public int all;
    }
    @Data
    private static class Coord{
        public double lon;
        public double lat;
    }
    @Data
    private static class Main{
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;
        public int sea_level;
        public int grnd_level;
    }
    @Data
    private static class Root{
        public Coord coord;
        public ArrayList<Weather> weather;
        public String base;
        public Main main;
        public int visibility;
        public Wind wind;
        public Clouds clouds;
        public int dt;
        public Sys sys;
        public int timezone;
        public int id;
        public String name;
        public int cod;
    }
    @Data
    private static class Sys{
        public String country;
        public int sunrise;
        public int sunset;
    }
    @Data
    private static class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }
    @Data
    private static class Wind{
        public double speed;
        public int deg;
        public double gust;
    }


}
