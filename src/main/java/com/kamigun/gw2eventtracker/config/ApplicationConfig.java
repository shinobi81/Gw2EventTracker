/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.config;

import com.kamigun.gw2eventtracker.model.gw2.Names;
import java.util.concurrent.TimeUnit;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author shinobi
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.kamigun.gw2eventtracker"}, excludeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Controller.class))
public class ApplicationConfig {
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
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
}
