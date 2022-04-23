package com.pbk.flights.Services;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;

import java.util.List;
import java.util.Set;

public interface HubServices {


    List<Hub> getAllHubs();
    Hub getHubByID(int hubID);
    Hub addHub(Hub hub);
    boolean updateHub(Hub hub);
    boolean removeHub(int hubID);
    Set<Flight> getOutgoingFlights(int hubID);
    Set<Flight> getIncomingFlights(int hubID);
}
