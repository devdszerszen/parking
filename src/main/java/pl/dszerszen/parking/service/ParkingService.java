package pl.dszerszen.parking.service;

import pl.dszerszen.parking.service.dto.ParkingReservationDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface ParkingService {
    String addReservation(ParkingReservationDto reservation);
    List<ParkingReservationDto> getReservationsForCar(String registrationNumber);
    ParkingReservationDto getReservationById(String id) throws NoSuchElementException;
}