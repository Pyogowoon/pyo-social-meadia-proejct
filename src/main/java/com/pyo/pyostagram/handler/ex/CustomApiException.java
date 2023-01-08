package com.pyo.pyostagram.handler.ex;

import java.util.Map;


//얘는 validation과 상관없음
public class CustomApiException extends RuntimeException {


    //객체를 구분할때
    private static final long serialVersionUID = 1L;


    public CustomApiException(String message) {
        super(message);
    }


}
