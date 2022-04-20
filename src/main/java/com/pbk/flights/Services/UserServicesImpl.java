package com.pbk.flights.Services;

import com.pbk.flights.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(int userid) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int ID) {
        return false;
    }
}
