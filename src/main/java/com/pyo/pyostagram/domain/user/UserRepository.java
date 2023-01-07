package com.pyo.pyostagram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

                                                 //오브젝트,프라이머리키 타입
    //JPA query method 사용할것

    User findByUsername(String username);
}
