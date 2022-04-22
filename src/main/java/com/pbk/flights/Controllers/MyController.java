package com.pbk.flights.Controllers;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.User;
import com.pbk.flights.Services.FlightServices;
import com.pbk.flights.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private FlightServices flightService;
    @Autowired
    private UserServices userServices;

    @GetMapping("/flights")
    public List<Flight> getAllFlight(){
        System.out.println("Serving Flights");
        return this.flightService.getAllFlights();
    }


    @GetMapping("/flights/{flightID}")
    public Flight getFlight(@PathVariable String flightID){
        return this.flightService.getFlightByID(Integer.parseInt(flightID));
    }

    @PostMapping("/flights")
    public Flight addFlight(@RequestBody Flight flight){
        return this.flightService.addFlight(flight);
    }

    @PutMapping("/flights")
    public boolean updateFlight(@RequestBody Flight flight){
        return this.flightService.updateFlight(flight);
    }

    @DeleteMapping("/flights/{flightID}")
    public String removeFlight(@PathVariable String flightID){
        return String.valueOf(this.flightService.getFlightByID(Integer.parseInt(flightID)));
    }


    //Users

    @GetMapping("/users")
    public List<User> getUsers(HttpServletRequest request){
        if (request.getSession().getAttribute("authority").equals("admin"))
            return this.userServices.getUsers();
        return new ArrayList<>();
    }

    @GetMapping("/users/{userID}")
    public User getUser(@PathVariable String userID, HttpServletRequest request){
        if (request.getSession().getAttribute("authority").equals("admin"))
            return this.userServices.getUser(Integer.parseInt(userID));
        return null;
    }

    @PostMapping("/signup")
    public boolean addUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        return this.userServices.addUser(user, request, response);
    }

    @PutMapping("/users")
    public boolean updateUser(@RequestBody User user, HttpServletRequest request){
        if (request.getSession().getAttribute("authority").equals("admin") ||
                request.getSession().getAttribute("userid").equals(String.valueOf(user.getUserId())))
            return this.userServices.updateUser(user);
        return false;
    }

    @DeleteMapping("/users/{userID}")
    public String removeUser(@PathVariable String userID, HttpServletRequest request){
        if (request.getSession().getAttribute("authority").equals("admin"))
            return String.valueOf(this.userServices.deleteUser(Integer.parseInt(userID)));
        return "Unauthorized to delete";
    }

    @GetMapping("/login/{username}/{password}")
    public String signin(@PathVariable String username, @PathVariable String password, HttpServletRequest request, HttpServletResponse response) {
        User u = new User();
        u.setEmail(username);
        u.setPassword(password);
        if (this.userServices.login(u, request, response))
            return "Login successful";
        else return "Login failed";
    }

    @RequestMapping("/signout")
    public String signout(HttpServletRequest request) {
        this.userServices.logout(request);
        return "index";
    }

}
