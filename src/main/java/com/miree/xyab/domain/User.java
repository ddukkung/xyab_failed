package com.miree.xyab.domain;

import com.miree.xyab.domain.enums.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table
public class User extends BaseEntity implements Serializable {

    @Column
    private String userId;

    @Column
    private String nickname;

    @Column
    @JsonIgnore
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    public User(String userId, String password, String nickname, UserType userType) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.userType = userType;
        this.setCreatedDate();
        this.setUpdatedDate();
    }
}
