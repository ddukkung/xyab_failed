package com.miree.xyab.dto;

import com.miree.xyab.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@EqualsAndHashCode(of = "idx")
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long idx;

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;
    
    private String userType;

    public UserDto(User user) {
        this.idx = user.getIdx();
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.userType = user.getUserType().getValue();
    }

    public UserDto(Long idx, String userId, String password, String nickname, String userType) {
        this.idx = idx;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.userType = userType;
    }
}
