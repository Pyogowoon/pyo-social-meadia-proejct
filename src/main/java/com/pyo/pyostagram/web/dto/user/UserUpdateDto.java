package com.pyo.pyostagram.web.dto.user;


import com.pyo.pyostagram.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;
    
    
//조금 위험함 코드수정 필요 (낫널처리 널처리)
    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password) // 패스워드를 기재 안하면 DB에 공백값이 들어가게될것
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();

    }
}
