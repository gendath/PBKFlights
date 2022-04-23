package com.pbk.flights.Services;

import com.pbk.flights.Entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserServices {
    boolean login(User user, HttpServletRequest request, HttpServletResponse response);
    boolean logout(HttpServletRequest request, HttpServletResponse response);
    List<User> getUsers();
    User getUser(int userID);
    boolean addUser(User user, HttpServletRequest request, HttpServletResponse response);
    boolean updateUser(User user);
    boolean deleteUser(int ID);
    boolean deleteUserByEmail(String userEmail);
    void getStatus(HttpServletRequest request, HttpServletResponse response);
}
