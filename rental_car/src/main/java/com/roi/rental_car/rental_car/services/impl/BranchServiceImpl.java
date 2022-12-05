package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.BranchDTO;
import com.roi.rental_car.rental_car.entities.Branch;
import com.roi.rental_car.rental_car.entities.Car;
import com.roi.rental_car.rental_car.mappers.BranchMapper;
import com.roi.rental_car.rental_car.repositories.BranchRepo;
import com.roi.rental_car.rental_car.services.BranchService;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private BranchRepo branchRepo;

    @Override
    public BranchDTO getById(Long id) {
        Optional<Branch> optionalBranch = branchRepo.findById(id);
        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch.get();
            BranchDTO branchDTO = branchMapper.toDto(branch);
            if (branch.getCars() != null && !branch.getCars().isEmpty()) {
                int available = 0;
                for (Car car : branch.getCars()) {
                    if (car.getCarStatus().equals(CarStatus.AVAILABLE))
                        available++;
                }
                if (available < 3)
                    branchDTO.setWarning("Warning! Lees than 2 cars are available today");
            }
            branchDTO.setWarning("Warning,less than 2 cars are available today");
            return branchDTO;
        } else throw new RuntimeException("Branch with this id" + id.toString() + "does not exist.");
    }

    @Override
    public List<BranchDTO> getAll() {
        List<Branch> branches = branchRepo.findAll();
        return branchMapper.toDtoList(branches);
    }


    @Override
    public void setWarning(Branch branch, BranchDTO branchDTO) {
        if (branch.getCars() != null && !branch.getCars().isEmpty()) {
            int available = 0;
            for (Car car : branch.getCars()) {
                if (car.getCarStatus().equals(CarStatus.AVAILABLE))
                    available++;
            }
            if (available < 3)
                branchDTO.setWarning("Warning! Lees than 2 cars are available today");
        }
    }

    @Override

    public BranchDTO createBranch(BranchDTO branchDTO) {
        if (branchRepo.existsBranchByName(branchDTO.getName())) {
            throw new RuntimeException("Branch with name " + branchDTO.getName() + " already exists");
        }
        Branch branch = branchMapper.toEntity(branchDTO);
        branch = branchRepo.save(branch);
        return branchMapper.toDto(branch);
    }

    @Override
    public BranchDTO updateBranch(BranchDTO branchDTO) {
        if (branchDTO.getBranchId() == null) throw new RuntimeException("id must not be null");
        Branch branch = branchRepo.findById(branchDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch with id" + branchDTO.getBranchId() + "does not exists"));
        if (branch.getName().toUpperCase().equals(branchDTO.getName().toUpperCase()) && branchRepo.existsBranchByNameIgnoreCase(branchDTO.getName()))
            throw new RuntimeException("Branch with name" + branchDTO.getName() + "already exists");

        branch.setName(branchDTO.getName());
        branchRepo.save(branch);
        return branchMapper.toDto(branch);
    }

    @Override
    public void deleteBranch(Long id) {
        branchRepo.deleteById(id);

    }

    @Override
    public String deleteById(Long id) {
        try {
            if (branchRepo.findById(id).isEmpty())
                return "Branch with id  " + id + " does not exist";
            branchRepo.deleteById(id);
            return "Branch with" + id + "has been deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}


