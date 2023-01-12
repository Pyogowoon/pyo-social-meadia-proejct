package com.pyo.pyostagram.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value="SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId) ORDER BY id desc", nativeQuery = true)
    Page<Image> mStroy(@Param("principalId") int principalId, @Param("pageable") Pageable pageable) ;

}
