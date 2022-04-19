package com.pbk.flights.Dao;

import com.pbk.flights.Entities.Booker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookerDao extends JpaRepository<Booker,Integer> {


}
