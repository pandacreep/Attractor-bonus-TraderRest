package com.trader.domain.enam;

public enum GoodType {
    MEAT("Мясо"),
    DRIED_FRUITS("Сухофрукты"),
    GRAIN("Зерно"),
    FLOUR("Мука"),
    FABRICS("Ткани"),
    PAINT("Краска");

    private final String value;

    GoodType(String name) {
        value = name;
    }

    public String getValue() {
        return value;
    }
}
