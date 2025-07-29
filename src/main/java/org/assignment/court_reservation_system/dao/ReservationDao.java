package org.assignment.court_reservation_system.dao;

import org.assignment.court_reservation_system.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationDao {
    void save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    void delete(Long id);

    List<Reservation> findByCourtAndOverlap(Long courtId, LocalDateTime startTime, LocalDateTime endTime);
}
