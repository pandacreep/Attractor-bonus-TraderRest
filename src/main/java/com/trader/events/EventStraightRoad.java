package com.trader.events;

import com.trader.domain.Trader;

public class EventStraightRoad implements Event {
    @Override
    public int action(Trader trader) {
        int distance = trader.getDistance();
        int speed = trader.getSpeed() + 2;
        if (speed > trader.getSpeedMax()) speed = trader.getSpeedMax();
        distance -= speed;
        if (distance < 0) distance = 0;
        trader.setDistance(distance);
        String eventMessage = String.format("Едем по ровной дороге. Скорость повышена до %s", speed);
        trader.setAction(eventMessage);
        return 0;
    }
}
