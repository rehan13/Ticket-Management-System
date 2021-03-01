package persistence;

import model.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTickets(String name, String category, Booking book) {
        assertEquals(name, book.getName());
    }
}
