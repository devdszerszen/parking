package pl.dszerszen.parking.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.dszerszen.parking.model.ParkingReservationDto;
import pl.dszerszen.parking.service.ParkingService;
import pl.dszerszen.parking.utils.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService service;

    @Autowired
    public ParkingController(ParkingService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addReservation(@Valid @RequestBody ParkingReservationDto reservation) {
        service.addReservation(reservation);
        return reservation.getId();
    }

    @GetMapping("/reservations")
    public List<ParkingReservationDto> getReservations(@RequestParam @NotNull String registrationNumber) {
        return service.getReservationsForCar(StringUtils.removeSpaces(registrationNumber));
    }

    @GetMapping("/reservations/details")
    public ParkingReservationDto getReservationDetails(@RequestParam @NotNull String id) {
        try {
            return service.getReservationById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}