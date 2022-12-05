package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.UnavailableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UnavailableRepo extends JpaRepository <UnavailableStatus, Long> {

    UnavailableStatus getUnavailableStatusByDate(LocalDate date);
}
