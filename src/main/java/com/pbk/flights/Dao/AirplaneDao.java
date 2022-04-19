package com.pbk.flights.Dao;

import com.pbk.flights.Entities.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneDao extends JpaRepository<Airplane, Integer> {
}
