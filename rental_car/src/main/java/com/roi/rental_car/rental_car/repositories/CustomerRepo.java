package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.Customer;
import com.roi.rental_car.rental_car.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer getCustomerByCustomerId(Long id);
    Customer getCustomerByName(String name);
    Customer getCustomerByAddress(String adress);
    Customer getCustomerByEmail(String email);
    Boolean existsCustomerByNameIgnoreCase(String name);
    Boolean findCustomersByCustomerId(Long id);
    @Query(value = "from Customer customer where customer.rental.rentalId = :id", nativeQuery = true )
    List<Customer> findAllByRentalId(@Param("id") Long rentaId);
}
