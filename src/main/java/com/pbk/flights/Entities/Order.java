package com.pbk.flights.Entities;

import javax.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;
    @OneToOne
    private User purchaser;
    @OneToOne
    private Flight flight;
}
