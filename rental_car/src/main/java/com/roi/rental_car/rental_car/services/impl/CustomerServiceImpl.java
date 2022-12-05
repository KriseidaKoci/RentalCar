package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.CustomerDTO;
import com.roi.rental_car.rental_car.entities.Customer;
import com.roi.rental_car.rental_car.mappers.CustomerMapper;
import com.roi.rental_car.rental_car.repositories.CustomerRepo;
import com.roi.rental_car.rental_car.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerDTO getById(Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Customer with id" + id + " does not exists"));
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerMapper.toDtoList(customerRepo.findAll());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if ((customerDTO.getCustomerId() != null)) throw new RuntimeException("Id must be null");
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepo.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getCustomerId() == null) throw new RuntimeException("Id must not be null");
        Customer customer = customerRepo.findById(customerDTO.getCustomerId()).
                orElseThrow(() -> new RuntimeException("Customer with this id " + customerDTO.getCustomerId() + "does not exists"));



        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        customerRepo.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public String deleteCustomer(Long id) {
        try {
            if (customerRepo.findById(id).isEmpty())
                throw new RuntimeException("Customer with this id" + id + "does not exists");
            customerRepo.deleteById(id);
            return "Branch with this id " + id + "has been deleted";
        } catch (RuntimeException e) {
            return e.getMessage();
        }

    }




}
