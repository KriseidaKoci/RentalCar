package com.roi.rental_car.rental_car.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RefundDTO {
    private Long refundId;
    private LocalDate returnDate;
    private String refundCommens;
    private Double surcharge;
    private Double refund;

    private ReservationDTO reservation;


}
