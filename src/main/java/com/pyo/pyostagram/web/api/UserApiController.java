package com.pyo.pyostagram.web.api;


import com.pyo.pyostagram.config.auth.PrincipalDetails;
import com.pyo.pyostagram.domain.user.User;
import com.pyo.pyostagram.handler.ex.CustomValidationApiException;
import com.pyo.pyostagram.handler.ex.CustomValidationException;
import com.pyo.pyostagram.service.UserService;
import com.pyo.pyostagram.web.dto.CMRespDto;
import com.pyo.pyostagram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {


    private final UserService userService;
    //DATA를 응답하는 애들은 api

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult, // 꼭@Valid가 적힌 다음파라메터에 적어야함
            @AuthenticationPrincipal PrincipalDetails principalDetails) {


        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성검사 실패", errorMap);
        } else {

            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1, "회원수정완료", userEntity);
            //응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
            //이것 역시 또 List<Image>의 무한반복 문제.


        }

    }
}
