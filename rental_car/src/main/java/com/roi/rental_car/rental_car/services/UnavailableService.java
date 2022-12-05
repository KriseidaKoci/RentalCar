package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.UnavailableStatusDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UnavailableService {
    UnavailableStatusDTO getById (Long id);
    List<UnavailableStatusDTO> getAll();
    UnavailableStatusDTO createUnavailableStatus(UnavailableStatusDTO unavailableStatusDTO);
    UnavailableStatusDTO updateUnavailableStatus(UnavailableStatusDTO unavailableStatusDTO);
    String deleteUnavailableStatus(Long id);

}
