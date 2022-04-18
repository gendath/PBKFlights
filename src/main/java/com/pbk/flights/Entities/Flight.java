package com.pbk.flights.Entities;

import javax.persistence.*;
import java.sql.Time;
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;
    @OneToOne
    private Airplane plane;

    @OneToOne
    private Hub origin;

    @OneToOne
    private Hub destination;

    private Time departure;
    private Time arrival;

    public Airplane getPlane() {
        return plane;
    }
}
