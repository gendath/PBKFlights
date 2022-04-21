package com.pbk.flights.Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer flightId;
    @OneToOne
    private Airplane plane;

    @OneToOne
    private Hub origin;

    @OneToOne
    private Hub destination;

    @ManyToMany(mappedBy = "flights")
    private Set<Trip> trips= new HashSet<>();

    private Time departure;
    private Time arrival;

    private BigDecimal price;

    public Airplane getPlane() {
        return plane;
    }

    public Flight() {
    }

    public Flight(Airplane plane, Hub origin, Hub destination) {
        this.plane = plane;
        this.origin = origin;
        this.destination = destination;
    }

    public Double getTripTime() {
        //returns time in hours
        return Double.valueOf(arrival.getTime()-departure.getTime())/3600000;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public void setPlane(Airplane plane) {
        this.plane = plane;
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

    public Time getDeparture() {
        return departure;
    }

    public void setDeparture(Time departure) {
        this.departure = departure;
    }

    public Time getArrival() {
        return arrival;
    }

    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static Flight createFlight(Airplane plane, Hub origin, Hub destination, Time departure, Time arrival, BigDecimal price){

        Flight flight = new Flight(plane,origin,destination);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setPrice(price);
        flight.getOrigin().getOutgoing().add(flight);
        flight.getDestination().getIncoming().add(flight);
        return flight;



    }
}
