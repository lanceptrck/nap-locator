package com.devops3.naplocator.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinate {

    private String lat;
    private String lon;

    public Coordinate(){

    }

    public Coordinate(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(lat, that.lat) &&
                Objects.equals(lon, that.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
