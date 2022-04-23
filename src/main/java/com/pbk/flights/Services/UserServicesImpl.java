package com.pbk.flights.Services;

import com.pbk.flights.Dao.UserDao;
import com.pbk.flights.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserDao userDao;

    @Override
    public boolean addUser(User user, HttpServletRequest request, HttpServletResponse response) {     // This is Sign Up function
        PrintWriter writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            writer = new PrintWriter(System.out);
        }
        user.setEmail(user.getEmail().toLowerCase());       // To avoid case sensitivity always ensure email is lowercase
        if (!userDao.findByEmailIgnoreCase(user.getEmail()).isEmpty()) {
            writer.println("A user with this email already exists");
            return false;
        }
        // TODO: Make a post request to do this instead
        login(userDao.save(user), request, response);
        writer.printf("User %s %s created successfully%n", user.getFirstName(), user.getLastName());
        return true;
    }

    @Override
    public boolean login(User user, HttpServletRequest request, HttpServletResponse response) {
        var users = userDao.findByEmailIgnoreCaseAndPassword(user.getEmail(), user.getPassword());
        PrintWriter writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            writer = new PrintWriter(System.out);
        }
        if (users.isEmpty()) {
            writer.println("Invalid username or password");

            logout(request, response);
            return false;
        }
        var current = users.get(0);

        request.getSession().setAttribute("userid", current.getUserId());
        request.getSession().setAttribute("username", current.getEmail());
        request.getSession().setAttribute("firstName", current.getFirstName());
        request.getSession().setAttribute("lastName", current.getLastName());
        request.getSession().setAttribute("authority", current.getAuthority());
        try {
            request.authenticate(response);
            writer.printf("%s successfully logged in as %n", current.getFirstName());
        } catch (Exception e) {
            writer.println("Could not authenticate user");
        }
        return true;
    }

    // TODO: add OAuth login method

    @Override
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("userid", "");
            request.getSession().setAttribute("username", "");
            request.getSession().setAttribute("firstName", "");
            request.getSession().setAttribute("lastName", "");
            request.getSession().setAttribute("authority", "");
            request.logout();
            request.getSession().invalidate();
            PrintWriter writer = response.getWriter();
            writer.println("Successfully logged out");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(int userID) {
        var u = userDao.findById(userID).orElse(null);
        if (u == null) throw new RuntimeException("User ID was not valid");
        return u;
    }

    @Override
    public boolean updateUser(User user) {
        userDao.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(int ID) {
        userDao.deleteById(ID);
        return true;
    }

    @Override
    public boolean deleteUserByEmail(String userEmail) {
        var users = userDao.findByEmailIgnoreCase(userEmail.toLowerCase());
        if (users.isEmpty()) return false;
        userDao.delete(users.get(0));
        return true;
    }

    // :::TESTING:::  Delete after
    @Override
    public void getStatus(HttpServletRequest request, HttpServletResponse response){
        try {
            PrintWriter writer = response.getWriter();
            writer.println(request.getSession().getAttribute("userid"));
            writer.println(request.getSession().getAttribute("username"));
            writer.println(request.getSession().getAttribute("firstName"));
            writer.println(request.getSession().getAttribute("lastName"));
            writer.println(request.getSession().getAttribute("authority"));
        } catch (Exception e) {
        }
    }
}
