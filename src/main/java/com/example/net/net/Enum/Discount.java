package com.example.net.net.Enum;

public enum Discount {
    ZERO("0"),
    TEN("10");

    private final String value;

    Discount(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

