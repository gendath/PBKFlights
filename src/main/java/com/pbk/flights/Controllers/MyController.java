package com.pbk.flights.Controllers;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.User;
import com.pbk.flights.Services.FlightServices;
import com.pbk.flights.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<User> getUsers(){
        return this.userServices.getUsers();
    }

// Need to send an integer as ID
    @GetMapping("/Users/{userID}")
    public User getUser(@PathVariable String userID){
        return this.userServices.getUser(Integer.parseInt(userID));
    }


    @PostMapping("/Users")
    public boolean addUser(@RequestBody User user){
        return this.userServices.addUser(user);
    }

    @PutMapping("/Users")
    public boolean updateUser(@RequestBody User user){
        return this.userServices.updateUser(user);
    }

    @DeleteMapping("/Users/{userID}")
    public String removeUser(@PathVariable String userID){
        //return String.valueOf(this.userServices(Integer.parseInt(userID)));
        return String.valueOf(this.userServices.getUser(Integer.parseInt(userID)));
    }




}
