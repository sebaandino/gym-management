package com.gym.demo.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleEnum {
    ADMIN,
    TRAINER,
    USER;

    @JsonCreator
    public static RoleEnum fromString(String key) {
        return key == null ? null : RoleEnum.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase();
    }

}
