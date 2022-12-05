package com.roi.rental_car.rental_car.dto;
import lombok.Data;
import java.util.List;

@Data
public class BranchDTO {
    private String name;
    private Long branchId;
    private CityDTO city;
    private RentalDTO rental;
    private List<EmployeeDTO> employees;
    private List<CarDTO> cars;
    private List<EmployeeDTO> menagers;
    private String warning;

}
