package com.pbk.flights.SeedData;

import com.pbk.flights.Dao.*;
import com.pbk.flights.Entities.Airplane;
import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Entities.Hub;
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
        Hub hub = new Hub();
        hub.setName("Baltimore Airport");
        hub.setLatitude(39.18);
        hub.setLongitude(-76.67);
        hub.setLocation("Baltimore, MD");

        hubDao.save(hub);

        Hub hub2 = new Hub();
        hub2.setName("Fresno Airport");
        hub2.setLatitude(36.77);
        hub2.setLongitude(-119.72);
        hub2.setLocation("Fresno, CA");
        hubDao.save(hub2);

        Hub hub3 = new Hub();
        hub3.setName("Detroit Airport");
        hub3.setLatitude(42.22);
        hub3.setLongitude(-83.36);
        hub3.setLocation("Detroit, MI");
        hubDao.save(hub3);

        Hub hub4 = new Hub();
        hub4.setName("Las Vegas Airport");
        hub4.setLatitude(36.09);
        hub4.setLongitude(-115.14);
        hub4.setLocation("Las Vegas. NV");
        hubDao.save(hub4);


        Hub hub5	 = new Hub();
        hub5.setName("Boston Airport");
        hub5.setLatitude(42.36);
        hub5.setLongitude(-71.02);
        hub5.setLocation("Boston, MA");
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
        Time depart1 = new Time(6,0,0);
        Time arrive1 = new Time(9,0,0);
        Time depart2 = new Time(11,0,0);
        Time arrive2 = new Time(14,0,0);

        Flight flight1=Flight.createFlight(a1,hub,hub4,depart1,arrive1,BigDecimal.valueOf(325.35));
        flightDao.save(flight1);
        hubDao.save(flight1.getOrigin());
        hubDao.save(flight1.getDestination());


        Flight flight2=Flight.createFlight(a1,hub,hub4,depart2,arrive2,BigDecimal.valueOf(325.35));
        flightDao.save(flight2);
        hubDao.save(flight2.getOrigin());
        hubDao.save(flight2.getDestination());


        Flight flight3=Flight.createFlight(a2,hub,hub3,depart1,arrive1,BigDecimal.valueOf(325.35));
        flightDao.save(flight3);
        hubDao.save(flight3.getOrigin());
        hubDao.save(flight3.getDestination());


        Flight flight4=Flight.createFlight(a1,hub3,hub,depart2,arrive2,BigDecimal.valueOf(325.35));
        flightDao.save(flight4);
        hubDao.save(flight4.getOrigin());
        hubDao.save(flight4.getDestination());


        Flight flight5=Flight.createFlight(a3,hub4,hub5,depart1,arrive1,BigDecimal.valueOf(325.35));
        flightDao.save(flight5);
        hubDao.save(flight5.getOrigin());
        hubDao.save(flight5.getDestination());


        Flight flight6=Flight.createFlight(a3,hub5,hub4,depart2,arrive2,BigDecimal.valueOf(325.35));
        flightDao.save(flight6);
        hubDao.save(flight6.getOrigin());
        hubDao.save(flight6.getDestination());


        Flight flight7=Flight.createFlight(a4,hub5,hub2,depart1,arrive1,BigDecimal.valueOf(325.35));
        flightDao.save(flight7);
        hubDao.save(flight7.getOrigin());
        hubDao.save(flight7.getDestination());


        Flight flight8=Flight.createFlight(a4,hub2,hub5,depart2,arrive2,BigDecimal.valueOf(325.35));
        flightDao.save(flight8);
        hubDao.save(flight8.getOrigin());
        hubDao.save(flight8.getDestination());



        Flight flight9=Flight.createFlight(a5,hub2,hub3,depart1,arrive1,BigDecimal.valueOf(325.35));
        flightDao.save(flight9);
        hubDao.save(flight9.getOrigin());
        hubDao.save(flight9.getDestination());


        Flight flight10=Flight.createFlight(a5,hub3,hub2,depart2,arrive2,BigDecimal.valueOf(325.35));
        flightDao.save(flight10);
        hubDao.save(flight10.getOrigin());
        hubDao.save(flight10.getDestination());





    }
}
