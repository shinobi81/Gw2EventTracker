/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.web.events;

import com.kamigun.gw2eventtracker.model.Response;
import com.kamigun.gw2eventtracker.model.gw2.Event;
import com.kamigun.gw2eventtracker.model.gw2.Names;
import com.kamigun.gw2eventtracker.service.events.EventsService;
import com.kamigun.gw2eventtracker.service.events.GetEventsServiceRequest;
import com.kamigun.gw2eventtracker.service.events.GetEventsServiceResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    private Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/index.htm")
    public ModelAndView showEvents(@ModelAttribute EventsCommand eventsCommand) throws Exception {
        ShowEventsWebResponse showEventsWebResponse = new ShowEventsWebResponse();
        //{"id":"2010","name":"Seafarer's Rest"}
        //{"id":"62","name":"Cursed Shore"},

        String worldId = eventsCommand.getWorldId();
        String mapId = eventsCommand.getMapId();

        List<Event> events = new ArrayList<Event>();
        if (StringUtils.hasText(mapId) && StringUtils.hasText(worldId)) {
            GetEventsServiceRequest getEventsServiceRequest = new GetEventsServiceRequest();
            getEventsServiceRequest.setMapId(StringUtils.hasText(mapId) ? Integer.valueOf(mapId) : null);
            getEventsServiceRequest.setWorldId(StringUtils.hasText(worldId) ? Integer.valueOf(worldId) : null);
            GetEventsServiceResponse getEventsServiceResponse = eventsService.getEvents(getEventsServiceRequest);
            events = getEventsServiceResponse.getEvents();
        }

        showEventsWebResponse.setEvents(events);
        showEventsWebResponse.setEventNames(names.getEventNames());
        showEventsWebResponse.setMapNames(names.getMapNames());
        showEventsWebResponse.setWorldNames(names.getWorldNames());


        ModelAndView mav = new ModelAndView("index");
        mav.addObject(Response.BEAN_ID, showEventsWebResponse);
        return mav;
    }
}
