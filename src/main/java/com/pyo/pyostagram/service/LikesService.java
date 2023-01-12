package com.pyo.pyostagram.service;


import com.pyo.pyostagram.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void 좋아요(int imageId , int principalId ){
        likesRepository.mLikes(imageId,principalId);

    }

    @Transactional
    public void 좋아요취소(int imageId , int principalId){
        likesRepository.mUnLikes(imageId, principalId);
    }

}
