package com.pyo.pyostagram.web;


import com.pyo.pyostagram.config.auth.PrincipalDetails;
import com.pyo.pyostagram.domain.user.User;
import com.pyo.pyostagram.service.UserService;
import com.pyo.pyostagram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        UserProfileDto dto = userService.회원프로필(pageUserId,principalDetails.getUser().getId());
        model.addAttribute("dto",dto);
        return "user/profile";
    }


    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        System.out.println("세션 정보 : " + principalDetails.getUser());
        // 역시 같은 오류를 발생(레이지 로딩)

        return "user/update";
    }
}
