package com.devops3.naplocator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class NapBox {

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String napBoxName;
    private String lat;
    private String lon;
    private String address;

    public NapBox() {

    }

    public NapBox(long id, String napBoxName, String lat, String lon, String address) {
        this.id = id;
        this.napBoxName = napBoxName;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public NapBox(String napBoxName, String lat, String lon, String address) {
        this.id = id;
        this.napBoxName = napBoxName;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNapBoxName() {
        return napBoxName;
    }

    public void setNapBoxName(String napBoxName) {
        this.napBoxName = napBoxName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore
    public Coordinate getCoordinate() {
        return new Coordinate(this.lat, this.lon);
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
        NapBox napBox = (NapBox) o;
        return id == napBox.id &&
                Objects.equals(lat, napBox.lat) &&
                Objects.equals(lon, napBox.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon);
    }

    @Override
    public String toString() {
        return "NapBox{" +
                "id=" + id +
                ", napBoxName='" + napBoxName + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
