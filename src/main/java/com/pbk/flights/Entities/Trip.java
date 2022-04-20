package com.pbk.flights.Entities;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Trip {

    @Id
    private int id;

    @OneToMany
    @JoinColumn(name = "trip_id")
    private Set<Flight> flights = new HashSet<>();

    @OneToOne
    private Hub origin;

    @OneToOne
    private Hub destination;

    public Trip() {
    }

    public Trip(Flight flight) {
        flights.add(flight);
    }


    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public double getTravelTime() {
        Optional<Double> op = flights.stream()
                .map(Flight::getTripTime)
                .reduce(Double::sum);
        if (op.isPresent()) {
            return op.get();
        }
        return -1d;
    }

    public Flight getLastFlight() {
        if (flights.isEmpty()) return null;
        var sorted = flights.stream()
                .sorted(Comparator.comparing(Flight::getDeparture))
                .collect(Collectors.toList());
        if (sorted.isEmpty()) return null;
        return sorted.get(sorted.size()-1);
    }

    public boolean hasVisited(Hub hub) {
        if (flights == null || flights.isEmpty()) return false;
        if (flights.stream().anyMatch(flight -> flight.getOrigin() == hub)) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        return id == trip.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
// TODO: 4/19/2022 price
}
