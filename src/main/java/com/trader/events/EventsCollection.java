package com.trader.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventsCollection {
    private final List<Event> states = new ArrayList<>();

    public EventsCollection() {
        states.add(new EventCartBroken());
        states.add(new EventGoodSpoiled());
        states.add(new EventMeetLocal());
        states.add(new EventOrdinaryDay());
        states.add(new EventRiver());
        states.add(new EventInn());
        states.add(new EventRobbers());
        states.add(new EventRain());
        states.add(new EventStraightRoad());
    }

    public Event getEvent() {
        return states.get(new Random().nextInt(states.size()));
    }
}
