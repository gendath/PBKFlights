package com.pbk.flights.Entities;

import java.util.Set;

public class Hub {
    private String name;
    private String location;
    private Set<Airplane> planes;
    private Set<Flight> outgoing;
    private Set<Flight> incoming;
}
