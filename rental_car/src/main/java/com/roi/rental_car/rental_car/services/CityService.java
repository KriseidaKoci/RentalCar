package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.CityDTO;

import java.util.List;

public interface CityService {
    CityDTO getById(String id);
    List<CityDTO> getAll();
    CityDTO updateCity(CityDTO cityDTO);
    CityDTO createCity(CityDTO cityDTO);
    String deleteCity(String id);

}
