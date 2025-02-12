package pl.dszerszen.parking.service.exceptions;

public class NoParkingSpaceException extends RuntimeException {
    public NoParkingSpaceException() {
        super("There is no parking space available for requested parameters");
    }
}
