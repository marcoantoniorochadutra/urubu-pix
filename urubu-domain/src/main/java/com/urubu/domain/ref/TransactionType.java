package com.urubu.domain.ref;

import com.urubu.core.domain.base.Selectable;

public enum TransactionType implements Selectable {

    WITHDRAW(0, "Withdraw"),
    TRANSFER(1, "Transfer"),
    DEPOSIT(2, "Deposit");

    private final Integer ordinal;
    private final String value;

    TransactionType(Integer ordinal, String value) {
        this.ordinal = ordinal;
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
