package com.pbk.flights.Services;

import com.pbk.flights.Dao.FlightDao;
import com.pbk.flights.Dao.HubDao;
import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
import com.pbk.flights.Entities.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightServicesImpl implements FlightServices{

    @Autowired
    private HubDao hubDao;

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
        flight.getOrigin().getOutgoing().add(flight);
        flight.getDestination().getIncoming().add(flight);
        flightDao.save(flight);
        hubDao.save(flight.getOrigin());
        hubDao.save(flight.getDestination());


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
    public List<Trip> getRoutes(String departure, String arrival) {
        var dep = hubDao.findByFAACodeIgnoreCase(departure);
        var arr = hubDao.findByFAACodeIgnoreCase(arrival);
        if (dep.isEmpty() || arr.isEmpty()) throw new RuntimeException("Invalid Hub code");
        return getRoutes(getAllFlights(), dep.get(0), arr.get(0));
    }

    public static List<Trip> getRoutes(List<Flight> flights, Hub departure, Hub arrival) {
        List<Flight> filtered = flights.stream()
                .filter(flight -> flight.getOrigin() == departure)
                .collect(Collectors.toList());
        return filtered.stream()
                .map(Trip::new)
                .map(trip -> trip.findRoute(flights, arrival))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
