package com.pyo.pyostagram.web.api;


import com.pyo.pyostagram.config.auth.PrincipalDetails;
import com.pyo.pyostagram.domain.comment.Comment;
import com.pyo.pyostagram.service.CommentService;
import com.pyo.pyostagram.web.dto.CMRespDto;
import com.pyo.pyostagram.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CommentApiController {



    private final CommentService commentService;


    @PostMapping("/api/comment")
    public ResponseEntity<?> commentSave(@RequestBody CommentDto commentDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        System.out.println(commentDto);
     Comment comment =   commentService.댓글쓰기(commentDto.getContent(),commentDto.getImageId() , principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기성공",comment), HttpStatus.OK);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> commentDelete(@PathVariable int id){

        return null;
    }


}
