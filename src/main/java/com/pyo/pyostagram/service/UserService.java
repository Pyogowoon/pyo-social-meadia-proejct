package com.pyo.pyostagram.service;


import com.pyo.pyostagram.domain.user.User;
import com.pyo.pyostagram.domain.user.UserRepository;
import com.pyo.pyostagram.handler.ex.CustomException;
import com.pyo.pyostagram.handler.ex.CustomValidationApiException;
import com.pyo.pyostagram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User 회원수정(int id, User user) {
        //1. 영속화
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> {
                    return new CustomValidationApiException("찾을 수 없는 id입니다.");
                });



        //2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setWebsite(user.getWebsite());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setBio(user.getBio());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        return userEntity;
    } // 더티체킹이 되서 업데이트가 완료됨


    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(int pageUserId, int principalId){
        UserProfileDto dto = new UserProfileDto();


        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
          throw new CustomException("해당 프로필 페이지는 없는 페이지입니다");
        });

        dto.setUser(userEntity);
        dto.setImageCount(userEntity.getImages().size());
        dto.setPageOwnerState(pageUserId == principalId); // true면 주인

        return dto;
    }
}
