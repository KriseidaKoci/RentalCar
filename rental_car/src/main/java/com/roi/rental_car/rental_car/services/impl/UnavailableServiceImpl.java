package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.UnavailableStatusDTO;
import com.roi.rental_car.rental_car.services.UnavailableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnavailableServiceImpl implements UnavailableService {
    @Override
    public UnavailableStatusDTO getById(Long id) {
        return null;
    }

    @Override
    public List<UnavailableStatusDTO> getAll() {
        return null;
    }

    @Override
    public UnavailableStatusDTO createUnavailableStatus(UnavailableStatusDTO unavailableStatusDTO) {
        return null;
    }

    @Override
    public UnavailableStatusDTO updateUnavailableStatus(UnavailableStatusDTO unavailableStatusDTO) {
        return null;
    }

    @Override
    public String deleteUnavailableStatus(Long id) {

        return null;
    }
}
