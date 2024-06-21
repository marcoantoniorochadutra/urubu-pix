package com.urubu.core.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountException extends RuntimeException {

    public AccountException(String message) {
        super(message);
    }
}
