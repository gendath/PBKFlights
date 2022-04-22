package com.pbk.flights.Services;

import com.pbk.flights.Dao.AirplaneDao;
import com.pbk.flights.Entities.Airplane;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AirplaneServicesImpl implements AirplaneServices {

    @Autowired
    AirplaneDao airplaneDao;

    @Override
    public List<Airplane> getAllAirplanes() {
        return airplaneDao.findAll();
    }

    @Override
    public Airplane getAirplaneByID(int planeID) {
        var a = airplaneDao.findById(planeID).orElse(null);
        if (a == null) throw new RuntimeException("Invalid Flight ID");
        return a;
    }

    @Override
    public Airplane addAirplane(Airplane plane) {
        return airplaneDao.save(plane);
    }

    @Override
    public boolean updateAirplane(Airplane plane) {
        airplaneDao.save(plane);
        return true;
    }

    @Override
    public boolean removeAirplane(int planeID) {
        airplaneDao.deleteById(planeID);
        return true;
    }

}
