package com.pyo.pyostagram.handler;

import com.pyo.pyostagram.handler.ex.CustomValidationApiException;
import com.pyo.pyostagram.handler.ex.CustomValidationException;
import com.pyo.pyostagram.util.Script;
import com.pyo.pyostagram.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        //CMRespDto, Script 비교
        // 1. 클라이언트한테 대답하면 Script가 좋음
        // 2. Ajax통신 - CMRespDto가 좋음
        // 3. android 통신 - CMRRespDto
        return Script.back(e.getErrorMap().toString());

    }

    @ExceptionHandler(CustomValidationApiException.class)
    public CMRespDto<?> validationApiException(CustomValidationApiException e){
        //CMRespDto, Script 비교
        // 1. 클라이언트한테 대답하면 Script가 좋음
        // 2. Ajax통신 - CMRespDto가 좋음
        // 3. android 통신 - CMRRespDto
        return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
 //여기부터해야함
    }

}
