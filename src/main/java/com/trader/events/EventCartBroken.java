package com.trader.events;

import com.trader.domain.Trader;

public class EventCartBroken implements Event{
    @Override
    public int action(Trader trader) {
        trader.setAction("День в пустую, стоим на месте");
        return 0;
    }
}
