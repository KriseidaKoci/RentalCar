package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO getById(Long id);
    List<EmployeeDTO> getAll();
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);
    String  deleteEmploye(Long id);
}
