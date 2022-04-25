package com.trader.service;

import com.trader.domain.Good;
import com.trader.domain.Trader;
import com.trader.repository.TraderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TraderService {
    private final TraderRepository traderRepository;

    public Trader getTrader() {
        var trader = traderRepository.findAll().get(0);
        return trader;
    }

    public void init() {
        traderRepository.deleteAll();
        traderRepository.save(Trader.random());
    }

    public void updateDistance(int distance) {
        Trader trader = getTrader();
        trader.setDistance(distance);
        traderRepository.save(trader);
    }

    public List<Good> buy() {
        Trader trader = traderRepository.findAll().get(0);
        List<Good> goods = new ArrayList<>();
        takeGoods(trader, goods);
        trader.setGoods(goods);
        traderRepository.save(trader);
        return goods;
    }

    private void takeGoods(Trader trader, List<Good> goods) {
        while (true) {
            Good good = Good.createOne();
            if (trader.getCapacity() - good.getWeight() <= 0
                    || trader.getMoney() - good.getPrice() <= 0) {
                break;
            }
            trader.setMoney(trader.getMoney() - good.getPrice());
            trader.setCapacity(trader.getCapacity() - good.getWeight());
            goods.add(good);
        }
    }

    public List<Good> getGoods() {
        Trader trader = traderRepository.findAll().get(0);
        return trader.getGoods();
    }

    public void save(Trader trader) {
        traderRepository.save(trader);
    }
}
