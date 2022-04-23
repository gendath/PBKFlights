package com.pbk.flights.Services;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightServicesImplTest {
    static Hub ohare;
    static Hub atl;
    static Hub dfw;
    static Hub lax;
    static List<Flight> flights;

    @BeforeAll
    static void setup() {
        ohare = new Hub("O'hare", "Chicago, IL", 41.978611,-87.904724, "ORD");
        atl = new Hub("Atlanta", "Atlanta, GA", 33.640411, -84.419853, "ATL");
        dfw = new Hub("Dallas/Fort-Worth", "Dallas, TX", 32.897480,-97.040443, "DFW");
        lax = new Hub("LAX", "Los Angeles, CA", 33.942791, -118.410042, "LAX");
        flights = new ArrayList<>();
        flights.add(Flight.createFlight(null, ohare, atl,
                new Time(6,0,0), new Time(9,0,0), BigDecimal.valueOf(100)));
        flights.add(Flight.createFlight(null, ohare, dfw,
                new Time(6,0,0), new Time(9,0,0), BigDecimal.valueOf(100)));
        flights.add(Flight.createFlight(null, atl, dfw,
                new Time(6,0,0), new Time(9,0,0), BigDecimal.valueOf(100)));
        flights.add(Flight.createFlight(null, dfw, lax,
                new Time(6,0,0), new Time(9,0,0), BigDecimal.valueOf(100)));
        flights.add(Flight.createFlight(null, lax, ohare,
                new Time(6,0,0), new Time(9,0,0), BigDecimal.valueOf(100)));
    }

    @Test
    void routesTest() {
        // Build flights data
        var hubs = List.of(ohare, atl, dfw, lax);
        for (int i = 0; i < 4; i++) {
            var d = hubs.get(ThreadLocalRandom.current().nextInt(hubs.size()));
            var a = hubs.get(ThreadLocalRandom.current().nextInt(hubs.size()));
            var routes = FlightServicesImpl.getRoutes(flights, d, a);
            System.out.printf("%s trip available from %s to %s%n", routes.size(), d.getName(), a.getName());
            for (var trip : routes) {
                System.out.println("##Start of Trip##");
                for (var flight : trip.getFlights()) {
                    System.out.printf("Flight from %s to %s%n", flight.getOrigin().getFAACode(), flight.getDestination().getFAACode());
                }
                System.out.println("##End of Trip##");
                System.out.println();
            }
            System.out.println();
        }

    }

    @Test
    void calculateDistanceTest() {
        assertEquals(Math.round(606.663), Math.round(Flight.calculateDistanceMiles(ohare, atl)), "Distance calculation incorrect");
        assertEquals(Math.round(729.821), Math.round(Flight.calculateDistanceMiles(atl, dfw)), "Distance calculation incorrect");
        assertEquals(Math.round(1232.289), Math.round(Flight.calculateDistanceMiles(dfw, lax)), "Distance calculation incorrect");

    }
}