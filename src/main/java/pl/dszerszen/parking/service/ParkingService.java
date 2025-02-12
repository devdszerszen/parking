package pl.dszerszen.parking.service;

import pl.dszerszen.parking.model.ParkingReservationDto;
import pl.dszerszen.parking.service.exceptions.NoParkingSpaceException;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public interface ParkingService {
    String addReservation(ParkingReservationDto reservation) throws NoParkingSpaceException;
    List<ParkingReservationDto> getReservationsForCar(String registrationNumber);
    ParkingReservationDto getReservationById(String id) throws NoSuchElementException;
}