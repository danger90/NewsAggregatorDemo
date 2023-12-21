package com.demo.news.agregator.demo.model.source;

import lombok.Getter;

@Getter
public enum RolesEnum {
    ARMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private final String role;

    RolesEnum(String role) {
        this.role = role;
    }
}