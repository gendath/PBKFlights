package com.pbk.flights.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripTest {
    Hub origin;
    Hub destination;
    Hub layover;
    Trip trip;

    Flight flight1;
    Flight flight2;

    @BeforeEach
    void setUp() {
        trip=new Trip();


        origin = new Hub("Origin 1","somewhere",39.18,-76.67,"SMW");

        destination = new Hub("Destination 2","somewhere",32.89,-97.04, "SM2");
        layover = new Hub("Layover","somewhere",36.78,-119.72, "LYO");

        flight1 = Flight.createFlight(new Airplane("XL"), origin,layover,new Time(6,0,0),new Time(9,0,0), BigDecimal.valueOf(335));
        flight2 = Flight.createFlight(new Airplane("XL"),layover,destination,new Time(11,0,0),new Time(14,0,0), BigDecimal.valueOf(335));
    }

    @Test
    void addFlight() {
        trip.addFlight(flight1,"AW");
        trip.addFlight(flight2,"AW");
        assertEquals(2, trip.getFlights().size());
    }

    @Test
    void getTravelTime() {

        trip.addFlight(flight1,"AW");
        trip.addFlight(flight2,"AW");
        assertEquals(6.0,trip.getTravelTime());
    }


}