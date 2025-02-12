package pl.dszerszen.parking.service;

import org.springframework.stereotype.Service;
import pl.dszerszen.parking.model.ParkingReservationDto;
import pl.dszerszen.parking.service.exceptions.NoParkingSpaceException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ArrayList<ParkingReservationDto> reservations = new ArrayList<>();

    public ParkingServiceImpl() {
        reservations.add(new ParkingReservationDto(LocalDate.parse("2025-12-25"), "KR12345", 1));
        reservations.add(new ParkingReservationDto(LocalDate.parse("2025-05-11"), "KK11111", 2));
    }

    @Override
    public String addReservation(ParkingReservationDto reservation) throws NoParkingSpaceException {
        if (reservation.getDate().isBefore(LocalDate.now())) {
            throw new DateTimeException("Date cannot be past");
        }

        List<Integer> availableSpaces = getAvailableSpaces(reservation.getDate());

        if (availableSpaces.isEmpty()) {
            System.out.println("Case 1");
            throw new NoParkingSpaceException();
        }

        if (reservation.getParkingSpaceNumber() != null && reservation.getParkingSpaceNumber() > 0) {
            if (!availableSpaces.contains(reservation.getParkingSpaceNumber())) {
                throw new NoParkingSpaceException();
            }
        } else {
            reservation.setParkingSpaceNumber(availableSpaces.getFirst());
        }

        reservations.add(reservation);
        return reservation.getId();
    }

    @Override
    public List<ParkingReservationDto> getReservationsForCar(String registrationNumber) {
        return reservations.stream()
                .filter(res -> res.getRegistrationNumber().equals(registrationNumber))
                .toList();
    }

    @Override
    public ParkingReservationDto getReservationById(String id) throws NoSuchElementException {
        return reservations.stream()
                .filter(res -> res.getId().equals(id))
                .findFirst().orElseThrow();
    }

    private List<Integer> getAvailableSpaces(LocalDate date) {
        if (date.isEqual(LocalDate.now())) {
            return List.of();
        } else {
            return List.of(4);
        }
    }
}