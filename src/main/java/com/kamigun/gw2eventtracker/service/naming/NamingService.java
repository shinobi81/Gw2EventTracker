/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.naming;

/**
 *
 * @author shinobi
 */
public interface NamingService {

    void getEventNames(GetEventNamesRequest getEventNamesRequest) throws Exception;
    void getMapNames(GetMapNamesRequest getMapNamesRequest) throws Exception;
    void getWorldNames(GetWorldNamesRequest getWorldNamesRequest) throws Exception;
}
