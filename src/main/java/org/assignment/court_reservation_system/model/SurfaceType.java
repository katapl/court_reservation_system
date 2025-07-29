package org.assignment.court_reservation_system.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "surface_types")
public class SurfaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal pricePerMinute;

    @Column(nullable = false)
    private boolean deleted = false;

    public BigDecimal getPricePerMinute() {
        return pricePerMinute;
    }

    // Gettery, settery, equals, hashCode
}

