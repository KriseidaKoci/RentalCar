package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.RentalDTO;
import com.roi.rental_car.rental_car.entities.Branch;
import com.roi.rental_car.rental_car.entities.Rental;
import com.roi.rental_car.rental_car.mappers.BranchMapper;
import com.roi.rental_car.rental_car.mappers.CustomerMapper;
import com.roi.rental_car.rental_car.mappers.RentalMapper;
import com.roi.rental_car.rental_car.mappers.ReservationMapper;
import com.roi.rental_car.rental_car.repositories.BranchRepo;
import com.roi.rental_car.rental_car.repositories.CustomerRepo;
import com.roi.rental_car.rental_car.repositories.RentalRepo;
import com.roi.rental_car.rental_car.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private RentalRepo rentalRepo;
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public RentalDTO getById(Long id) {
        Rental rental= rentalRepo.findById(id).orElseThrow(
                ()->new RuntimeException("Rental with this id "+ id +"does not exists"));
         RentalDTO rentalDTO=rentalMapper.toDto(rental);
         rentalDTO=setAllValues(rental,rentalDTO);
        return rentalDTO;
    }

    @Override
    public List<RentalDTO> getAll() {
        List<RentalDTO> rentalDTOList= new ArrayList<>();
        for (Rental rental:rentalRepo.findAll()){
            RentalDTO rentalDTO=rentalMapper.toDto(rental);
            rentalDTO=setAllValues(rental, rentalDTO);
            rentalDTOList.add(rentalDTO);
        }
        return rentalDTOList;
    }

    @Override
    public RentalDTO createRental(RentalDTO rentalDTO) {
        if(rentalDTO.getRentalId()!= null) throw new RuntimeException("Id must be null");
        Rental rental= rentalMapper.toEntity(rentalDTO);
        rentalDTO=setAllValues(rental,rentalDTO);
        rentalRepo.save(rentalMapper.toEntity(rentalDTO));
        return rentalDTO;
    }

    @Override
    public RentalDTO updateRental(RentalDTO rentalDTO) {
        if(rentalDTO.getRentalId()== null) throw new RuntimeException("ID MUST NOT BE NULL");
        Rental rental=rentalRepo.findById(rentalDTO.getRentalId()).orElseThrow(
                ()-> new RuntimeException("Rental with this id " + rentalDTO.getRentalId()+"does not exists"));
       rental.setInternetDomain(rentalDTO.getInternetDomain());
       rental.setContactAddress(rentalDTO.getContactAddress());
       rental.setName(rentalDTO.getName());
       rentalRepo.save(rental);
       RentalDTO rentalDTO1=rentalMapper.toDto(rental);
       rentalDTO1=setAllValues(rental,rentalDTO1);
       return rentalDTO1;
    }

    @Override
    public String deleteRental(Long id) {
try {
    if(rentalRepo.findById(id).isEmpty()) throw  new RuntimeException("Rental with this id" +id+ " does not exists");
    rentalRepo.deleteById(id);
    return "Rental with this id"+ id + "has been deleted";
}
catch (RuntimeException e) {
    return e.getMessage();
}
    }
    private RentalDTO setAllValues(Rental rental, RentalDTO rentalDTO){
        if (rental.getBranches()!=null)
        rentalDTO.setBranches(branchMapper.toDtoList(rental.getBranches()));
        if (rental.getCustomers()!=null)
            rentalDTO.setCustomers(customerMapper.toDtoList(rental.getCustomers()));


        return rentalDTO;
    }
}
