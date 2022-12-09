package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.RevenueDTO;
import com.roi.rental_car.rental_car.entities.Revenue;
import com.roi.rental_car.rental_car.mappers.RevenueMapper;
import com.roi.rental_car.rental_car.repositories.RevenueRepo;
import com.roi.rental_car.rental_car.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    private RevenueRepo revenueRepo;
    @Autowired
    private RevenueMapper revenueMapper;
    @Override
    public RevenueDTO getById(Long Id) {
        Revenue revenue=revenueRepo.findById(Id).orElseThrow(()-> new RuntimeException("This revenue doest exist"));
        return revenueMapper.toDto(revenue);
    }

    @Override
    public List<RevenueDTO> getAll() {
        return  revenueMapper.toDtoList(revenueRepo.findAll());
    }

    @Override
    public RevenueDTO createRevenue(RevenueDTO revenueDTO) {
        if (revenueDTO.getRevenueId()!=null) throw new RuntimeException("id must be null");
        Revenue revenue=revenueMapper.toEntity(revenueDTO);
        revenueRepo.save(revenue);
        return revenueMapper.toDto(revenue);
    }

    @Override
    public RevenueDTO updateRevenue(RevenueDTO revenueDTO) {
        if (revenueDTO.getRevenueId()==null) throw  new RuntimeException("ID must not be null");
        Revenue revenue=revenueRepo.findById(revenueDTO.getRevenueId()).orElseThrow(()-> new RuntimeException("This revenue doesnt exists"));
        revenue.setAmount(revenueDTO.getAmount());
        revenue.setMonth(revenueDTO.getMonth());
        revenueRepo.save(revenue);
        return revenueMapper.toDto(revenue);
    }

    @Override
    public String deleteRevenue(Long id) {
        try {
            if (revenueRepo.findById(id).isEmpty()) throw new RuntimeException("Revenue with this id doesnt exist");
            revenueRepo.deleteById(id);
            return "This car has been deleted";
        } catch (Exception e) {
            return e.getMessage() ;
        }
    }
}
