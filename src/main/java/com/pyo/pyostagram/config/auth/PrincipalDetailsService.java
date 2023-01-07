package com.pyo.pyostagram.config.auth;

import com.pyo.pyostagram.domain.user.User;
import com.pyo.pyostagram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

   private final UserRepository userRepository;

   //패스워드는 알아서 체킹하니까 신경x
//리턴이 잘 되면 자동으로 세션 생성
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null){
            return null;
        }else{
            return new PrincipalDetails(userEntity);
        }

    }
}
