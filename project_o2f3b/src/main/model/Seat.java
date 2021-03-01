package model;

// reserves a seat and stores its information
public class Seat {
    private String seatNo;
    private boolean bookingStatus;
    private String seatType;
    private boolean window;
    private User bookedBy;
    private int price;

    //Constructor

    public Seat(String seatNo, boolean bookingStatus, String seatType, boolean window, User bookedBy,int price) {
        this.seatNo = seatNo;
        this.bookingStatus = bookingStatus;
        this.seatType = seatType;
        this.window = window;
        this.bookedBy = bookedBy;
        this.price = price;
    }

    // Empty Constructor
    public Seat() {

    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public boolean isWindow() {
        return window;
    }

    public void setWindow(boolean window) {
        this.window = window;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public boolean bookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getSeatType() {
        return seatType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}