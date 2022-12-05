package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.CarDTO;
import com.roi.rental_car.rental_car.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping
    public CarDTO getByID(@RequestParam Long id) {
        return carService.getById(id);
    }
    @GetMapping("/all")
    public List<CarDTO> getAll(){return carService.getAll();}
     @PostMapping("/")
    public CarDTO createCar(@RequestBody CarDTO carDTO){
        return carService.createCar(carDTO);
     }
     @PutMapping
    public CarDTO updateCar(@RequestBody CarDTO carDTO){
        return carService.updateCar(carDTO);
     }

     @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(name="id") Long carId){
        return carService.deleteCar(carId);
     }














}
