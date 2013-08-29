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
public class Location {

    private String type;
    private ArrayList<Double> centre;
    private Double radius;
    private Double rotation;
    private Double height;
    @JsonProperty(value = "z_range")
    private ArrayList<Double> zRange;
    private ArrayList<ArrayList<Double>> points;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Double> getCentre() {
        return centre;
    }

    public void setCentre(ArrayList<Double> centre) {
        this.centre = centre;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getRotation() {
        return rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public ArrayList<Double> getzRange() {
        return zRange;
    }

    public void setzRange(ArrayList<Double> zRange) {
        this.zRange = zRange;
    }

    public ArrayList<ArrayList<Double>> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<ArrayList<Double>> points) {
        this.points = points;
    }
}
