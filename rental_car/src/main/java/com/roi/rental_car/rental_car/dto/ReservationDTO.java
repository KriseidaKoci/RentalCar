package com.roi.rental_car.rental_car.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationDTO {
    private Long reservationId;
    private LocalDate reservationBooking;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private Double amount;

    private BranchDTO bookingbranch;
    private BranchDTO returnbrench;
    private List<CarDTO> cars;
    private CustomerDTO customer;
    private RefundDTO refund;
}
