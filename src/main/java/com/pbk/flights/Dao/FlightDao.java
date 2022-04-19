package com.pbk.flights.Dao;

import com.pbk.flights.Entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightDao extends JpaRepository<Flight, Integer> {
}
