package model.exceptions;

public class FlightNumberNotFoundException extends TicketNumberGenerateFailException {

    public FlightNumberNotFoundException(String msg) {
        super(msg);
    }
}
