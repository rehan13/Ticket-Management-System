package model.exceptions;

public class SeatNumberNotFoundException extends TicketNumberGenerateFailException {

    public SeatNumberNotFoundException(String msg) {
        super(msg);
    }
}
