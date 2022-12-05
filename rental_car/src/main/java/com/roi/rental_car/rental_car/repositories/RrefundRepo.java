package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

public interface RrefundRepo extends JpaRepository<Refund, Long> {
    Refund getRefundByRefundId(Long id);
    Refund getRefundByReturnDate(LocalDate date);
    Refund getRefundByRefundCommens(String comments);
    Refund getRefundBySurcharge(Double surcharge);
    Refund getRefundByRefund(Double refund);
}
