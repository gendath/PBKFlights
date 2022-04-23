package com.pbk.flights.Services;

import com.pbk.flights.Entities.Airplane;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AirplaneServices {

    List<Airplane> getAllAirplanes();
    Airplane getAirplaneByID(int airplaneID);
    Airplane addAirplane(Airplane airplane);
    boolean updateAirplane(Airplane airplane);
    boolean removeAirplane(int airplaneID);
}
