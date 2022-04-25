package com.trader.controller;

import com.trader.domain.City;
import com.trader.domain.Good;
import com.trader.domain.Info;
import com.trader.domain.Trader;
import com.trader.service.JourneyService;
import com.trader.service.TraderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TraderController {
    private final TraderService traderService;
    private final JourneyService journeyService;

    @GetMapping("/start")
    public Info start() {
        return Info.message("Application started");
    }

    @GetMapping("/init")
    public Info init() {
        traderService.init();
        return Info.message("Initialization done");
    }

    @GetMapping("/trader")
    public Trader getTrader() {
        return traderService.getTrader();
    }

    @GetMapping("/buy")
    public List<Good> buy() {
        return traderService.buy();
    }

    @GetMapping("/city")
    public City selectCity() {
        City city = City.select();
        traderService.updateDistance(city.getDistance());
        return city;
    }

    @GetMapping("/go")
    public Trader go() {
        return journeyService.passOneDay();
    }

    @GetMapping("/goods")
    public List<Good> goods() {
        return traderService.getGoods();
    }

    @GetMapping("/sell-goods")
    public Info sell() {
        return journeyService.sellGoods();
    }
}
