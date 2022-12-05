package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

public interface ReservationRepo extends JpaRepository<Reservation, Long > {
    Reservation getReservationByReservationId(Long id);
    Reservation getReservationByReservationBooking(LocalDate date);
    Reservation getReservationByReservationStart(LocalDate start);
    Reservation getReservationByReservationEnd(LocalDate end);
    Reservation getReservationByAmount(Double amount);
}
