package com.pbk.flights.Services;

import com.pbk.flights.Dao.HubDao;
import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HubServicesImpl implements HubServices {

    @Autowired
    private final HubDao hubDao;

    public HubServicesImpl(HubDao hubDao) {
        this.hubDao = hubDao;
    }

    @Override
    public List<Hub> getAllHubs() {
        return (List<Hub>) hubDao.findAll();
    }

    @Override
    public Hub getHubByID(int hubID) {
        var h = hubDao.findById(hubID).orElse(null);
        if (h == null) throw new RuntimeException("Could not find Hub with given ID");
        return h;
    }

    @Override
    public Hub addHub(Hub hub) {
        return hubDao.save(hub);
    }

    @Override
    public boolean updateHub(Hub hub) {
        hubDao.save(hub);
        return true;
    }

    @Override
    public boolean removeHub(int hubID) {
        hubDao.deleteById(hubID);
        return true;
    }

    @Override
    public Set<Flight> getOutgoingFlights(int hubID) {
        var h = hubDao.findById(hubID).orElse(null);
        if (h == null) return new HashSet<>();
        return h.getOutgoing();
    }

    @Override
    public Set<Flight> getIncomingFlights(int hubID) {
        var h = hubDao.findById(hubID).orElse(null);
        if (h == null) return new HashSet<>();
        return h.getIncoming();
    }


}
