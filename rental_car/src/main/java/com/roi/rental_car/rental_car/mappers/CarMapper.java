package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.CarDTO;
import com.roi.rental_car.rental_car.entities.Car;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CarMapper implements BaseMapper<Car, CarDTO> {
    @Override
    public CarDTO toDto(Car car) {
        if (car == null) {
            return null;
        }

        CarDTO carDTO = new CarDTO();
        carDTO.setCarId(car.getCarId());
        carDTO.setAmount(car.getAmount());
        carDTO.setBrand(car.getBrand());
        carDTO.setCarStatus(car.getCarStatus());
        carDTO.setColor(car.getColor());
        carDTO.setMilage(car.getMilage());
        carDTO.setBodyType(car.getBodyType());
        carDTO.setYear(car.getYear());
        return carDTO;
    }

    @Override
    public Car toEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setCarId(carDTO.getCarId());
        car.setColor(carDTO.getColor());
        car.setBrand(carDTO.getBrand());
        car.setMilage(carDTO.getMilage());
        car.setAmount(carDTO.getAmount());
        car.setYear(carDTO.getYear());
        car.setBodyType(carDTO.getBodyType());
        car.setCarStatus(carDTO.getCarStatus());
        return car;
    }

    @Override
    public List<CarDTO> toDtoList(List<Car> e) {
        if(e==null){return null;

        }
        List<CarDTO> cars = new ArrayList<>();
        for (Car car : e) {
            CarDTO carDTO = toDto(car);
            cars.add(carDTO);
        }
        return cars;

    }

    @Override
    public List<Car> toEntitity(List<CarDTO> d) {
        if (d==null){return null;}
        List<Car> cars1= new ArrayList<>();
        for (CarDTO car:d) {
            Car car1= toEntity(car);
            cars1.add(car1);
        }
        return cars1;
    }
}


