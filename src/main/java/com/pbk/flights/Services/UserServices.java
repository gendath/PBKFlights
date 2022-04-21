package com.pbk.flights.Services;

import com.pbk.flights.Entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserServices {
    boolean login(User user, HttpServletRequest request, HttpServletResponse response);
    boolean logout(HttpServletRequest request);
    List<User> getUsers();
    boolean addUser(User user, HttpServletRequest request, HttpServletResponse response);
    boolean updateUser(User user);
    boolean deleteUser(int ID);
    User getUser(int parseInt);
}
