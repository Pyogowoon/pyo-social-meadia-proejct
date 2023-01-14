package com.pyo.pyostagram.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment , Integer> {

    @Modifying
    @Query(value= "INSERT INTO comment(content , userId, imageId, createDate) VALUES( :content, :userId, :imageId, now())", nativeQuery= true)
    int mSave(String content, int imageId, int userId );

}
