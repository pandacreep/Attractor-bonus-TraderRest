package com.trader.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Builder
@AllArgsConstructor
public class City {
    private String name;
    private int distance;

    public static City select() {
        Random r = new Random();
        List<String> cities = new ArrayList<>();
        cities.add("Алматы");
        cities.add("Тараз");
        cities.add("Павлодар");
        cities.add("Шымкент");
        cities.add("Уральск");
        return builder()
                .name(cities.get(r.nextInt(cities.size())))
                .distance(r.nextInt(21) + 20)
                .build();
    }
}