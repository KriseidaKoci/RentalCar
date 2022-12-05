package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.CustomerDTO;
import com.roi.rental_car.rental_car.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerMapper implements  BaseMapper <Customer, CustomerDTO> {
    @Override
    public CustomerDTO toDto(Customer customer) {
        if (customer == null) return null;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setCustomerId(customerDTO.getCustomerId());
        return customer;
    }

    @Override
    public List<CustomerDTO> toDtoList(List<Customer> e) {
        if (e == null) return null;
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : e) {
            CustomerDTO customerDTO = toDto(customer);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public List<Customer> toEntitity(List<CustomerDTO> d) {
        if (d == null) return null;
        List<Customer> customers = new ArrayList<>();
        for (CustomerDTO customerDTO : d) {
            Customer customer = toEntity(customerDTO);
            customers.add(customer);
        }
        return customers;
    }
}