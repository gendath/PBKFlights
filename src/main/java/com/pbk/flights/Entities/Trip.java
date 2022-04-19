package com.pbk.flights.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
