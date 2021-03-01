package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatTest {
    private Seat testSeat;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Ross","BR9887-JK78889","9-07-1999","canada",
                "987678767","8778665","Male","ross@gmail.com");
        testSeat = new Seat();
        testSeat.setSeatNo("1");
        testSeat.setBookingStatus(false);
        testSeat.setSeatType("Economic");
        testSeat.setWindow(true);
        testSeat.setBookedBy(user);
        testSeat.setPrice(200);

    }
    @Test
    void testConstructor(){
        assertEquals("1", testSeat.getSeatNo());
        assertEquals(false,testSeat.bookingStatus());
        assertEquals("Economic",testSeat.getSeatType());
        assertEquals(true,testSeat.isWindow());
        assertEquals(user, testSeat.getBookedBy());
        assertEquals(200,testSeat.getPrice());

    }
}