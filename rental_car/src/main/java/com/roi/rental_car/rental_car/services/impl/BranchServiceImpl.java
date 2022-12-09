package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.BranchDTO;
import com.roi.rental_car.rental_car.entities.Branch;
import com.roi.rental_car.rental_car.entities.Car;
import com.roi.rental_car.rental_car.entities.City;
import com.roi.rental_car.rental_car.entities.Rental;
import com.roi.rental_car.rental_car.mappers.*;
import com.roi.rental_car.rental_car.repositories.BranchRepo;
import com.roi.rental_car.rental_car.repositories.CityRepo;
import com.roi.rental_car.rental_car.repositories.RentalRepo;
import com.roi.rental_car.rental_car.services.BranchService;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import org.aspectj.weaver.patterns.IfPointcut;
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
   @Autowired
   private CityRepo cityRepo;

   @Autowired
   private RentalRepo rentalRepo;
   @Autowired
   private CarMapper carMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private CityMapper cityMapper;

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
        if (branchDTO.getBranchId()!=null) throw new RuntimeException("id must be null");
        if (branchRepo.existsBranchByBranchId(branchDTO.getBranchId())){
            throw new RuntimeException("Branch with this id already exists");
    }
        Branch branch = branchMapper.toEntity(branchDTO);
        if (branchDTO.getCity() != null) {
            City city = cityRepo.findById(branchDTO.getCity().getName()).
                    orElseThrow(() -> new RuntimeException("City with this id does not exists"));
            branch.setCity(city);
        }
        if (branchDTO.getRental() != null) {
            Rental rental = rentalRepo.findById(branchDTO.getRental().getRentalId()).
                    orElseThrow(() -> new RuntimeException("Rental with this id does not exists"));
            branch.setRental(rental);
        }
        branch = branchRepo.save(branch);
        return branchMapper.toDto(branch);
    }


    @Override
    public BranchDTO updateBranch(BranchDTO branchDTO) {
        if (branchDTO.getBranchId() == null) throw new RuntimeException("id must not be null");
        Branch branch = branchRepo.findById(branchDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch with id" + branchDTO.getBranchId() + "does not exists"));
        if (!branch.getName().toUpperCase().equals(branchDTO.getName().toUpperCase()) && branchRepo.existsBranchByNameIgnoreCase(branchDTO.getName()))
            throw new RuntimeException("Branch with name" + branchDTO.getName() + "already exists");
        branch.setName(branchDTO.getName());
        if (branchDTO.getCity()!=null){
            City city=cityRepo.findById(branchDTO.getCity().getName()).
                    orElseThrow(() -> new RuntimeException("This city does not exists"));
            branch.setCity(city);
        }
        if (branchDTO.getRental()!=null){
            Rental rental=rentalRepo.findById(branchDTO.getRental().getRentalId()).orElseThrow(
                    () -> new RuntimeException("This rental does not exists"));
            branch.setRental(rental);
        }
        branchRepo.save(branch);
        return branchMapper.toDto(branch);
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
    private BranchDTO setOtherValues(Branch branch, BranchDTO branchDTO) {
    if (!branch.getCars().isEmpty())
        branchDTO.setCars(carMapper.toDtoList(branch.getCars()));
    if(branch.getRental()!= null)
        branchDTO.setRental(rentalMapper.toDto(branch.getRental()));
    if (!branch.getEmployees().isEmpty())
        branchDTO.setEmployees(employeeMapper.toDtoList(branch.getEmployees()));
    if (branch.getCity()!=null)
        branchDTO.setCity(cityMapper.toDto(branch.getCity()));
        return branchDTO;
    }

}


