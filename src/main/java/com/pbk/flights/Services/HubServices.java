package com.pbk.flights.Services;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HubServices {


    List<Hub> getAllHubs();
    Hub getHubByID(int hubID);
    Flight addHub(Hub hub);
    boolean updateHub(Hub hub);
    boolean removeHub(int hubID);
    List<Hub> getOutgoingFlights();
    List<Hub> getIncomingFlights();
}
