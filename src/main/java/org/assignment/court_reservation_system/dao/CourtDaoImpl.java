package org.assignment.court_reservation_system.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assignment.court_reservation_system.model.Court;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CourtDaoImpl implements CourtDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Court court) {
        entityManager.persist(court);
    }

    @Override
    public void update(Court court) {
        entityManager.merge(court);
    }

    @Override
    public Court findById(Long id) {
        return entityManager.createQuery(
                        "SELECT c FROM Court c WHERE c.id = :id AND c.deleted = false", Court.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Court> findAll() {
        return entityManager.createQuery(
                        "SELECT c FROM Court c WHERE c.deleted = false", Court.class)
                .getResultList();
    }

    @Override
    public void softDelete(Long id) {
        Court court = entityManager.find(Court.class, id);
        if (court != null) {
            court.setDeleted(true);
            entityManager.merge(court);
        }
    }
}

