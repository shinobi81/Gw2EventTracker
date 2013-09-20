package com.kamigun.gw2eventtracker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.kamigun.gw2eventtracker.model.gw2.Names;
import com.kamigun.gw2eventtracker.service.events.EventsService;
import com.kamigun.gw2eventtracker.service.events.EventsServiceImpl;
import com.kamigun.gw2eventtracker.service.events.GetEventsServiceRequest;
import com.kamigun.gw2eventtracker.service.events.GetEventsServiceResponse;
import org.apache.http.impl.client.DefaultHttpClient;
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
        public DefaultHttpClient httpClient() {
            return new DefaultHttpClient();
        }

        @Bean
        public Names names() {
            return new Names();
        }
    }
    @Autowired
    private EventsService eventsService;
    @Autowired
    private Names names;
    
    @BeforeClass
    public static void setUpClass() {
        logger.info("BeforeClass");
    }

    @AfterClass
    public static void tearDownClass() {
        logger.info("AfterClass");
    }

    @Before
    public void setUp() {
        logger.info("Before");
    }

    @After
    public void tearDown() {
        logger.info("After");
    }

    @Test
    public void eventServiceTest() {
        GetEventsServiceResponse getEventsServiceResponse = null;
        try {
            GetEventsServiceRequest getEventsServiceRequest = new GetEventsServiceRequest();
            getEventsServiceRequest.setMapId(62);
            getEventsServiceRequest.setWorldId(2010);
            getEventsServiceResponse = eventsService.getEvents(getEventsServiceRequest);

        } catch (Exception e) {
            logger.error(e, e);
        }

        Assert.assertNotNull(getEventsServiceResponse);
    }
}