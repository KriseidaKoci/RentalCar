package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface CityRepo extends JpaRepository<City, String> {
    City getCityByName (String name);
    boolean existsCityByNameIgnoreCase(String name);
    City findCityByName(String name);

}
