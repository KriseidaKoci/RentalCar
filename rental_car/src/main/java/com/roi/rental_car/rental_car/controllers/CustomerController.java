package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.CustomerDTO;
import com.roi.rental_car.rental_car.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @GetMapping
    public CustomerDTO getById (@RequestParam Long id) {
        return customerService.getById(id);}
    @GetMapping("/all")
    public List<CustomerDTO> getAll(){
        return customerService.getAll();}
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }
    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }

}
