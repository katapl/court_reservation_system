package org.assignment.court_reservation_system.dto;

import java.math.BigDecimal;

public class ReservationResponseDto {

    private BigDecimal price;

    public ReservationResponseDto(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
