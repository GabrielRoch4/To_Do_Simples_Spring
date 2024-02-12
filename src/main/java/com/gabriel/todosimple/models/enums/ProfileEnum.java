package com.gabriel.todosimple.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ProfileEnum {

    AMDIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer code;
    private String description;

    public static ProfileEnum toEnum(Integer code) {
        // Prof code nulo
        if (Objects.isNull(code)) {
            return null;
        }

        // Retorna o prof
        for (ProfileEnum x : ProfileEnum.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        // Prof code não existente
        throw new IllegalArgumentException("Invalid code: " + code);
    }

}
