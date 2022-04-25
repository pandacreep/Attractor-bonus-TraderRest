package com.trader.events;

import com.trader.domain.Trader;

import java.util.Random;

public class EventMeetLocal implements Event{
    @Override
    public int action(Trader trader) {
        Random random = new Random();
        int extraSpeed = random.nextInt(4) + 3;
        int distance = trader.getDistance();
        distance -= trader.getSpeed();
        distance -= extraSpeed;
        if (distance < 0) distance = 0;
        trader.setDistance(distance);
        String eventMessage = String.format("Встретили местного. Удалось проехать на %s лиг больше", extraSpeed);
        trader.setAction(eventMessage);
        return 0;
    }
}
