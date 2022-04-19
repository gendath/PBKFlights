package com.pbk.flights.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Hub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String location;
    private String latitude;
    private String longitude;

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
}
