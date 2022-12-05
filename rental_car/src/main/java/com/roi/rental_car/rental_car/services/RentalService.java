package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.RentalDTO;

import java.util.List;

public interface RentalService {
    RentalDTO getById(Long id);
    List<RentalDTO> getAll();
    RentalDTO createRental(RentalDTO rentalDTO);
    RentalDTO updateRental(RentalDTO rentalDTO);
    String deleteRental(Long id);
}
