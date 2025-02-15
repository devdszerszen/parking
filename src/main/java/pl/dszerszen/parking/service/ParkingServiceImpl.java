package pl.dszerszen.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dszerszen.parking.repository.ParkingRepository;
import pl.dszerszen.parking.service.dto.ParkingReservationDto;
import pl.dszerszen.parking.service.exceptions.NoParkingSpaceException;
import pl.dszerszen.parking.service.exceptions.ReservationFailedException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;

    private final ReservationMapper mapper = new ReservationMapper();

    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public String addReservation(ParkingReservationDto reservation) {
        List<Integer> availableSpaces = getAvailableSpaces(reservation.getDate());

        if (availableSpaces.isEmpty()) {
            throw new NoParkingSpaceException();
        }

        if (reservation.getParkingSpaceNumber() != null && reservation.getParkingSpaceNumber() > 0) {
            if (!availableSpaces.contains(reservation.getParkingSpaceNumber())) {
                throw new NoParkingSpaceException();
            }
        } else {
            reservation.setParkingSpaceNumber(availableSpaces.getFirst());
        }
        int rowsAdded = parkingRepository.add(mapper.mapToEntity(reservation));
        if (rowsAdded != 1) {
            throw new ReservationFailedException("Unable to add reservation");
        }
        return reservation.getId();
    }

    @Override
    public List<ParkingReservationDto> getReservationsForCar(String registrationNumber) {
        return parkingRepository.findReservationsByRegistrationNumber(registrationNumber)
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public ParkingReservationDto getReservationById(String id) throws NoSuchElementException {
        return parkingRepository.findReservationById(id)
                .map(mapper::mapToDto)
                .orElseThrow(NoSuchElementException::new);
    }

    private List<Integer> getAvailableSpaces(LocalDate date) {
        return parkingRepository.findAvailableSpotsForDate(Date.valueOf(date));
    }
}