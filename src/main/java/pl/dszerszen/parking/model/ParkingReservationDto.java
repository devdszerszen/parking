package pl.dszerszen.parking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.UUID;

public class ParkingReservationDto {

    private final LocalDate date;

    private final String id;

    @NotBlank(message = "registrationNumber cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,8}$", message = "Wrong registrationNumber format")
    private final String registrationNumber;

    private Integer parkingSpaceNumber;

    public ParkingReservationDto(@JsonFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                 String registrationNumber,
                                 int parkingSpaceNumber) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.registrationNumber = registrationNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
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

    public Integer getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(int parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }
}