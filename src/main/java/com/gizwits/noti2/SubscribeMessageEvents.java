package com.gizwits.noti2;

import com.gizwits.noti2.client.Events;

import java.util.List;

/**
 * Created by feel on 2017/10/11.
 */
public class SubscribeMessageEvents {

    private List<Events> events;

    public SubscribeMessageEvents() {
    }

    public SubscribeMessageEvents(List<Events> events) {
        this.events = events;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "SubscribeMessageEvents{" +
                "events=" + events +
                '}';
    }
}
