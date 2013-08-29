/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.web.events;

import com.kamigun.gw2eventtracker.model.gw2.Event;
import com.kamigun.gw2eventtracker.model.gw2.Names;
import com.kamigun.gw2eventtracker.service.events.EventsService;
import com.kamigun.gw2eventtracker.service.events.GetEventsServiceRequest;
import com.kamigun.gw2eventtracker.service.events.GetEventsServiceResponse;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author shinobi
 */
@Controller
public class EventsController {

    @Autowired
    private Names names;
    @Autowired
    private EventsService eventsService;
    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @RequestMapping(value = "/events.htm")
    public String showEvents() throws Exception {
        //{"id":"2010","name":"Seafarer's Rest"}
        //{"id":"62","name":"Cursed Shore"},

        GetEventsServiceRequest getEventsServiceRequest = new GetEventsServiceRequest();
        getEventsServiceRequest.setMapId(62);
        getEventsServiceRequest.setWorldId(2010);
        GetEventsServiceResponse getEventsServiceResponse = eventsService.getEvents(getEventsServiceRequest);
        List<Event> events = getEventsServiceResponse.getEvents();
        for (Event event : events) {
            String eventId = event.getEventId();
            String state = event.getState();
            logger.info(names.getEventNames().get(eventId) + " " + state);
        }
        
        return "index";
    }
}
