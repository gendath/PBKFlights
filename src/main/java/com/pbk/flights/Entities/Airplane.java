package com.pbk.flights.Entities;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;
@Entity
@Component
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

    public Airplane(String model) {
        seats = Map.ofEntries(
                entry("AW", -1), entry("AI", -1),                  entry("BW", -1), entry("BI", -1),
                entry("CW", -1), entry("CI", -1),                  entry("DW", -1), entry("DI", -1),
                entry("EW", -1), entry("EI", -1),                  entry("FW", -1), entry("FI", -1),
                entry("GW", -1), entry("GI", -1),                  entry("HW", -1), entry("HI", -1),
                entry("IW", -1), entry("II", -1),                  entry("JW", -1), entry("JI", -1)
        );
        this.model = model;
    }

    public Airplane(int rows, int seatsPerRow) {
        seats = generateSeats(rows, seatsPerRow);
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

    public Map<String, Integer> generateSeats(int rows, int seatsPerRow) {
        Map<String, Integer> seatMap = new HashMap<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                String s = "" + i + ((char) ("A".getBytes()[0]+j));
                seatMap.put(s, -1);
            }
        }

        return seatMap;
    }

    public List<String> getAvailableSeats() {
        return seats.entrySet().parallelStream()
                .filter(e -> e.getValue() == -1)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }

    public boolean updateSeat(String seat, int userID) {
        if (!getAvailableSeats().contains(seat)) return false;
        seats.put(seat, userID);
        return true;
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
