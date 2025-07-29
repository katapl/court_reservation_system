package org.assignment.court_reservation_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courts")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "surfaceType_id", nullable = false)
    private SurfaceType surfaceType;

    @Column(nullable = false)
    private boolean deleted = false;

    public void setDeleted(boolean b) {
        deleted = b;
    }

    public SurfaceType getSurfaceType() {
        return surfaceType;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Gettery, settery, equals, hashCode
}
