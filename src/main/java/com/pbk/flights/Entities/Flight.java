package com.pbk.flights.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;
    @OneToOne
    private Airplane plane;

    @OneToOne
    private Hub origin;

    @OneToOne
    private Hub destination;

    private Time departure;
    private Time arrival;

    private BigDecimal price;

    public Airplane getPlane() {
        return plane;
    }


    public Double getTripTime() {
        //returns time in hours
        return Double.valueOf(arrival.getTime()-departure.getTime())/3600000;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        return flightId != null ? flightId.equals(flight.flightId) : flight.flightId == null;
    }

    @Override
    public int hashCode() {
        return flightId != null ? flightId.hashCode() : 0;
    }
}
