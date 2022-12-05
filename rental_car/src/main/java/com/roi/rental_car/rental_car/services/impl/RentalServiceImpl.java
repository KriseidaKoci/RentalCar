package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.RentalDTO;
import com.roi.rental_car.rental_car.entities.Rental;
import com.roi.rental_car.rental_car.mappers.RentalMapper;
import com.roi.rental_car.rental_car.repositories.RentalRepo;
import com.roi.rental_car.rental_car.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private RentalRepo rentalRepo;
    @Override
    public RentalDTO getById(Long id) {
        Rental rental= rentalRepo.findById(id).orElseThrow(
                ()->new RuntimeException("Rental with this id "+ id +"does not exists"));

        return rentalMapper.toDto(rental);
    }

    @Override
    public List<RentalDTO> getAll() {
        return rentalMapper.toDtoList(rentalRepo.findAll());
    }

    @Override
    public RentalDTO createRental(RentalDTO rentalDTO) {
        if(rentalDTO.getRentalId()!= null) throw new RuntimeException("Id must be null");
        Rental rental= rentalMapper.toEntity(rentalDTO);
        rentalRepo.save(rental);
        return rentalMapper.toDto(rental);
    }

    @Override
    public RentalDTO updateRental(RentalDTO rentalDTO) {
        if(rentalDTO.getRentalId()== null) throw new RuntimeException("ID MUST NOT BE NULL");
        Rental rental=rentalRepo.findById(rentalDTO.getRentalId()).orElseThrow(
                ()-> new RuntimeException("Rental with this id " + rentalDTO.getRentalId()+"does not exists"));
        if(!rental.getName().toUpperCase().equals(rentalDTO.getName().toUpperCase()) || rental.getInternetDomain().toUpperCase().equals(rentalDTO.getInternetDomain().toUpperCase()))
        { rental.setName(rentalDTO.getName());
        rental.setContactAddress(rentalDTO.getContactAddress());
        rental.setInternetDomain(rentalDTO.getInternetDomain());}
        rentalRepo.save(rental);
        return rentalMapper.toDto(rental);
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
}
