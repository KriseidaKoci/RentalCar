package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.EmployeeDTO;
import com.roi.rental_car.rental_car.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public EmployeeDTO getByid(@RequestParam Long id ){
        return employeeService.getById(id);}
    @GetMapping("/all")
    public List<EmployeeDTO> getAll(){
        return employeeService.getAll();}

  @PostMapping
public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(employeeDTO);
  }
  @PutMapping
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(employeeDTO);
  }
  @DeleteMapping("/employee/{id}")
    public String deleteCustomer(@PathVariable Long id){
        return employeeService.deleteEmploye(id);
  }
}