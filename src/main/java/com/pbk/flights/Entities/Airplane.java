package com.pbk.flights.Entities;


import javax.persistence.*;
import java.util.Map;

import static java.util.Map.entry;
@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String model;


    @ElementCollection
    @CollectionTable(name = "airplane_seat_mapping",
            joinColumns = {@JoinColumn(name = "airplane_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "seat")
    @Column(name = "seats")
    private final Map<String, Integer> seats;

    public Airplane() {
        seats = Map.ofEntries(
                entry("AW", -1), entry("AI", -1),                  entry("BW", -1), entry("BI", -1),
                entry("CW", -1), entry("CI", -1),                  entry("DW", -1), entry("DI", -1),
                entry("EW", -1), entry("EI", -1),                  entry("FW", -1), entry("FI", -1),
                entry("GW", -1), entry("GI", -1),                  entry("HW", -1), entry("HI", -1),
                entry("IW", -1), entry("II", -1),                  entry("JW", -1), entry("JI", -1)
        );
    }

    public Map<String, Integer> getSeats() {
        return seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airplane airplane = (Airplane) o;

        return id == airplane.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
