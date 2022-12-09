package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.ReservationDTO;
import com.roi.rental_car.rental_car.static_data.CarStatus;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationDTO getById(Long id);
    List<ReservationDTO> getAll();
    public ReservationDTO create(ReservationDTO dto);
    ReservationDTO updateReservation(ReservationDTO reservationDTO);
    String deleteReservation(Long id);

    String returnCar(Long id, LocalDate returndate, String  status, Long carId);
}
