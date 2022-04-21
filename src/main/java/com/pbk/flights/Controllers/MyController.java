package com.pbk.flights.Controllers;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.User;
import com.pbk.flights.Services.FlightServices;
import com.pbk.flights.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private FlightServices flightService;
    @Autowired
    private UserServices userServices;

    @GetMapping("/Flights")
    public List<Flight> getAllFlight(){
        return this.flightService.getAllFlights();
    }

    @GetMapping("/Flights/{flightID}")
    public Flight getFlight(@PathVariable String courseID){
        return this.flightService.getFlightByID(Integer.parseInt(courseID));
    }

    @PostMapping("/Flights")
    public Flight addFlight(@RequestBody Flight flight){
        return this.flightService.addFlight(flight);
    }

    @PutMapping("/Flights")
    public boolean updateFlight(@RequestBody Flight flight){
        return this.flightService.updateFlight(flight);
    }

    @DeleteMapping("/Flights/{flightID}")
    public String removeFlight(@PathVariable String flightID){
        return String.valueOf(this.flightService.getFlightByID(Integer.parseInt(flightID)));
    }


    //Users

    @GetMapping("/Users")
    public List<User> getUsers(HttpServletRequest request){
        if (request.getSession().getAttribute("authority").equals("admin"))
            return this.userServices.getUsers();
        return new ArrayList<>();
    }

    @PostMapping("/signup")
    public boolean addUser(@RequestBody User user, HttpServletRequest req){
        return this.userServices.addUser(user, req);
    }

    @PutMapping("/Users")
    public boolean updateUser(@RequestBody User user, HttpServletRequest request){
        if (request.getSession().getAttribute("authority").equals("admin") ||
                request.getSession().getAttribute("userid").equals(String.valueOf(user.getUserId())))
            return this.userServices.updateUser(user);
        return false;
    }

    @DeleteMapping("/Users/{userID}")
    public String removeUser(@PathVariable String userID, HttpServletRequest request){
        if (request.getSession().getAttribute("authority").equals("admin"))
            return String.valueOf(this.userServices.deleteUser(Integer.parseInt(userID)));
        return "Unauthorized to delete";
    }

    @GetMapping("/Users/{userID}")
    public User getUser(@PathVariable String userID, HttpServletRequest request){
        if (request.getSession().getAttribute("authority").equals("admin"))
            return this.userServices.getUser(Integer.parseInt(userID));
        return null;
    }

    @GetMapping("/login/{username}/{password}")
    public String signin(@PathVariable String username, @PathVariable String password, HttpServletRequest req) {
        User u = new User();
        u.setEmail(username);
        u.setPassword(password);
        if (this.userServices.login(u, req))
            return "Login successful";
        else return "Login failed";
    }

    @GetMapping("/signout")
    public void signout(HttpServletRequest req) {
        this.userServices.logout(req);
        // TODO: redirect to home
    }

}
