package com.miree.xyab.domain;

import com.miree.xyab.domain.enums.SocialType;
import com.miree.xyab.domain.enums.UserType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true, of = {"email"})
@Getter
@NoArgsConstructor
@Entity
@Table
public class User extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

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
    private SocialType socialType;

    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    public User(String name, String password, String email, String principal, SocialType socialType, UserType userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.principal = principal;
        this.socialType = socialType;
        this.userType = userType;
        this.setCreatedDate();
        this.setUpdatedDate();
    }
}
