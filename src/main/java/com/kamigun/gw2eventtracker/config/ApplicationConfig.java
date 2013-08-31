/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.config;

import com.kamigun.gw2eventtracker.model.gw2.Names;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author shinobi
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.kamigun.gw2eventtracker"})
public class ApplicationConfig extends WebMvcConfigurerAdapter {
    private Log logger = LogFactory.getLog(getClass());

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
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
