package model;

import model.exceptions.FlightNumberNotFoundException;
import model.exceptions.SeatNumberNotFoundException;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Date;

//Makes a booking and generates a ticket
public class Booking implements Writable {
    private String name;
    private String passportNo;
    private String dateOfBirth;
    private String address;
    private String contactNo;
    private String emergencyContact;
    private String gender;
    private String email;
    private static String seatNo;
    private String seatType;
    private boolean windowSeat;
    private static String flightNo;
    private String departure;
    private String arrival;
    private String flightType;
    private String duration;
    private Date date;
    private String bookingNo;
    private int price;
    private String time;

    public Booking(){

    }
    // Constructor

    public Booking(String name, String passportNo, String dateOfBirth, String address, String contactNo,
                   String emergencyContact, String gender, String email, String seatNo, String seatType,
                   boolean windowSeat, String flightNo, String departure, String arrival, String flightType,
                   String duration, Date date, String bookingNo,int price) {

        this.name = name;
        this.passportNo = passportNo;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactNo = contactNo;
        this.emergencyContact = emergencyContact;
        this.gender = gender;
        this.email = email;
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.windowSeat = windowSeat;
        this.flightNo = flightNo;
        this.departure = departure;
        this.arrival = arrival;
        this.flightType = flightType;
        this.duration = duration;
        this.date = date;
        this.bookingNo = bookingNo;
        this.price = price;
    }
    // Constructor

    public Booking(String name,String destination,String flightNo,String ticketNo,String seatNo,String contactNo,
                   String name1,String dateOfBirth,String emergencyContactNo,String passportNo) {

        this.name = name;
        this.passportNo = passportNo;
        this.dateOfBirth = dateOfBirth;
        this.contactNo = contactNo;
        this.emergencyContact = emergencyContactNo;
        this.email = name1;
        this.seatNo = seatNo;
        this.flightNo = flightNo;
        this.arrival = destination;
        this.bookingNo = ticketNo;
    }

    //Stores object to Json

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("System", name);
        json.put("Ticket No", getBookingNo());
        json.put("Flight No", getFlightNo());
        json.put("Seat No", getSeatNo());
        json.put("Destination",getArrival());
        json.put("Name",getName());
        json.put("Passport No",getPassportNo());
        json.put("Date of Birth",getDateOfBirth());
        json.put("Contact No",getContactNo());
        json.put("Emergency Contact No",getEmergencyContact());
        return json;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public boolean isWindowSeat() {
        return windowSeat;
    }

    public void setWindowSeat(boolean windowSeat) {
        this.windowSeat = windowSeat;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    // Require: flight no. and seat no.
    // Effects: generates ticket number

    public static String generateBookingNo() throws SeatNumberNotFoundException, FlightNumberNotFoundException {
        String bookingNo = "";
        int lastDigits = 5432;
        lastDigits = lastDigits + 1;
        if (seatNo.equals("")) {
            throw new SeatNumberNotFoundException("Seat Not Found!");
        } else if (flightNo.equals("")) {
            throw new FlightNumberNotFoundException("Flight number not found!");
        } else {
            bookingNo = flightNo + "-" + lastDigits + seatNo;
        }
        return bookingNo;
    }



}

