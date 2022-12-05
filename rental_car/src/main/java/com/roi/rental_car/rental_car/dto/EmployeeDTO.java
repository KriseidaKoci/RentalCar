package com.roi.rental_car.rental_car.dto;

import com.roi.rental_car.rental_car.static_data.Position;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long employeeId;
    private String name;
    private Position posittion;
    private BranchDTO branch;

}
