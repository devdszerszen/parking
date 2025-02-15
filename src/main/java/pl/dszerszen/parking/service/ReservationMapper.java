package pl.dszerszen.parking.service;

import pl.dszerszen.parking.repository.entity.ReservationEntity;
import pl.dszerszen.parking.service.dto.ParkingReservationDto;

import java.sql.Date;

public class ReservationMapper {
    public ParkingReservationDto mapToDto(ReservationEntity entity) {
        return new ParkingReservationDto(entity.id(),
                entity.date().toLocalDate(),
                entity.registrationNumber(),
                entity.spot());
    }

    public ReservationEntity mapToEntity(ParkingReservationDto dto) {
        return new ReservationEntity(dto.getId(),
                Date.valueOf(dto.getDate()),
                dto.getRegistrationNumber(),
                dto.getParkingSpaceNumber());
    }
}