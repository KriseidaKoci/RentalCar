package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RevenueRepo extends JpaRepository <Revenue, Long> {
    Revenue getRevenueByRevenueId(Long id);
    Revenue getRevenueByMonth(String month);
    Revenue getRevenueByAmount(Double amount);
}
