package pl.dszerszen.parking.repository.entity;

import java.sql.Date;

public record ReservationEntity(String id, Date date, String registrationNumber, int spot) {
}
