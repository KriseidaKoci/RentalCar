package com.roi.rental_car.rental_car.dto;

import lombok.Data;

import java.util.List;

@Data
public class CityDTO {
    private String name;
    private List<BranchDTO> branches;

}
