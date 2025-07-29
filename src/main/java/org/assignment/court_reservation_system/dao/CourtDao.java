package org.assignment.court_reservation_system.dao;

import org.assignment.court_reservation_system.model.Court;
import java.util.List;

public interface CourtDao {
    void save(Court court);
    void update(Court court);
    Court findById(Long id);
    List<Court> findAll();
    void softDelete(Long id);
}

