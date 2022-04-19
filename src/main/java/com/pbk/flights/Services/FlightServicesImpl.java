package com.pbk.flights.Services;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
import com.pbk.flights.Entities.Trip;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class FlightServicesImpl implements FlightServices{
    @Override
    public List<Flight> getAllFlights() {
        return null;
    }

    @Override
    public Flight getFlightByID(int flightID) {
        return null;
    }

    @Override
    public Flight addFlight(Flight flight) {
        return null;
    }

    @Override
    public boolean updateFlight(Flight flight) {
        return false;
    }

    @Override
    public boolean removeFlight(int flightID) {
        return false;
    }

    @Override
    public List<Trip> getRoutes(Hub departure, Hub arrival, Time time) {
        return null;
    }
}
