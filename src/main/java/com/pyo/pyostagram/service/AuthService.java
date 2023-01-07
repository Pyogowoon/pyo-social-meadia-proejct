package com.pyo.pyostagram.service;


import com.pyo.pyostagram.domain.user.User;
import com.pyo.pyostagram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User 회원가입(User user){
        String rawPassword=user.getPassword();
        String encPassword= bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");   // 관리자는 ROLE_ADMIN

   User userEntity = userRepository.save(user);
   return userEntity;


    }
}
