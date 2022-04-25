package com.trader.events;

import com.trader.domain.Good;
import com.trader.domain.Trader;

import java.util.Random;

public class EventGoodSpoiled implements Event{
    @Override
    public int action(Trader trader) {
        Random random = new Random();
        String eventMessage;
        if (random.nextInt(3) == 0) {
            Good good = trader.getGoods().get(random.nextInt(trader.getGoods().size()));
            eventMessage = String.format("Качество товара '%s' понижено", good.getType());
            trader.spoilQuality(good);
        } else {
            eventMessage = "Обычный день. Ничего не произошло.";
        }
        int distance = trader.getDistance();
        distance -= trader.getSpeed();
        if (distance < 0) distance = 0;
        trader.setDistance(distance);
        trader.setAction(eventMessage);
        return 0;
    }
}
