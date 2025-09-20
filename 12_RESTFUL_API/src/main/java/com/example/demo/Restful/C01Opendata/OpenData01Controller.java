package com.example.demo.Restful.C01Opendata;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

//대구광역시_돌발 교통정보 조회 서비스(신)
@Controller
@Slf4j
@RequestMapping("/INCIDENT")
public class OpenData01Controller {
    private String server = "https://apis.data.go.kr/6270000/service/rest/dgincident";
    private String serviceKey = "aee66adefc0e767d8331293756ce173f53307650ec5f8fc2e71b655710a6d0ee"; //인증키
    private String pageNo;
    private String numOfRows;

    @GetMapping(value = "/{pageNo}/{numOfRows}")
    public String get(
            // 필수조건 무조건 값을 넣어야한다
            @PathVariable(value = "pageNo", required = true) String pageNo,
            @PathVariable(value = "numOfRows", required = true) String numOfRows,
            Model model
    ) {
        log.info("GET /INCIDENT....pageNo : " + pageNo + "amount : " + numOfRows);
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;

        // 파라미터 설정( service Key 포함)
        String url = UriComponentsBuilder.fromHttpUrl(server)
                .queryParam("serviceKey",serviceKey)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate(); // restTemplate로 요청함
        // 요청 헤더 설정
//        HttpHeaders header = new HttpHeaders();
//        header.add("key", "value");
//        // 요청 바디 설정(X)
//        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
//        params.add("","");
//        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,header); // 엔티티의 첫번째는 파람을 넣음. //헤더와 바디 합치는?
        // 요청후 응답 확인
//        restTemplate.exchange("URL", "요청METHOD","entity","반환값 받아낼 자료형");
        ResponseEntity<Root> response =
        restTemplate.exchange(url, HttpMethod.GET,null,Root.class);


        //REST TYPE -> Class Type 변환
        System.out.println(response.getBody());

        // 확인
        Root root = response.getBody();
        Body body = root.getBody();
        Items items = body.getItems();
        List<Item> list = items.getItem();
        list.forEach(System.out::println);

        model.addAttribute("list", list);
        return "Opendata/index1";
    }

    // RESPONSE CLASS
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    @Data
    public static class Body{
        public Items items;
        public String numOfRows;
        public String pageNo;
        public String totalCount;
    }
    @Data
    public static class Header{
        public String resultCode;
        public String resultMsg;
    }
    @Data
    public static class Item{
        @JsonProperty("LOCATION")
        public String lOCATION;
        @JsonProperty("INCIDENTTITLE")
        public String iNCIDENTTITLE;
        @JsonProperty("LOGDATE")
        public String lOGDATE;
        @JsonProperty("TROUBLEGRADE")
        public String tROUBLEGRADE;
        @JsonProperty("STARTDATE")
        public String sTARTDATE;
        @JsonProperty("INCIDENTSUBCODE")
        public String iNCIDENTSUBCODE;
        @JsonProperty("LINKID")
        public String lINKID;
        @JsonProperty("REPORTDATE")
        public String rEPORTDATE;
        @JsonProperty("ENDDATE")
        public String eNDDATE;
        @JsonProperty("COORDX")
        public double cOORDX;
        @JsonProperty("INCIDENTCODE")
        public String iNCIDENTCODE;
        @JsonProperty("INCIDENTID")
        public String iNCIDENTID;
        @JsonProperty("COORDY")
        public double cOORDY;
        @JsonProperty("TRAFFICGRADE")
        public String tRAFFICGRADE;
    }
    @Data
    public static class Items{
        public ArrayList<Item> item;
    }
    @Data
    public static class Root{
        public Body body;
        public Header header;
    }


}