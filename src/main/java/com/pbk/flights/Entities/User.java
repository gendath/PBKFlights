package com.pbk.flights.Entities;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private final Set<Order> bookings= new HashSet<>();

}
