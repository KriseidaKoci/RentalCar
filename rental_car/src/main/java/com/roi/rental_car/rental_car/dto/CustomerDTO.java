package com.roi.rental_car.rental_car.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String email;
    private String address;
    private List<ReservationDTO> reservations;
    private RentalDTO rental;
}
