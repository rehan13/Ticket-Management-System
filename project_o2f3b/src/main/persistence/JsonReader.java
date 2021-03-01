package persistence;

import model.Booking;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Main;
import ui.TicketSystem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
// Reads data from JSon

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TicketSystem from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TicketSystem read() throws IOException {
        Main.jsonString = readFile(source);
        JSONObject jsonObject = new JSONObject(Main.jsonString);
        return getJsonTickets(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TicketSystem from JSON object and returns it

    public TicketSystem getJsonTickets(JSONObject jsonObject) throws IOException {
        String jsonData = null;
        jsonData = readFile(source);

        JSONObject jsonArray = new JSONObject(jsonData);
        return parseTicketSystem(jsonObject);

    }


    // EFFECTS: parses TicketSystem from JSON object and returns it

    private TicketSystem parseTicketSystem(JSONObject jsonObject) {
        String name = jsonObject.getString("System");
        TicketSystem ts = new TicketSystem(name);
        addTickets(ts, jsonObject);
        return ts;
    }

    //Effects: adds a ticket to the Json

    private void addTickets(TicketSystem ts, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Ticket");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addTicket(ts, nextThingy);
        }
    }

    // MODIFIES: ts, jsonObject
    // EFFECTS: parses from JSON object and adds it to Ticket System

    private void addTicket(TicketSystem ts, JSONObject jsonObject) {
        String name = jsonObject.getString("System");
        String destination = jsonObject.getString("Destination");
        String flightNo = jsonObject.getString("Flight No");
        String ticketNo = jsonObject.getString("Ticket No");
        String seatNo = jsonObject.getString("Seat No");
        String contactNo = jsonObject.getString("Contact No");
        String name1 = jsonObject.getString("Name");
        String dateOfBirth = jsonObject.getString("Date of Birth");
        String emergencyContactNo = jsonObject.getString("Emergency Contact No");
        String passportNo = jsonObject.getString("Passport No");
        Booking booking = new Booking(name,destination,flightNo,ticketNo,seatNo,contactNo,name1,dateOfBirth,
                emergencyContactNo,passportNo);
        booking.toJson();
        ts.addBookingListItem(booking);
    }

}
