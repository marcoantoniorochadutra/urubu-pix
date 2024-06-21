package com.urubu.domain.ref;

public enum TransactionType {

    WITHDRAW(0, "Withdraw"),
    PROFIT(1, "Profit"),
    DEPOSIT(2, "Deposit");

    private final Integer ordinal;
    private final String value;

    TransactionType(Integer ordinal, String value) {
        this.ordinal = ordinal;
        this.value = value;
    }
}
