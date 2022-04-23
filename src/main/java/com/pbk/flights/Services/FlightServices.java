package com.pbk.flights.Services;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Trip;

import java.util.List;

public interface FlightServices {
    List<Flight> getAllFlights();
    Flight getFlightByID(int flightID);
    Flight addFlight(Flight flight);
    boolean updateFlight(Flight flight);
    boolean removeFlight(int flightID);
    List<Trip> getRoutes(String departure, String arrival);
}
