package com.miree.xyab.domain;

import com.miree.xyab.domain.enums.UserType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true, of = {"email"})
@Getter
@NoArgsConstructor
@Entity
@Table
public class User extends BaseEntity implements Serializable {

    @Column
    private String name;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String email;

    @Column
    private String principal;

    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    public User(String name, String password, String email, String principal, UserType userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.principal = principal;
        this.userType = userType;
        this.setCreatedDate();
        this.setUpdatedDate();
    }
}
