package com.pyo.pyostagram.web.dto.subscribe;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
    //구독정보 클릭시 나오는 모달에 넣을 정보를 만드는 DTO
    private int id;
    private String username;
    private String profileImageUrl;
    private Integer subscribeState;
    private Integer equalUserState;


    public SubscribeDto(Object[] object) {
        this.id = (int) object[0];
        this.username = (String) object[1];
        this. profileImageUrl = (String)object[2];
        this.subscribeState =Integer.parseInt(String.valueOf(object[3]));
        this.equalUserState = Integer.parseInt(String.valueOf(object[4]));

    }
}
