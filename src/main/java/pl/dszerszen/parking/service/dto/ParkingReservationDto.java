package pl.dszerszen.parking.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.UUID;

public class ParkingReservationDto {

    @JsonIgnore
    private final String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate date;

    @NotBlank(message = "registrationNumber cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,8}$", message = "Wrong registrationNumber format")
    private final String registrationNumber;

    private Integer parkingSpaceNumber;

    public ParkingReservationDto(String id, LocalDate date, String registrationNumber, Integer parkingSpaceNumber) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.date = date;
        this.registrationNumber = registrationNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Integer getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(int parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }
}