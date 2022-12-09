package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.BranchDTO;
import com.roi.rental_car.rental_car.entities.Branch;

import java.util.List;

public interface BranchService {
    BranchDTO getById(Long id);
    List<BranchDTO> getAll();
    void setWarning(Branch branch, BranchDTO branchDTO);
    BranchDTO createBranch(BranchDTO branchDTO);
    BranchDTO updateBranch (BranchDTO branchDTO);
    String deleteById(Long id);

}