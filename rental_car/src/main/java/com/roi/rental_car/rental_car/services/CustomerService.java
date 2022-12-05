package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO getById(Long id);
    List<CustomerDTO> getAll();
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    String deleteCustomer(Long id);

}
