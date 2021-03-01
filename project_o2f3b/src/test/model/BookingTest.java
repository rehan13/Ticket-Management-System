package model;

import model.exceptions.FlightNumberNotFoundException;
import model.exceptions.SeatNumberNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    private Booking testBooking;
    private Booking booking;
    Date date;
    @BeforeEach
    void runBefore() {
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DATE,3);
        c1.set(Calendar.MONTH,10);
        c1.set(Calendar.YEAR,2020);
        c1.set(Calendar.HOUR_OF_DAY,14);
        c1.set(Calendar.MINUTE,00);
        date = c1.getTime();
        testBooking = new Booking();

        testBooking = new Booking("Ross","32244223",
                "Thu Nov 09 17:20:02 BDT 1995", "Canada","879898",
                "9834443","Male","ross@gmail.com","economic",
                "3",true,"AN43215","Calgary",
                "Montreal","Domestic","4 hrs",date,"AN43215-97275",200);



        booking = new Booking("Rehan", "Calgary", "KS1234", "AN43215-97275",
                "1", "01543", "male", "xyz@gmail.com",
                "Thu Nov 09 17:20:02 BDT 1995", "4234143");

        testBooking.setName("Ross");
        testBooking.setPassportNo("32244223");
        testBooking.setDateOfBirth("Thu Nov 09 17:20:02 BDT 1995");
        testBooking.setAddress("Canada");
        testBooking.setContactNo("879898");
        testBooking.setEmergencyContact("9834443");
        testBooking.setGender("Male");
        testBooking.setEmail("ross@gmail.com");
        testBooking.setSeatNo("3");
        testBooking.setSeatType("economic");
        testBooking.setWindowSeat(false);
        testBooking.setFlightNo("AN43215");
        testBooking.setDeparture("Calgary");
        testBooking.setArrival("Montreal");
        testBooking.setFlightType("Domestic");
        testBooking.setDuration("4 hrs");
        testBooking.setDate(date);
        try {
            testBooking.setBookingNo(testBooking.generateBookingNo());
        } catch (FlightNumberNotFoundException e) {
            e.printStackTrace();
        } catch (SeatNumberNotFoundException e) {
            e.printStackTrace();
        }
        testBooking.setPrice(200);
    }
    @Test
    void testConstructor(){

        assertEquals("Ross", testBooking.getName());
        assertEquals("32244223",testBooking.getPassportNo());
        assertEquals("Thu Nov 09 17:20:02 BDT 1995",testBooking.getDateOfBirth());
        assertEquals("Canada",testBooking.getAddress());
        assertEquals("879898",testBooking.getContactNo());
        assertEquals("9834443",testBooking.getEmergencyContact());
        assertEquals("Male",testBooking.getGender());
        assertEquals("ross@gmail.com",testBooking.getEmail());
        assertEquals("3",testBooking.getSeatNo());
        assertEquals("economic",testBooking.getSeatType());
        assertEquals(false,testBooking.isWindowSeat());
        assertEquals("AN43215",testBooking.getFlightNo());
        assertEquals("Calgary",testBooking.getDeparture());
        assertEquals("Montreal",testBooking.getArrival());
        assertEquals("Domestic",testBooking.getFlightType());
        assertEquals("4 hrs",testBooking.getDuration());
        assertEquals(date,testBooking.getDate());
        assertEquals("AN43215-54333", testBooking.getBookingNo());
        assertEquals(200,testBooking.getPrice());
    }

    //AN43215
    @Test
    void testGenerateBookingNo() {
        try {
            assertEquals("AN43215-54333", Booking.generateBookingNo());
        } catch (FlightNumberNotFoundException | SeatNumberNotFoundException e) {
            e.printStackTrace();
        }

        testBooking.setFlightNo("");
        try {
            assertEquals("5433-54333", Booking.generateBookingNo());
        } catch (FlightNumberNotFoundException | SeatNumberNotFoundException e) {
            e.printStackTrace();
        }
        testBooking.setFlightNo("AN43215");
        testBooking.setSeatNo("");
        try {
            assertEquals("AN43215-5433", Booking.generateBookingNo());
        } catch (FlightNumberNotFoundException | SeatNumberNotFoundException e) {
            e.printStackTrace();
        }

    }

}