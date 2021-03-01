package persistence;

import model.Booking;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import ui.TicketSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest  {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
    JSONArray ja = new JSONArray();
    JSONArray jsonArray = new JSONArray();

    @Test
    void testWriterInvalidFile() {
        try {
            TicketSystem ts = new TicketSystem("Ticket Management System");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }
    @Test
    void testTicketSystem() {
        try {
            Booking book = new Booking();
            Calendar c1 = Calendar.getInstance();
            c1.set(Calendar.DATE,3);
            c1.set(Calendar.MONTH,10);
            c1.set(Calendar.YEAR,2020);
            c1.set(Calendar.HOUR_OF_DAY,14);
            c1.set(Calendar.MINUTE,00);
            Date date = c1.getTime();
            book = new Booking("Ross","32244223",
                    "Thu Nov 09 17:20:02 BDT 1995", "Canada","879898",
                    "9834443","Male","ross@gmail.com","economic",
                    "3",true,"AN43215","Calgary",
                    "Montreal","Domestic","4 hrs",date,"AN43215-97275",200);

            ArrayList<Booking> booking = new ArrayList<>();
            booking.add(book);
            TicketSystem ticketSystem = new TicketSystem("Ticket Management System",booking);
            JsonWriter writer = new JsonWriter("./data/testTicketSystem.json");
            writer.open();
            writer.write(ticketSystem);
            writer.close();

            JsonReader reader = new JsonReader("./data/testTicketSystem.json");
            ticketSystem = reader.read();
            assertEquals("Ticket Management System", ticketSystem.getName());
            ticketSystem.getBookingList();
            assertEquals(1,ticketSystem.getBookingList().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testEmptyTicketSystem() {
        try {
            Booking booking =  new Booking();
            ArrayList<Booking> book= new ArrayList<Booking>();
            TicketSystem ts = new TicketSystem("Ticket Management System",book);
            JsonWriter write = new JsonWriter("./data/testWriteEmptySystem.json");
            write.open();
            write.write(ts);
            write.close();

            JsonReader read = new JsonReader("./data/testEmptySystem.json");
            //jsonArray = read.read();
            assertEquals("Ticket Management System", ts.getName());
            assertEquals(0,ts.getBookingList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
