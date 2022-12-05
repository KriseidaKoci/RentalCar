package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.BranchDTO;
import com.roi.rental_car.rental_car.dto.CityDTO;
import com.roi.rental_car.rental_car.services.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService){
        this.cityService=cityService;
    }

   @GetMapping("/all")
    public List<CityDTO> getAll(){return cityService.getAll();}
    @GetMapping
    public CityDTO getById(@RequestParam String id){
        return cityService.getById(id);
    }
    @PostMapping
    public CityDTO createCity(@RequestBody CityDTO cityDTO){
        return cityService.createCity(cityDTO);
    }
    @PutMapping
    public CityDTO updateCity(@RequestBody CityDTO cityDTO){
        return cityService.updateCity(cityDTO);
    }
    @DeleteMapping("/city/{id}")
    public String deleteCity(@PathVariable String id){
        return cityService.deleteCity(id);
    }

}
