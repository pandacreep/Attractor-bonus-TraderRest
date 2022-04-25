package com.trader.events;

import com.trader.domain.Good;
import com.trader.domain.Trader;

import java.util.Random;

public class EventRain implements Event{
    @Override
    public int action(Trader trader) {
        int distance = trader.getDistance();
        int speed = trader.getSpeed() - 2;
        if (speed < 0) speed = 0;
        String eventMessage = String.format("Идет дождь. Скорость снизилась до %d", speed);

        distance -= speed;
        if (distance < 0) distance = 0;
        trader.setDistance(distance);

        if (Math.random() < 0.2) {
            Random random = new Random();
            Good good = trader.getGoods().get(random.nextInt(trader.getGoods().size()));
            eventMessage = eventMessage
                + " " + String.format("Качество товар '%s' понижено", good.getType());
            trader.spoilQuality(good);
        }
        trader.setAction(eventMessage);
        return 0;
    }
}
