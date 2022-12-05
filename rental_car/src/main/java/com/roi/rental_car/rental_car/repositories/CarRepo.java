package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.Car;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CarRepo  extends JpaRepository<Car, Long> {
    Car getCarByCarId(Long id);
    Car getByBodyType(String bodyType);
    Car getByYear(Integer year);
    Car getCarByColor( String color);
    Car getByMilage( Double mileage);
    Car getByAmount( Double amount);
    boolean existsCarByCarId(Long id);
    Car findCarByCarId(Long id);

    @Query("from Car car where car.carStatus <> :status")
    List<Car> getAllByStatusNot(CarStatus status);



}
