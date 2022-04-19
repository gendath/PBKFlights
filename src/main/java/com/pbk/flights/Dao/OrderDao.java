package com.pbk.flights.Dao;

import com.pbk.flights.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {


}
