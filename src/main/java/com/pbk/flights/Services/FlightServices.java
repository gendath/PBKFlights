package com.pbk.flights.Services;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
import com.pbk.flights.Entities.Trip;

import java.sql.Time;
import java.util.List;

public interface FlightServices {
    List<Flight> getAllFlights();
    Flight getFlightByID(int flightID);
    Flight addFlight(Flight flight);
    boolean updateFlight(Flight flight);
    boolean removeFlight(int flightID);
    List<Trip> getRoutes(Hub departure, Hub arrival, Time time);
}
