package com.pyo.pyostagram.web.dto.image;


import com.pyo.pyostagram.domain.image.Image;
import com.pyo.pyostagram.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class ImageUploadDto {


    private MultipartFile file;  //멀티파츠는 @NotBlank 지원하지않음
    private String caption;

    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();

    }
}
