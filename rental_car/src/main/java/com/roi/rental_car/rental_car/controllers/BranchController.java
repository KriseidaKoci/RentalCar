package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.BranchDTO;
import com.roi.rental_car.rental_car.services.BranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }
    @GetMapping
    public BranchDTO getByID(@RequestParam Long id){
        return branchService.getById(id);
    }
    @GetMapping("/all")
    public List<BranchDTO> getAll(){
        return branchService.getAll();}
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        return branchService.deleteById(id);
    }
    @PutMapping("/")
    public BranchDTO updateBranch(@RequestBody BranchDTO branchDTO){
        return branchService.updateBranch(branchDTO); //mvc jo ne json po me model
    }

    @PostMapping
    public BranchDTO createBranch(@RequestBody BranchDTO branchDTO){
        return branchService.createBranch(branchDTO);
    }

}
