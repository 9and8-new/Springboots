//package com.example.demo.Restful.Opendata;
//
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RestController
//@Slf4j
//@RequestMapping("/WEATHER")
//public class OpenData02Controller {
//    private String server = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
//    private String serviceKey = "aee66adefc0e767d8331293756ce173f53307650ec5f8fc2e71b655710a6d0ee"; //인증키
//    private String pageNo;
//    private String numOfRows;
//    private String dataType;
//    private String base_date;
//    private String base_time;
//    private String nx;
//    private String ny;
//
//    @GetMapping(value = "/{pageNo}/{numOfRows}/{dataType}/{base_date}/{base_time}/{nx}/{ny}")
//    public void get(
//            // 필수조건 무조건 값을 넣어야한다
//            @PathVariable(value = "pageNo", required = true) String pageNo,
//            @PathVariable(value = "numOfRows", required = true) String numOfRows,
//            @PathVariable(value = "dataType", required = true) String dataType,
//            @PathVariable(value = "base_date", required = true) String base_date,
//            @PathVariable(value = "base_time", required = true) String base_time,
//            @PathVariable(value = "nx", required = true) String nx,
//            @PathVariable(value = "ny", required = true) String ny,
//            Model model
//    ) {
//        log.info("GET /WEATHER....pageNo : {} numOfRows : {} dataType : {} base_date : {} base_time : {}  nx = {} ny = {}"
//        ,pageNo,numOfRows,dataType,base_date,base_time,nx,ny);
//        this.pageNo = pageNo;
//        this.numOfRows = numOfRows;
//        this.dataType = dataType;
//        this.base_date = base_date;
//        this.base_time = base_time;
//        this.nx = nx;
//        this.ny = ny;
//
//
//        // 파라미터 설정( service Key 포함)
//        String url = UriComponentsBuilder.fromHttpUrl(server)
//                .queryParam("serviceKey",serviceKey)
//                .queryParam("pageNo", pageNo)
//                .queryParam("dataType", dataType)
//                .queryParam("base_date", base_date)
//                .queryParam("base_time", base_time)
//                .queryParam("nx", nx)
//                .queryParam("ny", ny)
//                .toUriString();
//        // 이거쓰니까 오류뜸.
////        String url = server;
////        url+="?serviceKey=" + serviceKey;
////        url+="?pageNo=" + pageNo;
////        url+="?dataType=" + dataType;
////        url+="?base_date=" + base_date;
////        url+="?base_time=" + base_time;
////        url+="?nx=" + nx;
////        url+="?ny=" + ny;
//        RestTemplate restTemplate = new RestTemplate(); // restTemplate로 요청함
//        // 요청 헤더 설정
////        HttpHeaders header = new HttpHeaders();
////        header.add("key", "value");
////        // 요청 바디 설정(X)
////        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
////        params.add("","");
////        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,header); // 엔티티의 첫번째는 파람을 넣음. //헤더와 바디 합치는?
//        // 요청후 응답 확인
////        restTemplate.exchange("URL", "요청METHOD","entity","반환값 받아낼 자료형");
//            ResponseEntity<Root> response =
//            restTemplate.exchange(url, HttpMethod.GET,null,Root.class);
//
//
//        //REST TYPE -> Class Type 변환
//        System.out.println(response.getBody());
////
////        // 확인
//        Root root = response.getBody();
//        Response rest = root.getResponse();
//        Body body = rest.getBody();
//        Items items = body.getItems();
//        List<Item> list = items.getItem();
//        list.forEach(System.out::println);
////
////        model.addAttribute("list", list);
////        return "Opendata/index1";
//    }
//    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
///* ObjectMapper om = new ObjectMapper();
//Root root = om.readValue(myJsonString, Root.class); */
//    @Data
//    private static class Body{
//        public String dataType;
//        public Items items;
//        public int pageNo;
//        public int numOfRows;
//        public int totalCount;
//    }
//    @Data
//    private static class Header{
//        public String resultCode;
//        public String resultMsg;
//    }
//    @Data
//    private static class Item{
//        public String baseDate;
//        public String baseTime;
//        public String category;
//        public int nx;
//        public int ny;
//        public String obsrValue;
//    }
//    @Data
//    private static class Items{
//        public ArrayList<Item> item;
//    }
//    @Data
//    private static class Response{
//        public Header header;
//        public Body body;
//    }
//    @Data
//    private static class Root{
//        public Response response;
//    }
//
//
//
//
//
//}