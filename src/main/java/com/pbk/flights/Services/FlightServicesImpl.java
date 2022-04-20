package com.pbk.flights.Services;

import com.pbk.flights.Dao.FlightDao;
import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
import com.pbk.flights.Entities.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightServicesImpl implements FlightServices{

    @Autowired
    private FlightDao flightDao;

    @Override
    public List<Flight> getAllFlights() {
        return flightDao.findAll();
    }

    @Override
    public Flight getFlightByID(int flightID) {
        var f = flightDao.findById(flightID).orElse(null);
        if (f == null) throw new RuntimeException("Invalid Flight ID");
        return f;
    }

    @Override
    public Flight addFlight(Flight flight) {
        flightDao.save(flight);
        return flight;
    }

    @Override
    public boolean updateFlight(Flight flight) {
        flightDao.save(flight);
        return true;
    }

    @Override
    public boolean removeFlight(int flightID) {
        flightDao.deleteById(flightID);
        return true;
    }

    @Override
    public List<Trip> getRoutes(Hub departure, Hub arrival) {
        return getRoutes(getAllFlights(), departure, arrival);
    }

    public static List<Trip> getRoutes(List<Flight> flights, Hub departure, Hub arrival) {
        List<Flight> filtered = flights.stream()
                .filter(flight -> flight.getOrigin() == departure)
                .collect(Collectors.toList());
        return filtered.stream()
                .map(Trip::new)
                .map(trip -> {
                    while (trip.getLastFlight().getDestination() != arrival) {
                        // TODO: Need to add time to this, at least an hour
                        var next = flights.stream()
                                .filter(flight -> flight.getOrigin() == trip.getLastFlight().getDestination())
                                .filter(flight -> !trip.hasVisited(flight.getDestination()))
                                .filter(flight -> flight.getDeparture().after(trip.getLastFlight().getArrival()))
                                .min(Comparator.comparingDouble(flight ->
                                        calculateDistanceMiles(flight.getDestination(), arrival)))
                                .orElse(null);
                        if (next == null) return null;
                        trip.addFlight(next);
                    }
                    return trip;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static double calculateDistanceMiles(Hub hub1, Hub hub2) {       // Haversine formula
        if (hub1 == null || hub2 == null) return -1d;
        if (hub1.getLatitude() == hub2.getLatitude() &&
                hub1.getLongitude() == hub2.getLongitude()) return 0;
        double theta = hub1.getLongitude() - hub2.getLongitude();
        double dist = Math.sin(Math.toRadians(hub1.getLatitude())) * Math.sin(Math.toRadians(hub2.getLatitude())) +
                Math.cos(Math.toRadians(hub1.getLatitude())) * Math.cos(Math.toRadians(hub2.getLatitude())) *
                        Math.cos(Math.toRadians(theta));
        dist = Math.toDegrees(Math.acos(dist));
        dist = dist * 60 * 1.1515;
        return dist;
    }
}
