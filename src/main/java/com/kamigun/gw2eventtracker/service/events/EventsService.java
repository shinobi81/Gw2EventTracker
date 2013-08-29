/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.events;

/**
 *
 * @author shinobi
 */
public interface EventsService {
    GetEventsServiceResponse getEvents(GetEventsServiceRequest getEventsServiceRequest) throws Exception;
}
