package com.pyo.pyostagram.domain.likes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pyo.pyostagram.domain.image.Image;
import com.pyo.pyostagram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
     uniqueConstraints ={
             @UniqueConstraint(name="likes_uk",
             columnNames = {"imageId","userId"}
             )
     }
    //좋아요는 중복될수 없으므로 유니크키로 묶은것
)
public class Likes { // N

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @JoinColumn(name="imageId")
    @ManyToOne
    private Image image; //1

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name="userId")
    @ManyToOne
    private User user;

   private LocalDateTime createDate;

    @PrePersist  // DB에 INSERT 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
