package com.miree.xyab.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = {"idx"})
@MappedSuperclass
// Entity 에서 공통적으로 사용될 날짜 필드를 관리할 클래스
public class BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Column(updatable = false)
    private LocalDateTime updatedDate;

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public void setUpdatedDate() {
        this.updatedDate = LocalDateTime.now();
    }

}
