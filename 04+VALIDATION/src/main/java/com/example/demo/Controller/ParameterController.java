package com.example.demo.Controller;


import com.example.demo.Dto.PersonDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/param")

public class ParameterController {

    @RequestMapping(value="/p01",method= RequestMethod.GET)
    public void paramHandler_1(@RequestParam(name="username",required = false) String name){
        log.info("GET /param/p01...."+name);
    }

    @RequestMapping(value="/p02",method= RequestMethod.GET)
    public void paramHandler_2(@RequestParam(name="username",required = true) String name){
        log.info("GET /param/p02...."+name);
    }

    @RequestMapping(value="/p03",method= RequestMethod.GET)
    public void paramHandler_3(@RequestParam(name="username",required = false) String name){
        log.info("GET /param/p03...."+name);
    }
//    @RequestParam : 동기요청 파라미터 처리 / html form 기반 전달되는 파라미터 처리(JS의 form-data도 받기가능, JSON Type 받기불가)
//    @RequestBody :  비동기요청 파라미터 처리 / json,filedata등 전달되는 파라미터 처리(html form 처리가능) 파일,Jaon만 쓰고 나머지는 Param

    @RequestMapping(value="/p04",method= RequestMethod.GET)
    public void paramHandler_4(@RequestBody String name){
        log.info("GET /param/p04...."+name);
    }

    @RequestMapping(value="/p05",method= RequestMethod.GET)
    public void paramHandler_5(@RequestParam(name="username",required = false,defaultValue = "홍길동") String name){
        log.info("POST /param/p05...."+name);
    }

    @RequestMapping(value="/p06",method= RequestMethod.GET)
    public void paramHandler_6(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam String addr
    ){
        log.info("GET /param/p06...."+name+ " " + age + " " + addr + " ");
    }

    @RequestMapping("/p07")
    public void paramHandler_7(PersonDto dto){ //@ModelAttribute 생략가능
        log.info("GET /param/p07...."+dto);
    }

    @GetMapping("/p08/{name}/{age}/{addr}")
    public void paramHandler_8(
            @PathVariable (value="name")String username, // username으로 하면 매핑이 안되기때문에 value="name"을 넣어줘야 찾을 수 있음.
            @PathVariable int age,
            @PathVariable String addr
    ){
        log.info("GET /param/p08...."+username+ " " + age + " " + addr + " ");
    }

    @GetMapping("/p09/{name}/{age}/{addr}")
    public void paramHandler_9(PersonDto dto){
        log.info("GET /param/p09...."+dto);
    }

    @GetMapping("/page1")
    public void page1(PersonDto dto, Model model){
        log.info("GET /param/page1..." + dto);
        //01 파라미터 받기 // 바로받아짐
        //02 유효성 검사
        //03 서비스 실행

        model.addAttribute("dto",dto);
        model.addAttribute("isLogin",true);
        //04 뷰로 이동(자동)
    }

    @GetMapping("/page2/{name}/{age}/{addr}")
    public ModelAndView page2_handler(PersonDto dto){
        log.info("GET /param/page2..." +dto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto",dto); //여러개는 AllObject, 개개는 addObject
        modelAndView.setViewName("param/page2");
        return modelAndView;
    }

    @GetMapping("/page3")
    public void page3_handler(HttpServletRequest request, HttpServletResponse response){

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age") );
        String addr = request.getParameter("addr");
        log.info("GET /param/page3..."+name+" "+age+" "+addr);
        PersonDto dto = new PersonDto(name,age,addr);
        request.setAttribute("dto", dto); //request는 요청할때 유지해서 페이지로이동 포워딩방식

        Cookie cookie = new Cookie("c1","v1"); //쿠키생성
        response.addCookie(cookie); //쿠키전송
//        HttpSession session = request.getSession();
//        session.setAttribute("dto", dto);
    }
    @GetMapping("/page4")
    public void page4_handler(@RequestParam Map<String,Object> params){
        log.info("GET /param/page4..." + params);
    }


    // =========================================
    // FORWARD (사용법 = forward:이동할페이지)
    // =========================================
    @GetMapping("/forward/init")
    public String forward_init_nadler(Model model){
        log.info("GET /param/forawrd/init...");
        model.addAttribute("init","init_value");
        return "forward:/param/forward/step1"; //return 이동할페이지 포워드처리 = forward:
    }
    @GetMapping("/forward/step1")
    public String forward_step1_nadler(Model model){
        model.addAttribute("step1","step1_value");
        log.info("GET /param/forawrd/step1...");
        return "forward:/param/forward/step2";
    }
    @GetMapping("/forward/step2")
    public void forward_step2_nadler(){
        log.info("GET /param/forward/step2...");
    }

    //==========================
    //REDIRECT
    //==========================
    @GetMapping("/redirect/init")
    public String redirect_init_nadler(Model model, RedirectAttributes redirectAttributes){
        log.info("GET /param/redirect/init...");
        model.addAttribute("init","init_value");
        redirectAttributes.addAttribute("r_init","r_init_value"); //쿼리스트링 파라미터형태로 전달
        redirectAttributes.addFlashAttribute("r_init2","r_init2_value"); //session 속성추가
        return "redirect:/param/redirect/step1";
    }
    @GetMapping("/redirect/step1")
    public void redirect_step1_nadler(Model model,@RequestParam String r_init){
        log.info("GET /param/redirect/step1..."+r_init);
        model.addAttribute("step1","step1_value");
    }
    @GetMapping("/redirect/step2")
    public void redirect_step2_nadler(){
        log.info("GET /param/redirect/step2...");
    }






//    @RequestMapping("")
//    public void paramHandler_1(){
//        log.info("");
//    }

}
