package com.pbk.flights.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Component
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    @JoinTable(name = "trip_flight",joinColumns = @JoinColumn(name = "trip_id"),inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private Set<Flight> flights = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "trip_seat_mapping",
            joinColumns = {@JoinColumn(name = "trip_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "seat")
    @Column(name = "seats")
    private final Map<Integer, String> seats = new HashMap<>();//store seat for each leg of flight Map<FlightID, Seat>

    @OneToOne
    private Hub origin;

    @OneToOne
    private Hub destination;



    public Trip() {
    }

    public Trip(Flight flight) {
        flights.add(flight);
    }


    public void addFlight(Flight flight, String seat) {
        seats.put(flight.getFlightId(), seat);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Flight> getFlights() {
        return flights;
    }



    public Hub getOrigin() {
        return origin;
    }

    public void setOrigin(Hub origin) {
        this.origin = origin;
    }

    public Hub getDestination() {
        return destination;
    }

    public void setDestination(Hub destination) {
        this.destination = destination;
    }

    public Map<Integer, String> getSeats() {
        return seats;
    }
// TODO: 4/19/2022 price
}
