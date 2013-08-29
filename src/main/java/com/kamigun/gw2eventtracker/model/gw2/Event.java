/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.model.gw2;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author shinobi
 */
public class Event {

    public static final String STATE_INACTIVE = "Inactive"; // The event is not running.
    public static final String STATE_ACTIVE = "Active"; // The event is running now.
    public static final String STATE_SUCCESS = "Success"; // The event has succeeded.
    public static final String STATE_FAIL = "Fail"; // The event has failed.
    public static final String STATE_WARMUP = "Warmup"; // The event is inactive and waiting for certain criteria to be met before becoming Active.
    public static final String STATE_PREPARATION = "Preparation"; // The criteria for the event to start have been met, but certain activities (such as an NPC dialogue) have not completed yet. After the activites have been completed, the event will become Active.
    @JsonProperty(value = "world_id")
    private Integer worldId;
    @JsonProperty(value = "map_id")
    private Integer mapId;
    @JsonProperty(value = "event_id")
    private String eventId;
    private String state;

    public Integer getWorldId() {
        return worldId;
    }

    public void setWorldId(Integer worldId) {
        this.worldId = worldId;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
