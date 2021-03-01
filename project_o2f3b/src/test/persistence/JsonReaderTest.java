package persistence;

import model.Booking;
import org.junit.jupiter.api.Test;
import ui.TicketSystem;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    public TicketSystem ts = new TicketSystem("Ticket Management System");


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ts = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySystem() {
        try {
            JsonReader reader = new JsonReader("./data/testReadEmptySystem.json");
            TicketSystem ts = new TicketSystem("Ticket Management System");
            ts = reader.read();
            assertEquals("Ticket Management System", ts.getName());
            assertEquals(0, ts.getBookingList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadSystem() {
        JsonReader reader = new JsonReader("./data/testReadEmptySystem.json");
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DATE,3);
        c1.set(Calendar.MONTH,10);
        c1.set(Calendar.YEAR,2020);
        c1.set(Calendar.HOUR_OF_DAY,14);
        c1.set(Calendar.MINUTE,00);
        Date date = c1.getTime();
        Booking book = new Booking("Ross","32244223",
                "Thu Nov 09 17:20:02 BDT 1995", "Canada","879898",
                "9834443","Male","ross@gmail.com","economic",
                "3",true,"AN43215","Calgary",
                "Montreal","Domestic","4 hrs",date,"AN43215-97275",350);

        TicketSystem ts = null;
        try {
            ts = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("Ticket Management System", ts.getName());
        List<Booking> bookings = ts.getBookingList();
        bookings.add(book);
        assertEquals(1, bookings.size());
        checkTickets("Ross", book.getName(), bookings.get(0));
    }
}