package com.pyo.pyostagram.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pyo.pyostagram.domain.image.Image;
import com.pyo.pyostagram.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100 , nullable = false) //댓글은 항상 제한 걸어두자
    private String content;

    @JsonIgnoreProperties({"images"})
    @ManyToOne(fetch = FetchType.EAGER)  //얘는 왜 eager??
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="imageId")
    private Image image;


    private LocalDateTime createDate;


    @PrePersist
    public void createDate(){
        this.createDate =  LocalDateTime.now();
    }
}
