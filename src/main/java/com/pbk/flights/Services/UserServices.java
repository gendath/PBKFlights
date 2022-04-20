package com.pbk.flights.Services;

import com.pbk.flights.Entities.User;

import java.util.List;

public interface UserServices {
    boolean login(String username, String password);
    boolean logout();
    List<User> getUsers();
    User getUser();
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int ID);
}
