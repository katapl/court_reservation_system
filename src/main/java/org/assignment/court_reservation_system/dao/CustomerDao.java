package org.assignment.court_reservation_system.dao;

import org.assignment.court_reservation_system.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDao {
    void save(Customer customer);
    Optional<Customer> findByPhone(String phoneNumber);
    List<Customer> findAll();
}
