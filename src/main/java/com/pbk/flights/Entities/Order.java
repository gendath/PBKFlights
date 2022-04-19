package com.pbk.flights.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;
    @OneToOne
    private User purchaser;

    @OneToMany
    @JoinColumn(name = "order_id")
    private Set<Trip> trips = new HashSet<>();


    private BigDecimal totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return orderID == order.orderID;
    }

    @Override
    public int hashCode() {
        return orderID;
    }
// TODO: 4/19/2022 QR code
}
