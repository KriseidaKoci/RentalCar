package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    ReservationDTO getById(Long id);
    List<ReservationDTO> getAll();
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO updateReservation(ReservationDTO reservationDTO);
    String deleteReservation(Long id);
}
