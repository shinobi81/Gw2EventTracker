package com.kamigun.gw2eventtracker;

import com.kamigun.gw2eventtracker.model.gw2.Event;
import com.kamigun.gw2eventtracker.model.gw2.Names;
import com.kamigun.gw2eventtracker.service.events.EventsService;
import com.kamigun.gw2eventtracker.service.events.EventsServiceImpl;
import com.kamigun.gw2eventtracker.service.events.GetEventsServiceRequest;
import com.kamigun.gw2eventtracker.service.events.GetEventsServiceResponse;
import com.kamigun.gw2eventtracker.service.naming.GetEventNamesRequest;
import com.kamigun.gw2eventtracker.service.naming.GetMapNamesRequest;
import com.kamigun.gw2eventtracker.service.naming.GetWorldNamesRequest;
import com.kamigun.gw2eventtracker.service.naming.NamingService;
import com.kamigun.gw2eventtracker.service.naming.NamingServiceImpl;
import com.kamigun.gw2eventtracker.service.tts.DoSpeakServiceRequest;
import com.kamigun.gw2eventtracker.service.tts.TextToSpeechService;
import com.kamigun.gw2eventtracker.service.tts.TextToSpeechServiceImpl;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author shinobi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ServicesTest {

    private static Logger logger = Logger.getLogger(ServicesTest.class.getSimpleName());
    @Autowired
    private EventsService eventsService;
    @Autowired
    private NamingService namingService;
    @Autowired
    private Names names;
    @Autowired
    private TextToSpeechService textToSpeechService;

    static {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%-4r [%t] %-5p %c %x - %m%n")));
    }

    @Configuration
    static class Config {

        @Bean
        public EventsService eventsService() {
            return new EventsServiceImpl();
        }

        @Bean
        public NamingService namingService() {
            return new NamingServiceImpl();
        }

        @Bean
        public CloseableHttpClient httpClient() {
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(60000, TimeUnit.MILLISECONDS);
            connectionManager.setDefaultMaxPerRoute(1000);
            connectionManager.setMaxTotal(1000);

            CloseableHttpClient httpclient = HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .setRetryHandler(new StandardHttpRequestRetryHandler(3, true))
                    .build();

            return httpclient;
        }

        @Bean
        public Names names() {
            return new Names();
        }

        @Bean
        public TextToSpeechService textToSpeechService() {
            return new TextToSpeechServiceImpl();
        }
    }

    @BeforeClass
    public static void setUpClass() {
//        logger.info("BeforeClass");
    }

    @AfterClass
    public static void tearDownClass() {
//        logger.info("AfterClass");
    }

    @Before
    public void setUp() {
//        logger.info("Before");
    }

    @After
    public void tearDown() {
//        logger.info("After");
    }

    @Test
    public void eventServiceTest() {
        GetEventsServiceResponse getEventsServiceResponse = null;
        try {
            GetEventsServiceRequest getEventsServiceRequest = new GetEventsServiceRequest();
            getEventsServiceRequest.setMapId(Integer.valueOf(62));
            getEventsServiceRequest.setWorldId(Integer.valueOf(2010));
            getEventsServiceResponse = this.eventsService.getEvents(getEventsServiceRequest);
        } catch (Exception e) {
            logger.error(e, e);
        }

        Assert.assertNotNull(getEventsServiceResponse);

        int eventsSize = 0;
        boolean eventFound = false;
        List<Event> events = getEventsServiceResponse.getEvents();
        if (events != null) {
            eventsSize = events.size();

            for (Event event : events) {
                String eventId = event.getEventId();
                String state = event.getState();
                if ("9522BF8A-5B2E-4257-AF17-49AF3BF81665".equals(eventId)
                        && (Event.STATE_ACTIVE.equals(state) || Event.STATE_FAIL.equals(state) || (Event.STATE_INACTIVE.equals(state))
                        || (Event.STATE_PREPARATION.equals(state)) || (Event.STATE_SUCCESS.equals(state)) || (Event.STATE_WARMUP.equals(state)))) {
                    eventFound = true;
                    break;
                }
            }
        }

        Assert.assertEquals(86, eventsSize);
        Assert.assertTrue(eventFound);
    }

    @Test
    public void eventNamesIntegrityTest() {
        Assert.assertEquals(2841, names.getEventNames().size());

        try {
            namingService.getEventNames(new GetEventNamesRequest());
        } catch (Exception e) {
            logger.error(e, e);
        }

        Assert.assertEquals(2841, names.getEventNames().size());

        Assert.assertEquals("Stop the skritt burglar before it escapes with the treasure.", names.getEventNames().get("17F43535-6677-4AB5-A1F3-B34D833BD97D"));
    }

    @Test
    public void mapNamesIntegrityTest() {
        Assert.assertEquals(28, names.getMapNames().size());

        try {
            namingService.getMapNames(new GetMapNamesRequest());
        } catch (Exception e) {
            logger.error(e, e);
        }

        Assert.assertEquals(28, names.getMapNames().size());

        Assert.assertEquals("Queensdale", names.getMapNames().get("15"));
    }

    @Test
    public void worldNamesIntegrityTest() {
        Assert.assertEquals(51, names.getWorldNames().size());

        try {
            namingService.getWorldNames(new GetWorldNamesRequest());
        } catch (Exception e) {
            logger.error(e, e);
        }

        Assert.assertEquals(51, names.getWorldNames().size());

        Assert.assertEquals("Stormbluff Isle", names.getWorldNames().get("1011"));
    }

    @Test
    public void ttsServiceTest() throws Exception {
        try {
            this.namingService.getEventNames(new GetEventNamesRequest());
        } catch (Exception e) {
            logger.error(e, e);
        }
        GetEventsServiceResponse getEventsServiceResponse = null;
        try {
            GetEventsServiceRequest getEventsServiceRequest = new GetEventsServiceRequest();
            getEventsServiceRequest.setMapId(Integer.valueOf(35));
            getEventsServiceRequest.setWorldId(Integer.valueOf(2010));
            getEventsServiceResponse = this.eventsService.getEvents(getEventsServiceRequest);
        } catch (Exception e) {
            logger.error(e, e);
        }
        if (getEventsServiceResponse != null) {
            List<Event> events = getEventsServiceResponse.getEvents();
            if (events != null) {
                for (Event event : events) {
                    String eventId = event.getEventId();
                    if ("33F76E9E-0BB6-46D0-A3A9-BE4CDFC4A3A4".equals(eventId)) {
                        String eventName = (String) this.names.getEventNames().get(eventId);
                        String state = event.getState();

                        DoSpeakServiceRequest doSpeakServiceRequest = new DoSpeakServiceRequest();
                        doSpeakServiceRequest.setText(eventName + " " + state);
                        this.textToSpeechService.doSpeak(doSpeakServiceRequest);

                        break;
                    }
                }
            }
        }
    }
}
