package com.example.demo.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/rest2")
public class RestTest2Controller {

    @GetMapping("/test1")
//    이렇게 중간중간 Res를 넣어서 처리해도 되지만 보통은 Rest컨트롤러를 만들어서 처리를 한다.
    @ResponseBody
    public String t1(){
        log.info("GET /rest2/test1...");
        return "HELLOWORLD";
    }
}
