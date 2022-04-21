package com.pbk.flights.Entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    Hub origin = new Hub();
    Hub destination = new Hub();
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createFlight() {
        Flight flight = Flight.createFlight(new Airplane("Cesna"),origin, destination,new Time(6),new Time(9), BigDecimal.valueOf(3.35));

    }
}