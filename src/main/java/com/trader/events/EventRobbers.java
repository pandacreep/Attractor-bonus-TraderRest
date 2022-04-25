package com.trader.events;

import com.trader.domain.Good;
import com.trader.domain.Trader;

public class EventRobbers implements Event{
    @Override
    public int action(Trader trader) {
        String eventMessage = "Напали разбойники. ";
        if (Math.random() < 0.2 && trader.getGoods().size() > 0) {
            Good goodMaxPrice = trader.getGoods().get(0);
            int goodMaxPriceIndex = 0;
            for (int i = 0; i < trader.getGoods().size(); i++) {
                if (trader.getGoods().get(i).getPrice() > goodMaxPrice.getPrice()) {
                    goodMaxPrice = trader.getGoods().get(i);
                    goodMaxPriceIndex = i;
                }
            }
            eventMessage = eventMessage
                    + " " + String.format("Откупаемся товаром. Отдали '%s'", trader.getGoods().get(goodMaxPriceIndex).getType());

            trader.getGoods().remove(goodMaxPriceIndex);
        } else {
            double tribute = Math.floor(trader.getMoney() * 0.04);
            trader.setMoney(trader.getMoney() - tribute);
            eventMessage = eventMessage
                    + " " + String.format("Откупаемся деньгами. Отдали %4.1f монет.", tribute);

        }
        int distance = trader.getDistance();
        distance -= trader.getSpeed();
        if (distance < 0) distance = 0;
        trader.setDistance(distance);
        trader.setAction(eventMessage);
        return 0;
    }
}
