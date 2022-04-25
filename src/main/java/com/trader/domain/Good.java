package com.trader.domain;

import com.trader.domain.enam.GoodQuality;
import com.trader.domain.enam.GoodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Random;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "goods")
public class Good {
    @Id
    private String id;
    private int weight;
    private String type;
    private double price;
    private GoodQuality quality;

    public static Good createOne() {
        Random random = new Random();

        var good = builder()
                .id(UUID.randomUUID().toString())
                .weight(random.nextInt(15) +10)
                .type(GoodType.values()[random.nextInt(GoodType.values().length)].getValue())
                .price(random.nextInt(5) + 5)
                .quality(GoodQuality.NORMAL)
                .build();
        return good;
    }
}
