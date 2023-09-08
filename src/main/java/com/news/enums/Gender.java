package com.news.enums;

public enum Gender {
    MALE(1),
    FEMALE(2);

    private final int value;

    Gender(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
