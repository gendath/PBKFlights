package com.pbk.flights.Dao;

import com.pbk.flights.Entities.Hub;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HubDao extends CrudRepository<Hub,Integer> {
    List<Hub> findByFAACodeIgnoreCase(String code);
}
