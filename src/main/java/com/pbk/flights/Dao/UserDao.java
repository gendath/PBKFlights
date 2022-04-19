package com.pbk.flights.Dao;

import com.pbk.flights.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
