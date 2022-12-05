package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.RentalDTO;
import com.roi.rental_car.rental_car.services.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @GetMapping
    public RentalDTO getByid(Long id){return rentalService.getById(id);}
    @GetMapping("/all")
    public List<RentalDTO> getAll(){return rentalService.getAll();}
    @PostMapping
    public RentalDTO createRental(@RequestBody RentalDTO rentalDTO){
        return rentalService.createRental(rentalDTO);
    }
    @PutMapping
    public RentalDTO updateRental(@RequestBody  RentalDTO rentalDTO){
        return rentalService.updateRental(rentalDTO);
    }
    @DeleteMapping("/renta/{id}")
    public String deleteRental(@PathVariable Long id){
        return rentalService.deleteRental(id);
    }
}
