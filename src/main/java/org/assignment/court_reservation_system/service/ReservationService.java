package org.assignment.court_reservation_system.service;

import org.assignment.court_reservation_system.dao.CourtDao;
import org.assignment.court_reservation_system.dao.CustomerDao;
import org.assignment.court_reservation_system.dao.ReservationDao;
import org.assignment.court_reservation_system.dto.ReservationRequestDto;
import org.assignment.court_reservation_system.model.Court;
import org.assignment.court_reservation_system.model.Customer;
import org.assignment.court_reservation_system.model.GameType;
import org.assignment.court_reservation_system.model.Reservation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;
    private final CustomerDao customerDao;
    private final CourtDao courtDao;

    public ReservationService(ReservationDao reservationDao, CustomerDao customerDao, CourtDao courtDao) {
        this.reservationDao = reservationDao;
        this.customerDao = customerDao;
        this.courtDao = courtDao;
    }

    public BigDecimal createReservation(ReservationRequestDto dto) {
        // 1. Over, že court existuje
        Court court = courtDao.findById(dto.getCourtId());

        // 2. Over, že čas nekoliduje s inou rezerváciou
        List<Reservation> existing = reservationDao.findByCourtAndOverlap(
                dto.getCourtId(), dto.getStartTime(), dto.getEndTime()
        );
        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("Court is already booked for that time.");
        }

        // 3. Nájde alebo vytvorí zákazníka
        Optional<Customer> optionalCustomer = customerDao.findByPhone(dto.getPhoneNumber());

        Customer customer;
        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        } else {
            customer = new Customer();
            customer.setPhoneNumber(dto.getPhoneNumber());
            customer.setName(dto.getCustomerName());
            customerDao.save(customer);
        }


        // 4. Spočítaj cenu
        BigDecimal price = court.getSurfaceType().getPricePerMinute()
                .multiply(BigDecimal.valueOf(Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes()));
        if (dto.getGameType() == GameType.DOUBLES) {
            price = price.multiply(BigDecimal.valueOf(1.5));
        }

        // 5. Vytvor rezerváciu
        Reservation r = new Reservation();
        r.setCourt(court);
        r.setCustomer(customer);
        r.setStartTime(dto.getStartTime());
        r.setEndTime(dto.getEndTime());
        r.setGameType(dto.getGameType());
        r.setPrice(price);
        r.setCreatedAt(LocalDateTime.now());
        reservationDao.save(r);

        return price;
    }
}

