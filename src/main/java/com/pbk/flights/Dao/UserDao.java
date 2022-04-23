package com.pbk.flights.Dao;

import com.pbk.flights.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    List<User> findByEmailIgnoreCase(String email);
    List<User> findByEmailIgnoreCaseAndPassword(String email, String password);
}
