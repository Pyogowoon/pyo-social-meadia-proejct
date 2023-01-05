package com.pyo.pyostagram.web;


import com.pyo.pyostagram.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpBodyController {



    @PostMapping("/body1")
    public String xwwwformurlencoded(String username){


        return "key=value 전송 옴" + username;
    }

    @PostMapping("/body2")
    public String plaintext(@RequestBody String data){

        return "plane/text 전송 옴" + data;
    }

    @PostMapping("/body3")
    public String apllicationjson(@RequestBody User user){
    return "json 전송 옴 " + user.getUsername();
    }
}
