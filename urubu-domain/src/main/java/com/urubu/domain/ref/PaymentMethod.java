package com.urubu.domain.ref;


import com.urubu.core.domain.base.Selectable;

import java.util.Arrays;

public enum PaymentMethod implements Selectable {

    BANK_SLIP(0, "Bank Slip"),
    PIX(1, "Pix"),
    CREDIT_CARD(2, "Credit Card");

    private final Integer ordinal;
    private final String value;

    PaymentMethod(Integer ordinal, String value) {
        this.ordinal = ordinal;
        this.value = value;
    }

	public static PaymentMethod fromStr(String value) {
		return Arrays.stream(PaymentMethod.values())
				.filter(e -> e.value.equalsIgnoreCase(value) || e.name().equalsIgnoreCase(value))
                .findAny()
				.orElseThrow(() -> new RuntimeException("INVALID PAYMENT METHOD: " + value));
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
