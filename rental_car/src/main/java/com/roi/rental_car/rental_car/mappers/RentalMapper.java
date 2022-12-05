package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.RentalDTO;
import com.roi.rental_car.rental_car.entities.Rental;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component

public class RentalMapper implements  BaseMapper<Rental, RentalDTO>{
    @Override
    public RentalDTO toDto(Rental rental){
if (rental == null) return null;
    RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setRentalId(rental.getRentalId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setInternetDomain(rental.getInternetDomain());
        rentalDTO.setContactAddress(rental.getContactAddress());
        return rentalDTO;
    }

    @Override
    public Rental toEntity(RentalDTO rentalDTO) {
        if (rentalDTO == null) return null;
        Rental rental = new Rental();
        rental.setRentalId(rentalDTO.getRentalId());
        rental.setName(rentalDTO.getName());
        rental.setInternetDomain(rentalDTO.getInternetDomain());
        rental.setContactAddress(rentalDTO.getContactAddress());
        return rental;
    }

    @Override
    public List<RentalDTO> toDtoList(List<Rental> e) {
      if(e==null) return null;
        List<RentalDTO> rentalDTOList= new ArrayList<>();
        for( Rental rental:e){
            RentalDTO rentalDTO=toDto(rental);
            rentalDTOList.add(rentalDTO);
        }
        return rentalDTOList;
    }

    @Override
    public List<Rental> toEntitity(List<RentalDTO> d) {
        if (d==null)return null;
        List<Rental> rentals= new ArrayList<>();
        for(RentalDTO rentalDTO:d){
            Rental rental=toEntity(rentalDTO);
            rentals.add(rental);
        }
        return rentals;
    }
}
