package com.trader.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Info {
    private String message;

    public static Info message(String text) {
        return builder().message(text).build();
    }
}
