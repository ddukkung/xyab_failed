package com.miree.xyab.dto;

import com.miree.xyab.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@EqualsAndHashCode(of = "idx")
@NoArgsConstructor
public class UserDto {

    private Long idx;
    private String name;
    private String email;
    private String userType;

    public UserDto(User user) {
        this.idx = user.getIdx();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userType = user.getUserType().getValue();
    }

    public UserDto(Long idx, String name, String email, String userType) {
        this.idx = idx;
        this.name = name;
        this.email = email;
        this.userType = userType;
    }
}
