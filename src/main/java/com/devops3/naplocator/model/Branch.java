package com.devops3.naplocator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Branch {

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String branchName;
    private String lat;
    private String lon;
    private String address;

    public Branch() {

    }

    public Branch(long id, String branchName, String lat, String lon, String address) {
        this.id = id;
        this.branchName = branchName;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public Branch(String branchName, String lat, String lon, String address) {
        this.id = id;
        this.branchName = branchName;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
        Branch branch = (Branch) o;
        return id == branch.id &&
                Objects.equals(lat, branch.lat) &&
                Objects.equals(lon, branch.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", branchName='" + branchName + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
