package com.pyo.pyostagram.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryPathController {


    @GetMapping("/chicken")
    public String chickenQuery(String type){
        return type +" 배달갑니다(쿼리스트링)";

    }


    @GetMapping("/chicken/{type}")
    public String chickenpath(@PathVariable String type){

        return type +" 배달갑니다(주소 변수 매핑)";
    }
}

