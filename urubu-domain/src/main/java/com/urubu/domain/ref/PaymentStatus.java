package com.urubu.domain.ref;


public enum PaymentStatus {

    PENDING(0, "Payment Pending"),
    RECEIVED(1, "Payment Received"),
    OVERDUE(2, "Payment Overdue"),
    REFUNDED(3, "Payment Refunded"),
    CONFIRMED(4, "Payment Confirmed");

    private final Integer ordinal;
    private final String value;

    PaymentStatus(Integer ordinal, String value) {
        this.ordinal = ordinal;
        this.value = value;
    }
}
