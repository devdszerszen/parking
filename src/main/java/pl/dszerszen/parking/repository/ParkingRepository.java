package pl.dszerszen.parking.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.dszerszen.parking.repository.entity.ReservationEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ParkingRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final static String ID = "id";
    private final static String DATE = "reservation_date";
    private final static String REGISTRATION_NUMBER = "registration_number";
    private final static String SPOT = "spot";

    private static class ReservationEntityMapper implements RowMapper<ReservationEntity> {

        @Override
        public ReservationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ReservationEntity(rs.getString(ID), rs.getDate(DATE), rs.getString(REGISTRATION_NUMBER), rs.getInt(SPOT));
        }
    }

    public int add(ReservationEntity reservation) {
        String sql = "insert into reservations ("
                + ID + ", "
                + DATE + ", "
                + REGISTRATION_NUMBER + ", "
                + SPOT + ") values( ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                reservation.id(),
                reservation.date(),
                reservation.registrationNumber(),
                reservation.spot());
    }

    public Optional<ReservationEntity> findReservationById(String id) {
        String sql = "select * from reservations where " + ID + "=?";
        return jdbcTemplate.query(sql, new ReservationEntityMapper(), id).stream().findFirst();
    }

    public boolean hasActiveReservation(String registrationNumber, Date date) {
        String sql = "select * from reservations where " + REGISTRATION_NUMBER + "=? AND " + DATE + "=?";
        return jdbcTemplate.query(sql, new ReservationEntityMapper(), registrationNumber, date).size() == 1;
    }

    public List<ReservationEntity> findReservationsByRegistrationNumber(String registrationNumber) {
        String sql = "select * from reservations where " + REGISTRATION_NUMBER + "=?";
        return jdbcTemplate.query(sql, new ReservationEntityMapper(), registrationNumber);
    }

    public List<Integer> findAvailableSpotsForDate(Date date) {
        String sql = "select * from spots except select " + SPOT + " from reservations where " + DATE + "=?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt(ID), date);
    }
}