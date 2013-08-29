/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.model.gw2;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 *
 * @author shinobi
 */
public class EventDetail {
    private String name;
    private Integer level;
    @JsonProperty(value = "map_id")
    private Integer mapId;
    private ArrayList<String> flags;
    private Location location;
}
