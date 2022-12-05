package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RentalRepo extends JpaRepository <Rental, Long> {
    Rental getRentalByRentalId(Long id);
    Rental getRentalByName(String  name);
    Rental getRentalByInternetDomain(String domain);
    Rental getRentalByContactAddress(String adress);
}
