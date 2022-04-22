package com.pbk.flights.Services;

import com.pbk.flights.Entities.Airplane;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AirplaneServices {

    List<Airplane> getAllAirplanes();
    Airplane getAirplaneByID(int planeID);
    Airplane addAirplane(Airplane plane);
    boolean updateAirplane(Airplane plane);
    boolean removeAirplane(int planeID);
}
