//package com.example.demo.Restful.Opendata;
//
//
//import jakarta.xml.bind.annotation.XmlAccessType;
//import jakarta.xml.bind.annotation.XmlAccessorType;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
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
////대구광역시_돌발 교통정보 조회 서비스(신)
//@RestController
//@Slf4j
//@RequestMapping("/APT")
//public class OpenData04Controller {
//    private String server = "https://apis.data.go.kr/1613000/RTMSDataSvcAptTrade/getRTMSDataSvcAptTrade";
//    private String serviceKey = "aee66adefc0e767d8331293756ce173f53307650ec5f8fc2e71b655710a6d0ee"; //인증키
//    private String LAWD_CD;
//    private String DEAL_YMD;
//
//    ///  LAWD_CD	11110
//    /// DEAL_YMD    202509
//
//
//    @GetMapping(value = "/{LAWD_CD}/{DEAL_YMD}")
//    public void get(
//            // 필수조건 무조건 값을 넣어야한다
//            @PathVariable(value = "LAWD_CD", required = true) String LAWD_CD,
//            @PathVariable(value = "DEAL_YMD", required = true) String DEAL_YMD,
//            Model model
//    ) {
//        log.info("GET /BUS....LAWD_CD : " + LAWD_CD + "DEAL_YMD : " + DEAL_YMD);
//        this.LAWD_CD = LAWD_CD;
//        this.DEAL_YMD = DEAL_YMD;
//
//        // 파라미터 설정( service Key 포함)
//        String url = UriComponentsBuilder.fromHttpUrl(server)
//                .queryParam("serviceKey",serviceKey)
//                .queryParam("LAWD_CD", LAWD_CD)
//                .queryParam("DEAL_YMD", DEAL_YMD)
//                .toUriString();
//
//        RestTemplate restTemplate = new RestTemplate(); // restTemplate로 요청함
//        //Post방식 사용하면 밑에꺼 다 사용함
//        // 요청 헤더 설정
////        HttpHeaders header = new HttpHeaders();
////        header.add("key", "value");
////        // 요청 바디 설정(X)
////        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
////        params.add("","");
////        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,header); // 엔티티의 첫번째는 파람을 넣음. //헤더와 바디 합치는?
//        // 요청후 응답 확인
////        restTemplate.exchange("URL", "요청METHOD","entity","반환값 받아낼 자료형");
//
//        ResponseEntity<Root> response =
//        restTemplate.exchange(url, HttpMethod.GET,null,Root.class);
//
//
//        //REST TYPE -> Class Type 변환
//        System.out.println(response.getBody());
//
//        // 확인
//
//        Root root = response.getBody();
//        Body body = root.getBody();
//        ArrayList<Item> items = body.getItems();
//        items.forEach((item)->{System.out.println(item);
//        });
//
//
//
//
//    }
//    @Data
//    @XmlAccessorType(XmlAccessType.FIELD)
//    private static class header {
//        public int resultCode;
//        public String resultMsg;
//    }
//    @Data
//    @XmlAccessorType(XmlAccessType.FIELD)
//    private static class item {
//        public Object aptDong;
//        public String aptNm;
//        public int buildYear;
//        public Object buyerGbn;
//        public Object cdealDay;
//        public Object cdealType;
//        public double dealAmount;
//        public int dealDay;
//        public int dealMonth;
//        public int dealYear;
//        public Object dealingGbn;
//        public Object estateAgentSggNm;
//        public double excluUseAr;
//        public int floor;
//        public int jibun;
//        public String landLeaseholdGbn;
//        public Object rgstDate;
//        public int sggCd;
//        public Object slerGbn;
//        public String umdNm;
//    }
//    @Data
//    @XmlAccessorType(XmlAccessType.FIELD)
//    private static class items {
//        public List<item> item;
//    }
//    @Data
//    @XmlAccessorType(XmlAccessType.FIELD)
//    private static class body {
//        public items items;
//        public int numOfRows;
//        public int pageNo;
//        public int totalCount;
//    }
//    @Data
//    @XmlAccessorType(XmlAccessType.FIELD)
//    private static class Root {
//        public header header;
//        public body body;
//    }
//
//
//
//
//}