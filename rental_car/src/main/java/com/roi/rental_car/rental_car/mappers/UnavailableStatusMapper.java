package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.UnavailableStatusDTO;
import com.roi.rental_car.rental_car.entities.UnavailableStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UnavailableStatusMapper implements BaseMapper <UnavailableStatus, UnavailableStatusDTO>{
    @Override
    public UnavailableStatusDTO toDto(UnavailableStatus unavailableStatus) {
        if (unavailableStatus == null) return null;
        UnavailableStatusDTO unavailableStatusDTO = new UnavailableStatusDTO();
        unavailableStatusDTO.setStatusId(unavailableStatus.getStatusId());
        unavailableStatusDTO.setStatus(unavailableStatus.getStatus());
        unavailableStatusDTO.setDate(unavailableStatus.getDate());
        return unavailableStatusDTO;
    }

    @Override
    public UnavailableStatus toEntity(UnavailableStatusDTO unavailableStatusDTO) {
        if (unavailableStatusDTO == null) return null;
        UnavailableStatus unavailableStatus = new UnavailableStatus();
        unavailableStatus.setStatusId(unavailableStatusDTO.getStatusId());
        unavailableStatus.setStatus(unavailableStatusDTO.getStatus());
        unavailableStatus.setDate(unavailableStatusDTO.getDate());
        return unavailableStatus;
    }

    @Override
    public List<UnavailableStatusDTO> toDtoList(List<UnavailableStatus> e) {
        if (e == null) return null;
        List<UnavailableStatusDTO> unavailableStatusDTOList= new ArrayList<>();
        for(UnavailableStatus unavailableStatus:e){
            UnavailableStatusDTO unavailableStatusDTO=toDto(unavailableStatus);
            unavailableStatusDTOList.add(unavailableStatusDTO);
        }
        return unavailableStatusDTOList;
    }

    @Override
    public List<UnavailableStatus> toEntitity(List<UnavailableStatusDTO> d) {
        if (d == null) return null;
        List<UnavailableStatus> unavailableStatuses= new ArrayList<>();
        for(UnavailableStatusDTO unavailableStatusDTO:d){
            UnavailableStatus unavailableStatus=toEntity(unavailableStatusDTO);
            unavailableStatuses.add(unavailableStatus);
        }
        return unavailableStatuses;
    }
}
