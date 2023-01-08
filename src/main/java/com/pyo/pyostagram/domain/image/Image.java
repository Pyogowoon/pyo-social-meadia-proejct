package com.pyo.pyostagram.domain.image;


import com.pyo.pyostagram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption;
    private String postImageUrl;  // 사진을 전송받아서 그 사진을 서버에
    // 폴더에 저장하게될것 - DB에는 경로를 인서트

    @JoinColumn(name="userId") //foreign 키 이름 지정
    @ManyToOne // 한명이 많은 이미지를 올릴 수 있고 이미지는 하나의 이미지가 여럿일수없으니
    private User user; // db에 오브젝트 자체를 저장할 순 없고 이대로면 foreign key로 저장됨

    //이미지 좋아요

    //이미지 댓글

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
