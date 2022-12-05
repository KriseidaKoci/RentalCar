package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.RevenueDTO;

import java.util.List;

public interface RevenueService {
    RevenueDTO getById (Long Id);
    List<RevenueDTO> getAll();
    RevenueDTO createRevenue(RevenueDTO revenueDTO);
    RevenueDTO updateRevenue(RevenueDTO revenueDTO);
  String deleteRevenue(Long id);
}
