package org.assignment.court_reservation_system.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assignment.court_reservation_system.dao.ReservationDao;
import org.assignment.court_reservation_system.model.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Reservation.class, id));
    }

    @Override
    public List<Reservation> findAll() {
        return entityManager.createQuery("SELECT r FROM Reservation r WHERE r.deleted = false", Reservation.class)
                .getResultList();
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) {
            reservation.setDeleted(true); // soft delete
            entityManager.merge(reservation);
        }
    }

    @Override
    public List<Reservation> findByCourtAndOverlap(Long courtId, LocalDateTime startTime, LocalDateTime endTime) {
        return List.of();
    }
}

