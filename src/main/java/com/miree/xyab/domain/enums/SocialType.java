package com.miree.xyab.domain.enums;

public enum SocialType {
    FACEBOOK("facebook"),
    GOOGLE("google");

    private final String ROLE_PREFIX = "ROLE_";
    private String name;

    SocialType(String name) {
        this.name = name;
    }

    // 'ROLE_*' 형식으로 소셜 미디어 권한명 생성
    public String getRoleType() {
        return ROLE_PREFIX + name.toUpperCase();
    }

    public String getValue() {
        return name;
    }

    public boolean isEquals(String authority) {
        return this.getRoleType().equals(authority);
    }
}
