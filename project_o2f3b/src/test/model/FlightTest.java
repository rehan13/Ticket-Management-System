package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private Flight testFlight;
    Date[] date = new Date[1];
    Seat[] f1seats = new Seat[1];
    User user;

    @BeforeEach
    void setUp() {
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DATE,3);
        c1.set(Calendar.MONTH,10);
        c1.set(Calendar.YEAR,2020);
        c1.set(Calendar.HOUR_OF_DAY,14);
        c1.set(Calendar.MINUTE,00);
        date[0] = c1.getTime();
        user = new User("Ross","BR9887-JK78889","9-07-1999","canada",
                "987678767","8778665","Male","ross@gmail.com");

        f1seats[0] = new Seat("1",false,"Economic",true,user,200);

        testFlight = new Flight();

        testFlight = new Flight("Air North","AN43215","Calgary","Montreal",
                "Domestic", "4 hrs",date,3,2,f1seats);

        testFlight.setAirlineName("Air North");
        testFlight.setFlightNo("AN43215");
        testFlight.setDeparture("Calgary");
        testFlight.setArrival("Montreal");
        testFlight.setFlightType("Domestic");
        testFlight.setDuration("4 hrs");
        testFlight.setTimeDate(date);
        testFlight.setBusinessSeatNo(3);
        testFlight.setEconomySeatNo(2);
        testFlight.setSeatInfo(f1seats);

    }
    @Test
    void testConstructor(){
        assertEquals("Air North", testFlight.getAirlineName());
        assertEquals("AN43215",testFlight.getFlightNo());
        assertEquals("Calgary",testFlight.getDeparture());
        assertEquals("Montreal",testFlight.getArrival());
        assertEquals("Domestic",testFlight.getFlightType());
        assertEquals("4 hrs",testFlight.getDuration());
        assertEquals(date,testFlight.getTimeDate());
        assertEquals(3, testFlight.getBusinessSeatNo());
        assertEquals(2, testFlight.getEconomySeatNo());
        assertEquals(f1seats,testFlight.getSeatInfo());
    }
}