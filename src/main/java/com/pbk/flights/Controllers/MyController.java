package com.pbk.flights.Controllers;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.User;
import com.pbk.flights.Services.FlightServices;
import com.pbk.flights.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private FlightServices flightService;
    @Autowired
    private UserServices userServices;

    @GetMapping("/flights")
    public List<Flight> getAllFlight(){
        return this.flightService.getAllFlights();
    }

    @GetMapping("/Flights/{flightID}")
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
    public List<User> getUsers(){
        return this.userServices.getUsers();
    }

// Need to send an integer as ID
    @GetMapping("/users/{userID}")
    public User getUser(@PathVariable String userID){
        return this.userServices.getUser(Integer.parseInt(userID));
    }


    @PostMapping("/users")
    public boolean addUser(@RequestBody User user){
        return this.userServices.addUser(user);
    }

    @PutMapping("/users")
    public boolean updateUser(@RequestBody User user){
        return this.userServices.updateUser(user);
    }

    @DeleteMapping("/users/{userID}")
    public String removeUser(@PathVariable String userID){
        //return String.valueOf(this.userServices(Integer.parseInt(userID)));
        return String.valueOf(this.userServices.getUser(Integer.parseInt(userID)));
    }




}
