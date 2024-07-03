package com.urubu.core.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionException extends RuntimeException {

    public TransactionException(String message) {
        super(message);
    }
}
