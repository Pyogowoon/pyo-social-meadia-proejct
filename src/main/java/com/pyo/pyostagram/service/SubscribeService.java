package com.pyo.pyostagram.service;

import com.pyo.pyostagram.domain.subscribe.Subscribe;
import com.pyo.pyostagram.domain.subscribe.SubscribeRepository;
import com.pyo.pyostagram.handler.ex.CustomApiException;
import com.pyo.pyostagram.handler.ex.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(int fromUserId, int toUserId){

        try{
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        }catch(Exception e){
            throw new CustomApiException("이미 구독 되었습니다.");
        }



    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId){
     subscribeRepository.mUnSubscribe(fromUserId,toUserId);

    }
}
