package Controller;

import com.pbk.flights.Entities.Flight;
import com.pbk.flights.Services.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MyController {

    @Autowired
    private FlightServices flightService;

    @GetMapping("/home")
    //@RequestMapping("home")
    //public String home(@RequestParam(value="name", defaultValue="World") String name,@RequestParam(value="msg", defaultValue="Good Morning") String msg)
    public String home()
    {
        //return "Hello"+" "+name+" "+msg;
        //return "<HTML><H1> Welcome to My First Web Application</H1></HTML>";
        return "<HTML><H1> Welcome to My Course Application</H1></HTML>";

    }

    @GetMapping("/Flights")
    public List<Flight> getCourses(){
        return this.flightService.getAllFlights();
    }

    @GetMapping("/Flights/{flightID}")
    public Flight getCourse(@PathVariable String courseID){
        return this.flightService.getFlightByID(Integer.parseInt(courseID));
    }

    @PostMapping("/Flights")
    public Flight addCourse(@RequestBody Flight flight){
        return this.flightService.addFlight(flight);
    }

    @PutMapping("/Flights")
    public boolean updateFlight(@RequestBody Flight flight){
        return this.flightService.updateFlight(flight);
    }

    @DeleteMapping("/Flights/{flightID}")
    public String removeFlight(@PathVariable String flightID){
        return String.valueOf(this.flightService.getFlightByID(Integer.parseInt(flightID)));
    }
}
