package com.pbk.flights.Services;

import com.pbk.flights.Dao.TripDao;
import com.pbk.flights.Entities.Trip;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TripServicesImpl implements TripServices {

    @Autowired
    TripDao tripDao;

    @Override
    public List<Trip> getAllTrips() {
        return tripDao.findAll();
    }

    @Override
    public Trip getTripByID(int tripID) {
        var t = tripDao.findById(tripID).orElse(null);
        if (t == null) throw new RuntimeException("Invalid Flight ID");
        return t;
    }

    @Override
    public Trip addTrip(Trip trip) {
        return tripDao.save(trip);
    }

    @Override
    public boolean updateTrip(Trip trip) {
        tripDao.save(trip);
        return true;
    }

    @Override
    public boolean removeTrip(int tripID) {
        tripDao.deleteById(tripID);
        return true;
    }

}
