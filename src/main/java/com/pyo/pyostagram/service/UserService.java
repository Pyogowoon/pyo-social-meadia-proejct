package com.pyo.pyostagram.service;


import com.pyo.pyostagram.domain.subscribe.SubscribeRepository;
import com.pyo.pyostagram.domain.user.User;
import com.pyo.pyostagram.domain.user.UserRepository;
import com.pyo.pyostagram.handler.ex.CustomApiException;
import com.pyo.pyostagram.handler.ex.CustomException;
import com.pyo.pyostagram.handler.ex.CustomValidationApiException;
import com.pyo.pyostagram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SubscribeRepository subscribeRepository;




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
    public UserProfileDto 회원프로필(int pageUserId, int principalId) {
        UserProfileDto dto = new UserProfileDto();


        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다");
        });

        dto.setUser(userEntity);
        dto.setImageCount(userEntity.getImages().size());
        dto.setPageOwnerState(pageUserId == principalId); // true면 주인


        int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeCount(subscribeCount);
        dto.setSubscribeState(subscribeState == 1);


        userEntity.getImages().forEach((image) -> {
            image.setLikeCount(image.getLikes().size());

        });

        return dto;
    }

    @Value("${file.path}") // yml에 적힌값 가져오는거
    private String uploadFolder;


    @Transactional
    public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile){
        // 프로필 업로드랑 로직 동일함
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid+"_"+ profileImageFile.getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try{
            Files.write(imageFilePath, profileImageFile.getBytes());
        }catch(Exception e){
            e.printStackTrace();

        }

            User userEntity = userRepository.findById(principalId).orElseThrow(()->{
                return new CustomApiException("유저를 찾을 수 없습니다.");
            });

        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;
        //더티체킹으로 업데이트 됨.

    }
}
