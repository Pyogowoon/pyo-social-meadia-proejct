package com.pyo.pyostagram.handler;

import com.pyo.pyostagram.handler.ex.CustomApiException;
import com.pyo.pyostagram.handler.ex.CustomException;
import com.pyo.pyostagram.handler.ex.CustomValidationApiException;
import com.pyo.pyostagram.handler.ex.CustomValidationException;
import com.pyo.pyostagram.util.Script;
import com.pyo.pyostagram.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
        //CMRespDto, Script 비교
        // 1. 클라이언트한테 대답하면 Script가 좋음
        // 2. Ajax통신 - CMRespDto가 좋음
        // 3. android 통신 - CMRRespDto

        if (e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        } else {
            return Script.back(e.getErrorMap().toString());
        }

    }
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e){

        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(), e.getErrorMap()),HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e){

        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CustomException.class)
    public String Exception(CustomException e) {

        return Script.back(e.getMessage());
    }
}
