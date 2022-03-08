package com.miree.xyab.domain.enums;

public enum UserType {
    ADMIN("관리자"),
    COMMON_USER("일반 유저");

    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
