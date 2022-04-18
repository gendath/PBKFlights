package com.pbk.flights.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToMany
    private final Set<Order> bookings= new HashSet<>();

}
