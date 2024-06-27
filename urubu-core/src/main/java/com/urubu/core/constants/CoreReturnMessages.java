package com.urubu.core.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CoreReturnMessages {

    WEAK_PASSWORD(0),
    NOT_NULL_MESSAGE(1),
    INVALID_ARGUMENT(2),
    INVALID_FIELD(3),
    INVALID_SIZE(4),
    INTERNAL_ERROR(5),
    TOKEN_ERROR(6),
    EXISTING_ACCOUNT(7),
    LOGIN_UNAUTHORIZED_ERROR(8),
    LOGIN_CREDENTIALS_ERROR(9),
    LOGIN_BLOCKED_ACCOUNT(10),
    LOGIN_EXISTING_EMAIL(11);

    private final Integer code;
}
