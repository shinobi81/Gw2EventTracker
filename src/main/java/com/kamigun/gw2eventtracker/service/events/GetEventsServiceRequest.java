/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamigun.gw2eventtracker.service.events;

/**
 *
 * @author shinobi
 */
public class GetEventsServiceRequest {

    private Integer worldId;
    private Integer mapId;
    private Integer eventId;

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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
