package com.pyo.pyostagram.web.dto.comment;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    //NotNull = null값 체크
    //@NotEmpty   빈값이거나 null을 체크
    //NotBlank = 빈값이거나 null체크 그리고 빈 공백(스페이스) 까지
    @NotBlank
    private String content;
    @NotNull
    private Integer imageId;

    // toEntity가 필요없다
}
