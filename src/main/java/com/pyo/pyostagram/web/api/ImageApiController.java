package com.pyo.pyostagram.web.api;

import com.pyo.pyostagram.config.auth.PrincipalDetails;
import com.pyo.pyostagram.domain.image.Image;
import com.pyo.pyostagram.service.ImageService;
import com.pyo.pyostagram.service.LikesService;
import com.pyo.pyostagram.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageApiController {


    private final ImageService imageService;

    private final LikesService likesService;


    @GetMapping("/api/image")
    public ResponseEntity<?> imageStroy(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @PageableDefault(size = 3) Pageable pageable) {
        Page<Image> images = imageService.이미지스토리(principalDetails.getUser().getId(), pageable);


        return new ResponseEntity<>(new CMRespDto<>(1, "성공", images), HttpStatus.OK);
    }

    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PathVariable int imageId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
            likesService.좋아요(imageId , principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1, "좋아요 성공", null), HttpStatus.OK);
    }
    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable int imageId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.좋아요취소(imageId , principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1, "좋아요취소 성공", null), HttpStatus.OK);
    }


}
