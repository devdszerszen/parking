package pl.dszerszen.parking.service;

import org.springframework.stereotype.Service;
import pl.dszerszen.parking.model.ParkingReservationDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FakeParkingService implements ParkingService {

    private final ArrayList<ParkingReservationDto> db = new ArrayList<>();

    public FakeParkingService() {
        db.add(new ParkingReservationDto(LocalDate.parse("2025-12-25"), "KR 12345"));
        db.add(new ParkingReservationDto(LocalDate.parse("2025-05-11"), "RSA 11111"));
    }

    @Override
    public String addReservation(ParkingReservationDto reservation) {
        db.add(reservation);
        return reservation.getId();
    }

    @Override
    public List<ParkingReservationDto> getReservationsForCar(String registrationNumber) {
        return db.stream()
                .filter(res -> res.getRegistrationNumber().equals(registrationNumber))
                .toList();
    }

    @Override
    public ParkingReservationDto getReservationById(String id) throws NoSuchElementException {
        return db.stream()
                .filter(res -> res.getId().equals(id))
                .findFirst().orElseThrow();
    }
}