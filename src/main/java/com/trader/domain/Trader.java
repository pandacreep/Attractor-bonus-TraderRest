package com.trader.domain;

import com.trader.domain.enam.GoodQuality;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "trader")
public class Trader {
    @Id
    private String id;
    private double initialMoney;
    private double money;
    private int speedMax;
    private int capacity;
    private int distance;
    private boolean isDecisionToChangeCity;
    private int day;
    private String action;
    private List<Good> goods = new ArrayList<>();

    public static Trader random() {
        Random r = new Random();
        var trader = builder()
                .id(UUID.randomUUID().toString())
                .initialMoney(r.nextInt(51) + 100)
                .speedMax(5)
                .capacity(r.nextInt(351) + 50)
                .distance(100) //TODO take distance from city
                .isDecisionToChangeCity(false)
                .day(0)
                .build();
        trader.setMoney(trader.getInitialMoney());
        return trader;
    }

    public int getSpeed() {
        return (new Random()).nextInt(getSpeedMax()) + 1;
    }

    public void spoilQuality(Good good) {
        switch (good.getQuality()) {
            case NORMAL:
                good.setQuality(GoodQuality.SLIGHTLY_SPOILED);
                break;
            case SLIGHTLY_SPOILED:
                good.setQuality(GoodQuality.HALF_SPOILED);
                break;
            case HALF_SPOILED:
                good.setQuality(GoodQuality.ALMOST_SPOILED);
                break;
            case ALMOST_SPOILED:
                good.setQuality(GoodQuality.TRASH);
                break;
            default:
                good.setQuality(GoodQuality.TRASH);
                break;
        }
    }
}
