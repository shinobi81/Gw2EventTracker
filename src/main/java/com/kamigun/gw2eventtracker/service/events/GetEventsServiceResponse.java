/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.events;

import com.kamigun.gw2eventtracker.model.gw2.Event;
import com.kamigun.gw2eventtracker.model.gw2.GwServiceError;
import java.util.List;

/**
 *
 * @author shinobi
 */
public class GetEventsServiceResponse extends GwServiceError {

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
