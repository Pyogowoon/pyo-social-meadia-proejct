package com.pyo.pyostagram.web.dto.user;

import com.pyo.pyostagram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfileDto {


    private boolean pageOwnerState;
    private int imageCount;

    private User user;
}


