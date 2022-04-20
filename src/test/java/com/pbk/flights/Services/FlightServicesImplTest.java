package com.pbk.flights.Services;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightServicesImplTest {
    static Hub ohare;
    static Hub atl;
    static Hub dfw;
    static Hub lax;
    static List<Flight> flights;

    @BeforeAll
    static void setup() {
        ohare = new Hub();
        ohare.setLatLong(	41.978611,-87.904724);
        atl = new Hub();
        atl.setLatLong(33.640411, -84.419853);
        dfw = new Hub();
        dfw.setLatLong(32.897480,-97.040443);
        lax = new Hub();
        lax.setLatLong(33.942791, -118.410042);
        flights = new ArrayList<>();
    }

    @Test
    void routesTest() {
        // Build flights data
        var routes = FlightServicesImpl.getRoutes(flights, ohare, atl);
    }

    @Test
    void calculateDistanceTest() {
        assertEquals(Math.round(606.663), Math.round(FlightServicesImpl.calculateDistanceMiles(ohare, atl)), "Distance calculation incorrect");
        assertEquals(Math.round(729.821), Math.round(FlightServicesImpl.calculateDistanceMiles(atl, dfw)), "Distance calculation incorrect");
        assertEquals(Math.round(1232.289), Math.round(FlightServicesImpl.calculateDistanceMiles(dfw, lax)), "Distance calculation incorrect");
    }
}