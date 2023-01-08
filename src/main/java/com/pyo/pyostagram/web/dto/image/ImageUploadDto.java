package com.pyo.pyostagram.web.dto.image;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private MultipartFile file;
    private String caption;
}
