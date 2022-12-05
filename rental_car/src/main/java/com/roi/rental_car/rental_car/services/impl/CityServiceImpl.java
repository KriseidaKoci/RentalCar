package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.CityDTO;
import com.roi.rental_car.rental_car.entities.City;
import com.roi.rental_car.rental_car.mappers.CityMapper;
import com.roi.rental_car.rental_car.repositories.CityRepo;
import com.roi.rental_car.rental_car.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CityRepo cityRepo;
    @Override
    public CityDTO getById(String id) {
City city=cityRepo.findById(id).orElseThrow(
        ()->new RuntimeException("City with this id" + id+ "does not exists"));
      return cityMapper.toDto(city);
    }
    @Override
    public List<CityDTO> getAll() {
        return  cityMapper.toDtoList(cityRepo.findAll());
    }

    @Override
    public CityDTO updateCity(CityDTO cityDTO) {
     if (cityDTO.getName()==null) throw new RuntimeException("Id mus not be null");
     City city= cityRepo.findById(cityDTO.getName()).orElseThrow(()-> new RuntimeException("City with this id does not exists"));

        return cityDTO;
    }

    @Override
    public CityDTO createCity(CityDTO cityDTO) {
        if(cityDTO.getName()!= null) throw new RuntimeException("ID must be null");
        City city=cityMapper.toEntity(cityDTO);
        cityRepo.save(city);
            return  cityMapper.toDto(city);

    }


    @Override
    public String deleteCity(String id) {
        try {
            if(cityRepo.findById(id).isEmpty()) throw new RuntimeException("City with this id" + id+ "does not exists");
            cityRepo.deleteById(id);
            return "City with this id " + id + "has been deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
