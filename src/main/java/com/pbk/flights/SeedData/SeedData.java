package com.pbk.flights.SeedData;

import com.pbk.flights.Dao.*;
import com.pbk.flights.Entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Time;


@Component
public class SeedData implements CommandLineRunner {

    HubDao hubDao;
    FlightDao flightDao;
    AirplaneDao airplaneDao;
    OrderDao orderDao;
    TripDao tripDao;
    UserDao userDao;

    public SeedData(HubDao hubDao, FlightDao flightDao, AirplaneDao airplaneDao, OrderDao orderDao, TripDao tripDao, UserDao userDao) {
        this.hubDao = hubDao;
        this.flightDao = flightDao;
        this.airplaneDao = airplaneDao;
        this.orderDao = orderDao;
        this.tripDao = tripDao;
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("adding hubs");
        Hub hub = new Hub("Baltimore Airport", "Baltimore, MD",
                39.18, -76.67, "BWI");
        hubDao.save(hub);

        Hub hub2 = new Hub("Fresno Airport", "Fresno, CA",
                36.77, -119.72, "FAT");
        hubDao.save(hub2);

        Hub hub3 = new Hub("Detroit Airport", "Detroit, MI",
                42.22, -83.36, "DTW");
        hubDao.save(hub3);

        Hub hub4 = new Hub("Las Vegas Airport", "Las Vegas. NV",
                36.09, -115.14, "LAS");
        hubDao.save(hub4);


        Hub hub5 = new Hub("Boston Airport", "Boston, MA",
                42.36, -71.02, "BOS");
        hubDao.save(hub5);

        System.out.println("adding Airplanes");

        Airplane a1 = new Airplane("Cesna Citation Mustang");
        Airplane a2 = new Airplane("Cesna Citation XLS");
        Airplane a3 = new Airplane("Cesna Citation Mustang");
        Airplane a4 = new Airplane("Cesna Citation XLS");
        Airplane a5 = new Airplane("Cesna Citation Mustang");
        airplaneDao.save(a1);
        airplaneDao.save(a2);
        airplaneDao.save(a3);
        airplaneDao.save(a4);
        airplaneDao.save(a5);

        System.out.println("Adding Flights");
        Time depart1 = new Time(6, 0, 0);
        Time arrive1 = new Time(9, 0, 0);
        Time depart2 = new Time(11, 0, 0);
        Time arrive2 = new Time(14, 0, 0);

        Flight flight1 = Flight.createFlight(a1, hub, hub4, depart1, arrive1, BigDecimal.valueOf(325.35));
        flightDao.save(flight1);
        hubDao.save(flight1.getOrigin());
        hubDao.save(flight1.getDestination());


        Flight flight2 = Flight.createFlight(a1, hub4, hub, depart2, arrive2, BigDecimal.valueOf(325.35));
        flightDao.save(flight2);
        hubDao.save(flight2.getOrigin());
        hubDao.save(flight2.getDestination());


        Flight flight3 = Flight.createFlight(a2, hub, hub3, depart1, arrive1, BigDecimal.valueOf(325.35));
        flightDao.save(flight3);
        hubDao.save(flight3.getOrigin());
        hubDao.save(flight3.getDestination());


        Flight flight4 = Flight.createFlight(a1, hub3, hub, depart2, arrive2, BigDecimal.valueOf(325.35));
        flightDao.save(flight4);
        hubDao.save(flight4.getOrigin());
        hubDao.save(flight4.getDestination());


        Flight flight5 = Flight.createFlight(a3, hub4, hub5, depart1, arrive1, BigDecimal.valueOf(325.35));
        flightDao.save(flight5);
        hubDao.save(flight5.getOrigin());
        hubDao.save(flight5.getDestination());


        Flight flight6 = Flight.createFlight(a3, hub5, hub4, depart2, arrive2, BigDecimal.valueOf(325.35));
        flightDao.save(flight6);
        hubDao.save(flight6.getOrigin());
        hubDao.save(flight6.getDestination());


        Flight flight7 = Flight.createFlight(a4, hub5, hub2, depart1, arrive1, BigDecimal.valueOf(325.35));
        flightDao.save(flight7);
        hubDao.save(flight7.getOrigin());
        hubDao.save(flight7.getDestination());


        Flight flight8 = Flight.createFlight(a4, hub2, hub5, depart2, arrive2, BigDecimal.valueOf(325.35));
        flightDao.save(flight8);
        hubDao.save(flight8.getOrigin());
        hubDao.save(flight8.getDestination());


        Flight flight9 = Flight.createFlight(a5, hub2, hub3, depart1, arrive1, BigDecimal.valueOf(325.35));
        flightDao.save(flight9);
        hubDao.save(flight9.getOrigin());
        hubDao.save(flight9.getDestination());


        Flight flight10 = Flight.createFlight(a5, hub3, hub2, depart2, arrive2, BigDecimal.valueOf(325.35));
        flightDao.save(flight10);
        hubDao.save(flight10.getOrigin());
        hubDao.save(flight10.getDestination());

        System.out.println("Adding Users");

        User perry = new User("Perry", "Shelton",
                "perry.shelton@genspark.net", "password1");
        perry.setAuthority("admin");
        userDao.save(perry);

        User brian = new User("Brian", "Upsher",
                "brian.upsher@genspark.net", "password1");
        userDao.save(brian);

        User kiran = new User("Kiran", "Yeturu",
                "kiran.yeturu@genspark.net", "password1");
        kiran.setAuthority("admin");
        userDao.save(kiran);

        System.out.println("Creating Orders and trips");

        for (User user : userDao.findAll()) {
            Order order = new Order();
            order.setUser(user);
            Trip trip1 = new Trip();
            trip1.setOrigin(flight1.getOrigin());
            trip1.addFlight(flight1, "AW");
            trip1.setDestination(flight5.getDestination());
            trip1.addFlight(flight5,"JW");
            tripDao.save(trip1);
            order.getTrips().add(trip1);

            Trip trip2 = new Trip();
            trip2.setOrigin(flight4.getOrigin());
            trip2.addFlight(flight4,"BW");
            trip2.setDestination(flight2.getDestination());
            trip2.addFlight(flight2,"BI");
            tripDao.save(trip2);
            order.getTrips().add(trip2);

            orderDao.save(order);


        }


    }
}
