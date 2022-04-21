package com.pbk.flights.Services;

import com.pbk.flights.Entities.Trip;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TripServices {

    List<Trip> getAllTrips();
    Trip getTripByID(int tripID);
    Trip addTrip(Trip trip);
    boolean updateTrip(Trip trip);
    boolean removeTrip(int tripID);
}
