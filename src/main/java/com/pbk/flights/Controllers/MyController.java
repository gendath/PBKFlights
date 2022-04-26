package com.pbk.flights.Controllers;

import com.pbk.flights.Entities.*;
import com.pbk.flights.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class MyController {

    @SuppressWarnings("unused")
    @Autowired
    private FlightServices flightService;
    @SuppressWarnings("unused")
    @Autowired
    private UserServices userServices;
    @SuppressWarnings("unused")
    @Autowired
    private HubServices hubServices;
    @SuppressWarnings("unused")
    @Autowired
    private OrderServices orderServices;
    @SuppressWarnings("unused")
    @Autowired
    private TripServices tripServices;
    @SuppressWarnings("unused")
    @Autowired
    private AirplaneServices airplaneServices;

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
        if (isUserAdmin(request))
            return this.userServices.getUsers();
        return new ArrayList<>();
    }

    @GetMapping("/users/{userID}")
    public User getUser(@PathVariable String userID, HttpServletRequest request){
        if (isUserAdmin(request))
            return this.userServices.getUser(Integer.parseInt(userID));
        return null;
    }

    @PostMapping("/signup")
    public void addUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        this.userServices.addUser(user, request, response);
    }

    @PutMapping("/users")
    public boolean updateUser(@RequestBody User user, HttpServletRequest request){
        if (isUserAdmin(request) ||
                request.getSession().getAttribute("userid").equals(String.valueOf(user.getUserId())))
            return this.userServices.updateUser(user);
        return false;
    }

    @DeleteMapping("/users/{userID}")
    public String removeUser(@PathVariable String userID, HttpServletRequest request){
        if (isUserAdmin(request))
            return String.valueOf(this.userServices.deleteUser(Integer.parseInt(userID)));
        return "Unauthorized to delete";
    }

    @GetMapping("/login/{username}/{password}")
    public User signin(@PathVariable String username, @PathVariable String password, HttpServletRequest request, HttpServletResponse response) {
        User u = new User();
        u.setEmail(username);
        u.setPassword(password);
        return this.userServices.login(u, request, response);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        this.userServices.logout(request);
    }

    // Convenience functions for testing, will be removed

    @DeleteMapping("/test/delete/{userEmail}")
    public String removeUserByEmail(@PathVariable String userEmail, HttpServletRequest request) {
        if ("admin".equals(request.getSession().getAttribute("authority")))
            return String.valueOf(this.userServices.deleteUserByEmail(userEmail));
        return "Unauthorized to delete";
    }

    @GetMapping("/test/status")
    public void getUserStatus(HttpServletRequest request, HttpServletResponse response) {
        userServices.getStatus(request, response);
    }

    @GetMapping("/test/routing/{departure}/{arrival}")
    public Trip getTripRoutess(@PathVariable String departure, @PathVariable String arrival) {
        return flightService.getRoutes(departure, arrival).get(0);
    }

    // Hubs

    @GetMapping("/hubs")
    public List<Hub> getAllHubs(HttpServletRequest request) {
        return hubServices.getAllHubs();
    }
    @GetMapping("/hubs/{hubID}")
    public Hub getHub(@PathVariable String hubID, HttpServletRequest request) {
//        if (isUserAdmin(request))
            return hubServices.getHubByID(Integer.parseInt(hubID));
//        return null;
    }
    @PostMapping("/hubs")
    public Hub addHub(@RequestBody Hub hub, HttpServletRequest request) {
        if (isUserAdmin(request))
            return hubServices.addHub(hub);
        return null;
    }
    @PutMapping("/hubs")
    public boolean updateHub(@RequestBody Hub hub, HttpServletRequest request) {
        if (isUserAdmin(request))
            return hubServices.updateHub(hub);
        return false;
    }
    @DeleteMapping("/hubs/{hubID}")
    public boolean deleteHub(@PathVariable String hubID, HttpServletRequest request) {
        if (isUserAdmin(request))
            return hubServices.removeHub(Integer.parseInt(hubID));
        return false;
    }
    @GetMapping("/hubs/out/{hubID}")
    public Set<Flight> getHubOutboundFlights(@PathVariable String hubID, HttpServletRequest request) {
//        if (isUserAdmin(request))
            return hubServices.getOutgoingFlights(Integer.parseInt(hubID));
//        return new HashSet<>();
    }
    @GetMapping("/hubs/in/{hubID}")
    public Set<Flight> getHubInboundFlights(@PathVariable String hubID, HttpServletRequest request) {
//        if (isUserAdmin(request))
            return hubServices.getIncomingFlights(Integer.parseInt(hubID));
//        return new HashSet<>();
    }


    // orders
    @GetMapping("/orders")
    public List<Order> getAllOrders(HttpServletRequest request) {
        if (!isLoggedIn(request)) return new ArrayList<>();
        if (isUserAdmin(request)) return orderServices.getAllOrders();
        return orderServices.getAllOrdersFromUser(Integer.parseInt(
                    request.getSession().getAttribute("userid").toString()));
    }
    @GetMapping("/orders/{orderID}")
    public Order getOrder(@PathVariable String orderID, HttpServletRequest request) {
        if (isLoggedIn(request))
            return orderServices.getOrderByID(Integer.parseInt(orderID));
        return null;
    }
    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order, HttpServletRequest request) {
        if (isUserAdmin(request))
            return orderServices.addOrder(order);
        return null;
    }
    @PutMapping("/orders")
    public boolean updateOrder(@RequestBody Order order, HttpServletRequest request) {
        if (isUserAdmin(request))
            return orderServices.updateOrder(order);
        return false;
    }
    @DeleteMapping("/orders/{orderID}")
    public boolean deleteOrder(@PathVariable String orderID, HttpServletRequest request) {
        if (isUserAdmin(request))
            return orderServices.removeOrder(Integer.parseInt(orderID));
        return false;
    }


    // Trip
    @GetMapping("/trips")
    public List<Trip> getAllTrips(HttpServletRequest request) {
//        if (isUserAdmin(request))
            return tripServices.getAllTrips();
//        return new ArrayList<>();
    }
    @GetMapping("/trips/{departure}/{arrival}")
    public List<Trip> getTripRoutes(@PathVariable String departure, @PathVariable String arrival) {
        return flightService.getRoutes(departure, arrival);
    }
    @GetMapping("/trips/{orderID}")
    public Trip getTrip(@PathVariable String tripID, HttpServletRequest request) {
        if (isUserAdmin(request))
            return tripServices.getTripByID(Integer.parseInt(tripID));
        return null;
    }
    @PostMapping("/trips")
    public Trip addTrip(@RequestBody Trip trip, HttpServletRequest request) {
        if (isUserAdmin(request))
            return tripServices.addTrip(trip);
        return null;
    }
    @PutMapping("/trips")
    public boolean updateTrip(@RequestBody Trip trip, HttpServletRequest request) {
        if (isUserAdmin(request))
            return tripServices.updateTrip(trip);
        return false;
    }
    @DeleteMapping("/trips/{orderID}")
    public boolean deleteTrip(@PathVariable String tripID, HttpServletRequest request) {
        if (isUserAdmin(request))
            return tripServices.removeTrip(Integer.parseInt(tripID));
        return false;
    }


    // Airplane
    @GetMapping("/airplanes")
    public List<Airplane> getAllPlanes(HttpServletRequest request) {
        if (isUserAdmin(request))
            return airplaneServices.getAllAirplanes();
        return new ArrayList<>();
    }
    @GetMapping("/airplanes/{planeID}")
    public Airplane getPlane(@PathVariable String planeID, HttpServletRequest request) {
        if (isUserAdmin(request))
            return airplaneServices.getAirplaneByID(Integer.parseInt(planeID));
        return null;
    }
    @PostMapping("/airplanes")
    public Airplane addPlane(@RequestBody Airplane plane, HttpServletRequest request) {
        if (isUserAdmin(request))
            return airplaneServices.addAirplane(plane);
        return null;
    }
    @PutMapping("/airplanes")
    public boolean updatePlane(@RequestBody Airplane plane, HttpServletRequest request) {
        if (isUserAdmin(request))
            return airplaneServices.updateAirplane(plane);
        return false;
    }
    @DeleteMapping("/airplanes/{planeID}")
    public boolean deletePlane(@PathVariable String planeID, HttpServletRequest request) {
        if (isUserAdmin(request))
            return airplaneServices.removeAirplane(Integer.parseInt(planeID));
        return false;
    }

    private boolean isUserAdmin(HttpServletRequest request) {
        return request.getSession().getAttribute("authority").equals("admin");
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        var authority = request.getSession().getAttribute("authority");
        return "admin".equals(authority) || "user".equals(authority);
    }

    private boolean isActiveUser(int userID, HttpServletRequest request) {
        return userID == Integer.parseInt(request.getSession().getAttribute("userid").toString());
    }
}
