package com.pbk.flights.Services;

import com.pbk.flights.Dao.HubDao;
import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;

import java.util.List;

public class HubServicesImpl implements HubServices {

    private final HubDao hubDao;


    public HubServicesImpl(HubDao hubDao) {
        this.hubDao = hubDao;
    }

    @Override
    public List<Hub> getAllHubs() {
        return null;
    }

    @Override
    public Hub getHubByID(int hubID) {
        return null;
    }

    @Override
    public Flight addHub(Hub hub) {
        return null;
    }

    @Override
    public boolean updateHub(Hub hub) {
        return false;
    }

    @Override
    public boolean removeHub(int hubID) {
        return false;
    }

    @Override
    public List<Hub> getOutgoingFlights() {
        return null;
    }

    @Override
    public List<Hub> getIncomingFlights() {
        return null;
    }


}
