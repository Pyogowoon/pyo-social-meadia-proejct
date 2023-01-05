package com.pyo.pyostagram.domain;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpResponseJsonController {

    @GetMapping("/resp/json")
    public String respJson() {

        return "{\"username\" :\"cos\"}";

    }

    @GetMapping("/resp/json/object")
    public String respJsonObject() {
        User user = new User();
        user.setUsername("홍길동");

        String data="{\"username\" :\" "+user.getUsername()+"\"}";
        return data;

    }

    @GetMapping("/resp/json/javaobject")
    public User respJavaObject() {
        User user = new User();
        user.setUsername("홍길동");


        return user; // 1.스프링의 MessageConverter가 JSON(옛날:xml로)을 자동으로 JavaObject를 json으로 변경해서 통신을 통해 응답해
        // 2. @RestController 일때만 MessageConverter가 작동한다
    }



}
