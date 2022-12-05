package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.BranchDTO;
import com.roi.rental_car.rental_car.dto.CarDTO;
import com.roi.rental_car.rental_car.entities.Car;

import java.util.List;

public interface CarService {
    CarDTO getById(Long id);
    List<CarDTO> getAll();
    CarDTO createCar(CarDTO carDTO);
    CarDTO updateCar (CarDTO carDTO);
    String deleteCar(Long id);
}
