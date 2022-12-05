package com.roi.rental_car.rental_car.dto;

import lombok.Data;

import java.util.List;

@Data
public class RentalDTO {
    private Long rentalId;
    private String name;
    private String internetDomain;
    private String contactAddress;
    private List<BranchDTO> branches;
    private List <CustomerDTO> customers;
}
