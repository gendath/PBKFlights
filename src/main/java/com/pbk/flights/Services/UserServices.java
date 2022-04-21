package com.pbk.flights.Services;

import com.pbk.flights.Entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserServices {
    boolean login(User user, HttpServletRequest request);
    boolean logout(HttpServletRequest request);
    List<User> getUsers();
    boolean addUser(User user, HttpServletRequest request);
    boolean updateUser(User user);
    boolean deleteUser(int ID);
    User getUser(int parseInt);
}
