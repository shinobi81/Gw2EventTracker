/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.naming;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamigun.gw2eventtracker.model.gw2.EventName;
import com.kamigun.gw2eventtracker.model.gw2.MapName;
import com.kamigun.gw2eventtracker.model.gw2.Names;
import com.kamigun.gw2eventtracker.model.gw2.GwServiceError;
import com.kamigun.gw2eventtracker.model.gw2.WorldName;
import java.io.InputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shinobi
 */
@Service
public class NamingServiceImpl implements NamingService {

    @Autowired
    private DefaultHttpClient httpClient;
    @Autowired
    private Names names;
    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    public void getEventNames(GetEventNamesRequest getEventNamesRequest) throws Exception {
        String lang = getEventNamesRequest.getLang();

        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost("api.guildwars2.com").setPath("/v1/event_names.json")
                .setParameter("lang", lang);
        HttpGet httpGet = new HttpGet(builder.build());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream content = httpResponse.getEntity().getContent();
            StatusLine statusLine = httpResponse.getStatusLine();
            logger.info(statusLine);

            if (statusLine.getStatusCode() == 200) {
                List<EventName> eventNames = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, EventName.class));
                for (EventName eventName : eventNames) {
                    names.getEventNames().put(eventName.getId(), eventName.getName());
                }
            } else {
                GwServiceError error = mapper.readValue(content, GwServiceError.class);
                logger.error(error.getText());
            }
        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            httpGet.releaseConnection();
        }
    }

    @Override
    public void getMapNames(GetMapNamesRequest getMapNamesRequest) throws Exception {
        String lang = getMapNamesRequest.getLang();

        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost("api.guildwars2.com").setPath("/v1/map_names.json")
                .setParameter("lang", lang);
        HttpGet httpGet = new HttpGet(builder.build());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream content = httpResponse.getEntity().getContent();
            StatusLine statusLine = httpResponse.getStatusLine();
            logger.info(statusLine);

            if (statusLine.getStatusCode() == 200) {
                List<MapName> mapNames = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, MapName.class));
                for (MapName mapName : mapNames) {
                    names.getMapNames().put(mapName.getId(), mapName.getName());
                }
            } else {
                GwServiceError error = mapper.readValue(content, GwServiceError.class);
                logger.error(error.getText());
            }
        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            httpGet.releaseConnection();
        }
    }

    @Override
    public void getWorldNames(GetWorldNamesRequest getWorldNamesRequest) throws Exception {
        String lang = getWorldNamesRequest.getLang();

        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost("api.guildwars2.com").setPath("/v1/world_names.json")
                .setParameter("lang", lang);
        HttpGet httpGet = new HttpGet(builder.build());

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream content = httpResponse.getEntity().getContent();
            StatusLine statusLine = httpResponse.getStatusLine();
            logger.info(statusLine);

            if (statusLine.getStatusCode() == 200) {
                List<WorldName> worldNames = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, WorldName.class));
                for (WorldName worldName : worldNames) {
                    names.getWorldNames().put(worldName.getId(), worldName.getName());
                }
            } else {
                GwServiceError error = mapper.readValue(content, GwServiceError.class);
                logger.error(error.getText());
            }
        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            httpGet.releaseConnection();
        }
    }

    @PostConstruct
    public void post() throws Exception {
        getEventNames(new GetEventNamesRequest());
        getMapNames(new GetMapNamesRequest());
        getWorldNames(new GetWorldNamesRequest());
    }
}
