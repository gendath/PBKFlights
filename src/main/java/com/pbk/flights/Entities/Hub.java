package com.pbk.flights.Entities;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Hub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String location;
    @OneToMany
    private Set<Flight> outgoing;
    @OneToMany
    private Set<Flight> incoming;
}
