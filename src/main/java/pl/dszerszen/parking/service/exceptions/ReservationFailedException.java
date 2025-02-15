package pl.dszerszen.parking.service.exceptions;

public class ReservationFailedException extends RuntimeException {
    public ReservationFailedException(String message) {
        super(message);
    }
}
