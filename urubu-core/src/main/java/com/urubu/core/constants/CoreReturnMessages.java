package com.urubu.core.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoreReturnMessages {

    WEAK_PASSWORD(0),
    NOT_NULL_MESSAGE(1),
    INVALID_ARGUMENTO(2),
    INVALID_SIZE(3),
    INTERNAL_ERROR(4),
    TOKEN_ERROR(5),
    LOGIN_UNAUTHORIZED_ERROR(6),
    EXISTING_ACCOUNT(7);

    private final Integer code;
}
