package org.assignment.court_reservation_system.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assignment.court_reservation_system.model.Court;
import org.assignment.court_reservation_system.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Optional<Customer> findByPhone(String phoneNumber) {
        List<Customer> result = entityManager
                .createQuery("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber AND c.deleted = false", Customer.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();

        return result.stream().findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c WHERE c.deleted = false", Customer.class)
                .getResultList();
    }
}
