package com.trader.events;

import com.trader.domain.Trader;

public interface Event {
    int action(Trader trader);
}
