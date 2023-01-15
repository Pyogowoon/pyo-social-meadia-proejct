package com.pyo.pyostagram.handler.aop;


import com.pyo.pyostagram.handler.ex.CustomValidationApiException;
import com.pyo.pyostagram.handler.ex.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component //RestController, Service 모든 것들이 component를 상속해서 만들어져있음
@Aspect
public class ValidationAdvice {

    @Around("execution(* com.pyo.pyostagram.web.api.*Controller.*(..))")
    public Object apiAdivice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {

                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
                }
            }
        }

        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* com.pyo.pyostagram.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {

                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {

                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성검사 실패", errorMap);
                }
            }
        }


        return proceedingJoinPoint.proceed();
    }


}
