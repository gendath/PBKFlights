package com.pbk.flights.Dao;

import com.pbk.flights.Entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripDao extends JpaRepository<Trip, Integer> {
}
