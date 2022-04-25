package com.trader.service;

import com.trader.domain.Good;
import com.trader.domain.Info;
import com.trader.domain.Trader;
import com.trader.events.EventsCollection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JourneyService {
    private final TraderService traderService;

    public Trader passOneDay() {
        Trader trader = traderService.getTrader();
        trader.setDay(trader.getDay() + 1);
        action(trader);
        traderService.save(trader);
        return trader;
    }

    public void action(Trader trader) {
        EventsCollection events = new EventsCollection();
        events.getEvent().action(trader);
    }

    public Info sellGoods() {
        Trader trader = traderService.getTrader();
        double income = 0;
        for (Good good : trader.getGoods()) {
            income += good.getPrice() * good.getQuality().getFactor();
        }
        String finalMessage = String.format("Товар продан за %4.2f монет", income);
        double currentMoney = income + trader.getMoney();
        String result;
        if (trader.getInitialMoney() > currentMoney) result = "Потерял";
        else result = "Заработал";
        finalMessage = finalMessage
                + " "
                + String.format("Начинал с %4.1f, сейчас у меня %4.1f. %s %4.1f на продаже товара.",
                    trader.getInitialMoney(),
                    currentMoney,
                    result,
                    Math.abs(trader.getInitialMoney() - currentMoney));
        return Info.message(finalMessage);
    }
}
