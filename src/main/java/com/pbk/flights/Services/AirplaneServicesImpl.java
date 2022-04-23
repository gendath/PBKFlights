package com.pbk.flights.Services;

import com.pbk.flights.Dao.AirplaneDao;
import com.pbk.flights.Entities.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneServicesImpl implements AirplaneServices {

    @Autowired
    AirplaneDao airplaneDao;

    @Override
    public List<Airplane> getAllAirplanes() {
        return airplaneDao.findAll();
    }

    @Override
    public Airplane getAirplaneByID(int airplaneID) {
        var o = airplaneDao.findById(airplaneID).orElse(null);
        if (o == null) throw new RuntimeException("Invalid Airplane ID");
        return o;
    }

    @Override
    public Airplane addAirplane(Airplane airplane) {
        return airplaneDao.save(airplane);
    }

    @Override
    public boolean updateAirplane(Airplane airplane) {
        airplaneDao.save(airplane);
        return true;
    }

    @Override
    public boolean removeAirplane(int airplaneID) {
        airplaneDao.deleteById(airplaneID);
        return true;
    }

}
