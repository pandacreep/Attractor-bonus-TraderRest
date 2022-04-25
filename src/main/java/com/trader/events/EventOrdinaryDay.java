package com.trader.events;

import com.trader.domain.Trader;

public class EventOrdinaryDay implements Event{
    @Override
    public int action(Trader trader) {
        int distance = trader.getDistance();
        distance -= trader.getSpeed();
        if (distance < 0) distance = 0;
        trader.setDistance(distance);
        trader.setAction("Обычный день. Ничего не произошло.");
        return 0;
    }
}
