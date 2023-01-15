package com.pyo.pyostagram.domain.user;

//JPA - Java Persistance API(자바로 데이터를 영구적으로 저장(DB)할 수있는 api 제공

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pyo.pyostagram.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    private String name;
    private String website;  //웹사이트
    private String bio; // 자기소개

    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl;
    private String role;

    private LocalDateTime createDate;


    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy ="user", fetch= FetchType.LAZY)  //user는 Image의 변수를 넣어야함 //나는 연관관계의 주인이 아니다 를 알리는것.
    //이걸 함으로써 DB가 컬럼을 만들지않으며 select 할때 user Id로 등록된 image들을 다 가져옴
    // Lazy = User를 select할떄 해당 User id로 등록된 image들을 가져오지마 - 대신 getImages() 메소드가 호출될 때 가져와
    // Eaguer = User를 select할때 해당 user Id로 등록된 image들을 전부 join해서 가져와!
    private List<Image> images; // 양방향 매핑    /   DB는 여러개못들어감 (리스트가 뭔지모름)

    @PrePersist  // DB에 INSERT 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", role='" + role + '\'' +
                ", createDate=" + createDate +

                '}';
    }
}
