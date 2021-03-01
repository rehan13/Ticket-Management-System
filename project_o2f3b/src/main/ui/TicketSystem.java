package ui;

import model.Booking;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Adds tickets to the Json
public class TicketSystem {
    private String name;
    private ArrayList<Booking> bookingList = new ArrayList<Booking>();
    private static final String JSON_STORE = "./data/ticketSystem.json";

    private static TicketSystem ts = new TicketSystem("System");
    public static JSONArray jsonArray = new JSONArray();

    public TicketSystem(String name) {
        this.name = name;
    }

    public TicketSystem(String name, ArrayList<Booking> bookingList) {
        this.name = name;
        this.bookingList = bookingList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    //Constructs Json

    public JSONObject toJson() {
        JSONObject ticket = new JSONObject();
        ticket.put("System", name);
        ticket.put("Ticket",ticketsToJson());
        return ticket;

    }
    //Modifies: this
    // Effects: adds booking list items to Json ticket

    public void addBookingListItem(Booking book) {
        bookingList.add(book);
    }

    // EFFECTS: returns things in this workroom as a JSON array

    private JSONArray ticketsToJson() {
        for (Booking t : bookingList) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    // Modifies: this
    // Effects: Write data from Json

    public static void jsonWrite(TicketSystem ts) {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(ts);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Modifies: this
    // Effects: Read data from Json

    public static void jsonRead() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            ts = jsonReader.read();
            if (!ts.equals(null)) {
                printTickets();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to Read to file: " + JSON_STORE);
        }
    }

    //Modifies: this
    //Effects: prints tickets from Json

    private static void printTickets() {
        ArrayList<Booking> bookings = ts.getBookingList();

        for (Booking t : bookings) {
            System.out.println(t.toJson());
        }
    }

}

