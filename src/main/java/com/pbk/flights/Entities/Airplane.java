package com.pbk.flights.Entities;

import java.util.Map;

import static java.util.Map.entry;

public class Airplane {
    private int id;
    private String model;
    private final Map<String, User> seats;
    private String price;

    public Airplane() {
        seats = Map.ofEntries(
                entry("AW", null), entry("AI", null), entry("BW", null), entry("BI", null),
                entry("CW", null), entry("CI", null), entry("DW", null), entry("DI", null),
                entry("EW", null), entry("EI", null), entry("FW", null), entry("FI", null),
                entry("GW", null), entry("GI", null), entry("HW", null), entry("HI", null),
                entry("IW", null), entry("II", null), entry("JW", null), entry("JI", null)
        );
    }

    public Map<String, User> getSeats() {
        return seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
