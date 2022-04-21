package com.pbk.flights.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
public class Hub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String location;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatLong(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private double latitude;

    private double longitude;

    @OneToMany
    @JoinColumn(name = "hub_id")
    private Set<Flight> outgoing = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "hub_id")
    private Set<Flight> incoming = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hub hub = (Hub) o;

        return id == hub.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Flight> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(Set<Flight> outgoing) {
        this.outgoing = outgoing;
    }

    public Set<Flight> getIncoming() {
        return incoming;
    }

    public void setIncoming(Set<Flight> incoming) {
        this.incoming = incoming;
    }
}
