package com.pbk.flights.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private final Set<Flight> flights = new LinkedHashSet<>();

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

    private double totalTravelTime =0;

    private BigDecimal totalPrice= BigDecimal.ZERO;



    public Trip() {
    }

    public Trip(Flight flight) {
        seats.put(flight.getFlightId(), null);
        flights.add(flight);
        this.totalPrice=this.totalPrice.add(flight.getPrice());
        this.totalTravelTime += flight.getTripTime();
    }


    public void addFlight(Flight flight, String seat) {
        seats.put(flight.getFlightId(), seat);
        flights.add(flight);
        this.totalPrice=this.totalPrice.add(flight.getPrice());
        this.totalTravelTime += flight.getTripTime();

    }

    public boolean updateSeat(Flight flight, String seat, int userID) {
        if (!flight.getPlane().updateSeat(seat, userID)) return false;
        seats.put(flight.getFlightId(), seat);
        return true;
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

    public double getTotalTravelTime() {
        return totalTravelTime;
    }

    public void setTotalTravelTime(double totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = BigDecimal.valueOf(totalPrice);
    }

    public Flight getLastFlight() {
        if (flights.isEmpty()) return null;
        Flight result = null;
        for (var f : flights) result = f;
        return result;
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


    public Trip findRoute(List<Flight> availableFlights, Hub arrival) {
        if (getLastFlight() == null) return null;
        while (getLastFlight().getDestination() != arrival) {
            var temp = availableFlights.stream()
                    .filter(flight -> flight.getOrigin() == getLastFlight().getDestination())
                    .collect(Collectors.toList());
            var next = temp.stream()
                    .filter(flight -> !hasVisited(flight.getDestination()))
                    // TODO: Need to add time to this, at least an hour
                    // TODO: this filter should be a sort method instead
//                  .filter(flight -> flight.getDeparture().after(trip.getLastFlight().getArrival()))
                    .min(Comparator.comparingDouble(flight ->
                            Flight.calculateDistanceMiles(flight.getDestination(), arrival)))
                    .orElse(null);
            if (next == null) return null;

            addFlight(next,null);
        }
        return this;
    }

}
