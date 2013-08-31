/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shinobi
 */
@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    private DefaultHttpClient httpClient;
    private ObjectMapper mapper = new ObjectMapper();
    private Log logger = LogFactory.getLog(getClass());

    @Override
    public GetEventsServiceResponse getEvents(GetEventsServiceRequest getEventsRequest) throws Exception {
        GetEventsServiceResponse getEventsServiceResponse;

        Integer eventId = getEventsRequest.getEventId();
        Integer mapId = getEventsRequest.getMapId();
        Integer worldId = getEventsRequest.getWorldId();

        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost("api.guildwars2.com").setPath("/v1/events.json");
        if (eventId != null) {
            builder.setParameter("event_id", String.valueOf(eventId));
        }
        if (mapId != null) {
            builder.setParameter("map_id", String.valueOf(mapId));
        }
        if (worldId != null) {
            builder.setParameter("world_id", String.valueOf(worldId));
        }
        HttpGet httpGet = new HttpGet(builder.build());

        HttpResponse httpResponse = httpClient.execute(httpGet);
        InputStream content = httpResponse.getEntity().getContent();
        StatusLine statusLine = httpResponse.getStatusLine();
        logger.info(statusLine);

        getEventsServiceResponse = mapper.readValue(content, GetEventsServiceResponse.class);
        
        return getEventsServiceResponse;
    }
}
