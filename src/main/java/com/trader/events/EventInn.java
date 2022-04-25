package com.trader.events;

import com.trader.domain.Good;
import com.trader.domain.Trader;

import java.util.Random;

public class EventInn implements Event{
    @Override
    public int action(Trader trader) {
        Random random = new Random();
        String eventMessage = "Встретился придорожный трактир. ";
        int decisionStayInInn = random.nextInt(4);
        int spending = random.nextInt(1) + 5;
        if (decisionStayInInn == 0 || spending > trader.getMoney()) {
            eventMessage = eventMessage
                    + " " + "Решили проехать мимо.";

            int distance = trader.getDistance();
            distance -= trader.getSpeed();
            if (distance < 0) distance = 0;
            trader.setDistance(distance);
            trader.setAction(eventMessage);
            return 0;
        }
        eventMessage = eventMessage
                + " " + String.format("Решили остановиться. Потратили %d монет на еду и ночлег.", spending);
        trader.setMoney(trader.getMoney() - spending);
        if (random.nextInt(2) == 0) {
            eventMessage = eventMessage
                    + " " + trade(trader, random);
        }
        trader.setAction(eventMessage);
        return 1;
    }

    private String trade(Trader trader, Random random) {
        if (random.nextInt(2) == 0) return "";
        if (trader.getGoods().size() == 0) return "";
        String eventMessage = "Решили продать товар. ";
        int index = random.nextInt(trader.getGoods().size());
        Good good = trader.getGoods().get(index);
        double sellingPrice = good.getPrice() * good.getQuality().getFactor();
        trader.setMoney(trader.getMoney() + sellingPrice);
        eventMessage = eventMessage
                + " " + String.format("Товар %s был продан за %4.1f$", good.getType(), sellingPrice)
                + " " + String.format("У нас стало %4.1f монет", trader.getMoney());
        trader.getGoods().remove(index);
        return eventMessage;
    }
}
