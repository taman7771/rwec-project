package org.launchcode.models;

import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class EventData {

    static ArrayList<Event> events = new ArrayList<>();

    // getAll
    public static ArrayList<Event> getAll() {
        return events;
    }

    // add
    public static void add(Event newEvent) {
        events.add(newEvent);
    }

    // remove
    public static void remove(int id) {
        Event eventToRemove = getById(id);
        events.remove(eventToRemove);
    }

    // getById
    public static Event getById(int id) {

        Event theEvent = null;

        for (Event candidateEvent : events) {
            if (candidateEvent.getEventId() == id) {
                theEvent = candidateEvent;
            }
        }

        return theEvent;
    }

}
