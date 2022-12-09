package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.UnavailableStatusDTO;
import com.roi.rental_car.rental_car.entities.UnavailableStatus;
import com.roi.rental_car.rental_car.mappers.UnavailableStatusMapper;
import com.roi.rental_car.rental_car.repositories.UnavailableRepo;
import com.roi.rental_car.rental_car.services.UnavailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnavailableServiceImpl implements UnavailableService {
    @Autowired
    UnavailableStatusMapper unavailableStatusMapper;
    @Autowired
    UnavailableRepo unavailableRepo;

    @Override
    public UnavailableStatusDTO getById(Long id) {
        UnavailableStatus unavailableStatus = unavailableRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Status with this id not found"));
        return unavailableStatusMapper.toDto(unavailableStatus);
    }

    @Override
    public List<UnavailableStatusDTO> getAll() {
        List<UnavailableStatusDTO> unavailableStatusDTOList = unavailableStatusMapper.toDtoList(unavailableRepo.findAll());
        return unavailableStatusDTOList;
    }

    @Override
    public UnavailableStatusDTO createUnavailableStatus(UnavailableStatusDTO unavailableStatusDTO) {
        if (unavailableStatusDTO.getStatusId() != null) throw new RuntimeException("Id must be null");
        UnavailableStatus unavailableStatus = unavailableStatusMapper.toEntity(unavailableStatusDTO);
        unavailableRepo.save(unavailableStatus);
        return unavailableStatusMapper.toDto(unavailableStatus);
    }

    @Override
    public UnavailableStatusDTO updateUnavailableStatus(UnavailableStatusDTO unavailableStatusDTO) {
        if (unavailableStatusDTO.getStatusId() == null) throw new RuntimeException("Id must not be null");
        UnavailableStatus unavailableStatus = unavailableRepo.findById(unavailableStatusDTO.getStatusId()).orElseThrow(
                () -> new RuntimeException("Branch with this id does not exists"));
        unavailableStatus.setStatus(unavailableStatusDTO.getStatus());
        unavailableRepo.save(unavailableStatus);
        return unavailableStatusMapper.toDto(unavailableStatus);

    }

    @Override
    public String deleteUnavailableStatus(Long id) {
        try {
            if (unavailableRepo.findById(id).isEmpty()) throw new RuntimeException("This status doesnt exist");
            unavailableRepo.deleteById(id);
            return "Status with this id has been deleted";
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
