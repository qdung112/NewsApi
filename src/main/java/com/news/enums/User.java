package com.news.enums;

public enum User {
    ACTIVE_STATUS(1),
    INACTIVE_STATUS(0);

    private final int value;

    User(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
