/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.web.events;

import com.kamigun.gw2eventtracker.model.Response;
import com.kamigun.gw2eventtracker.model.gw2.Event;
import java.util.List;
import java.util.Map;

/**
 *
 * @author shinobi
 */
public class ShowEventsWebResponse extends Response {

    private Map<String, String> eventNames;
    private Map<String, String> mapNames;
    private Map<String, String> worldNames;
    private List<Event> events;

    public Map<String, String> getMapNames() {
        return mapNames;
    }

    public void setMapNames(Map<String, String> mapNames) {
        this.mapNames = mapNames;
    }

    public Map<String, String> getWorldNames() {
        return worldNames;
    }

    public void setWorldNames(Map<String, String> worldNames) {
        this.worldNames = worldNames;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Map<String, String> getEventNames() {
        return eventNames;
    }

    public void setEventNames(Map<String, String> eventNames) {
        this.eventNames = eventNames;
    }
}
