package com.miree.xyab.dto;

import com.miree.xyab.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode(of = "idx")
public class UserDto implements Serializable {

    private Long idx;
    private String name;
    private String email;
    private String userType;

    public UserDto(User user) {
        this.idx = user.getIdx();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
