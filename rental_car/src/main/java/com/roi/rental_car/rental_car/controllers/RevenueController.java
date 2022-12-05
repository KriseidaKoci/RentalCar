package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.RevenueDTO;
import com.roi.rental_car.rental_car.services.RevenueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("revenue")
public class RevenueController {
    private final RevenueService revenueService;
    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }
    @GetMapping
    public RevenueDTO getByID(Long id){return revenueService.getById(id);}
    @GetMapping("/all")
    public List<RevenueDTO> getAll(){return revenueService.getAll();}
    @PostMapping
    public RevenueDTO createRevenue(@RequestBody RevenueDTO revenueDTO){
        return revenueService.createRevenue(revenueDTO);
    }
    @PutMapping
    public RevenueDTO updateRevenue(@RequestBody RevenueDTO revenueDTO){
        return revenueService.updateRevenue(revenueDTO);
    }
    @DeleteMapping("/revenue/{id}")
    public String deleteRevenue(@PathVariable Long id){
        return revenueService.deleteRevenue(id);
    }


}
