package com.pyo.pyostagram.web;


import com.pyo.pyostagram.config.auth.PrincipalDetails;
import com.pyo.pyostagram.domain.image.Image;
import com.pyo.pyostagram.handler.ex.CustomValidationException;
import com.pyo.pyostagram.service.ImageService;
import com.pyo.pyostagram.web.dto.image.ImageUploadDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;


    @GetMapping({"/", "image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular(Model model) {
        //api는 데이터를 리턴하는 서버 여서 이건 그냥 ajax없이 들고가기만 해서
        //컨트롤러에 구현
        List<Image> images = imageService.인기사진();

        model.addAttribute("images", images);

        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";

    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        //서비스 호출
        if (imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);

        }

        imageService.사진업로드(imageUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }
}


