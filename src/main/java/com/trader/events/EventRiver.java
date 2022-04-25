package com.trader.events;

import com.trader.domain.Trader;

import java.util.Random;

public class EventRiver implements Event{
    @Override
    public int action(Trader trader) {
        Random random = new Random();
        int speed = random.nextInt(2) + 1;
        int distance = trader.getDistance();
        distance -= speed;
        if (distance < 0) distance = 0;
        trader.setDistance(distance);
        String eventMessage = String.format("Встретилась река. Потратили целый день, пока искали брод. Проехали %s лиг", speed);
        trader.setAction(eventMessage);
        return 0;
    }
}
