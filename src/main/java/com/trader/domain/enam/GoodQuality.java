package com.trader.domain.enam;

public enum GoodQuality {
    NORMAL("нормальное", 1.2),
    SLIGHTLY_SPOILED("слегка испорчен", 0.95),
    HALF_SPOILED("половина испортилась", 0.55),
    ALMOST_SPOILED("почти весь пропал", 0.25),
    TRASH("испорчен в хлам", 0.1);

    private final String name;
    private final double factor;

    GoodQuality(String name, double factor) {
        this.name = name;
        this.factor = factor;
    }

    public String getName() {
        return name;
    }

    public double getFactor() {
        return factor;
    }
}
