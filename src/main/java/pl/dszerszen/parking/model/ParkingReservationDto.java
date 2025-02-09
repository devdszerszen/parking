package pl.dszerszen.parking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.dszerszen.parking.utils.StringUtils;

import java.time.LocalDate;
import java.util.UUID;

public class ParkingReservationDto {
    private final LocalDate date;
    private final String id;
    private final String registrationNumber;

    public ParkingReservationDto(@JsonFormat(pattern = "yyyy-MM-dd")LocalDate date, String registrationNumber) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.registrationNumber = StringUtils.removeSpaces(registrationNumber);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}