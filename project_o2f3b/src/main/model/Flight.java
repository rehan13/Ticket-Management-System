package model;

import java.util.Date;

// Stores all the flight details
public class Flight {
    private String airlineName;
    private String flightNo;
    private String departure;
    private String arrival;
    private String flightType;
    private String duration;
    private Date[] timeDate;
    private int businessSeatNo;
    private int economySeatNo;
    private Seat[] seatInfo;

    // Empty Constructor

    public Flight() {
    }

    // Constructor

    public Flight(String airlineName, String flightNo, String departure, String arrival,
                  String flightType, String duration, Date[] timeDate, int businessSeatNo,
                  int economySeatNo, Seat[] seatInfo) {
        this.airlineName = airlineName;
        this.flightNo = flightNo;
        this.departure = departure;
        this.arrival = arrival;
        this.flightType = flightType;
        this.duration = duration;
        this.timeDate = timeDate;
        this.businessSeatNo = businessSeatNo;
        this.economySeatNo = economySeatNo;
        this.seatInfo = seatInfo;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
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

    public Date[] getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(Date[] timeDate) {
        this.timeDate = timeDate;
    }

    public int getBusinessSeatNo() {
        return businessSeatNo;
    }

    public void setBusinessSeatNo(int businessSeatNo) {
        this.businessSeatNo = businessSeatNo;
    }

    public int getEconomySeatNo() {
        return economySeatNo;
    }

    public void setEconomySeatNo(int economySeatNo) {
        this.economySeatNo = economySeatNo;
    }

    public Seat[] getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(Seat[] seatInfo) {
        this.seatInfo = seatInfo;
    }
}