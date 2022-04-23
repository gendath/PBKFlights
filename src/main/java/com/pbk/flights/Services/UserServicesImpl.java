package com.pbk.flights.Services;

import com.pbk.flights.Dao.UserDao;
import com.pbk.flights.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserDao userDao;

    @Override
    public boolean addUser(User user, HttpServletRequest request, HttpServletResponse response) {     // This is Sign Up function
        if (userDao.findAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()))) {
            System.out.println("A user with this email already exists");
            return false;
        }
        login(userDao.save(user), request, response);
        return true;
    }

    @Override
    public boolean login(User user, HttpServletRequest request, HttpServletResponse response) {
        User current = userDao.findAll().stream()
                .filter(u-> u.getEmail().equalsIgnoreCase(user.getEmail()))
                .filter(u-> u.getPassword().equals(user.getPassword()))
                .findFirst().orElse(null);
        if (current == null) {
            System.out.println("Invalid username or password");

            logout(request);
            return false;
        }

        request.getSession().setAttribute("userid", current.getUserId());
        request.getSession().setAttribute("username", current.getEmail());
        request.getSession().setAttribute("firstName", current.getFirstName());
        request.getSession().setAttribute("lastName", current.getLastName());
        request.getSession().setAttribute("authority", current.getAuthority());
        try {
            request.authenticate(response);
        } catch (Exception e) {
            System.out.println("Could not authenticate user");
        }
        return true;
    }

    // TODO: add OAuth login method

    @Override
    public boolean logout(HttpServletRequest request) {
        try {
            request.getSession().setAttribute("userid", "");
            request.getSession().setAttribute("username", "");
            request.getSession().setAttribute("firstName", "");
            request.getSession().setAttribute("lastName", "");
            request.getSession().setAttribute("authority", "");
            request.logout();
            request.getSession().invalidate();
            return true;
        } catch (ServletException e) {
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
}
