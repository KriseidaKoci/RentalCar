package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.UnavailableStatusDTO;
import com.roi.rental_car.rental_car.services.UnavailableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/unavailable status")
public class UnavailableStatusController {
    private final UnavailableService unavailableService;
    public UnavailableStatusController(UnavailableService unavailableService) {
        this.unavailableService = unavailableService;
    }
    @GetMapping
    public UnavailableStatusDTO getById(Long id ){return unavailableService.getById(id);}
    @GetMapping("/all")
    List<UnavailableStatusDTO> getAll(){return  unavailableService.getAll();}
    @PostMapping
    public UnavailableStatusDTO createUnavailableStatus(@RequestBody UnavailableStatusDTO unavailableStatusDTO){
        return unavailableService.createUnavailableStatus(unavailableStatusDTO);
    }
    @PutMapping
    public UnavailableStatusDTO updateUnavailableStatus(@RequestBody UnavailableStatusDTO unavailableStatusDTO){
        return unavailableService.updateUnavailableStatus(unavailableStatusDTO);    }
    @DeleteMapping("//unavailable status/{id}")
    public String deleteUnavailableStatus(@PathVariable Long id){
        return unavailableService.deleteUnavailableStatus(id);
    }
}
