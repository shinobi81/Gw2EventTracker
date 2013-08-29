/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.model.gw2;

import java.util.HashMap;

/**
 *
 * @author shinobi
 */
public class Names {

    private HashMap<String, String> eventNames = new HashMap<String, String>();
    private HashMap<String, String> mapNames = new HashMap<String, String>();
    private HashMap<String, String> worldNames = new HashMap<String, String>();

    public HashMap<String, String> getEventNames() {
        return eventNames;
    }

    public HashMap<String, String> getMapNames() {
        return mapNames;
    }

    public HashMap<String, String> getWorldNames() {
        return worldNames;
    }
}
