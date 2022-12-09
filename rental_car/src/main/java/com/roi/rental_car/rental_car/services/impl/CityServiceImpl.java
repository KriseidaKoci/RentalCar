package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.CityDTO;
import com.roi.rental_car.rental_car.entities.Branch;
import com.roi.rental_car.rental_car.entities.City;
import com.roi.rental_car.rental_car.mappers.BranchMapper;
import com.roi.rental_car.rental_car.mappers.CityMapper;
import com.roi.rental_car.rental_car.repositories.BranchRepo;
import com.roi.rental_car.rental_car.repositories.CityRepo;
import com.roi.rental_car.rental_car.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired

    private BranchMapper branchMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private BranchRepo branchRepo;

    @Override
    public CityDTO getById(String id) {
        City city = cityRepo.findById(id).orElseThrow(
                () -> new RuntimeException("City with this id" + id + "does not exists"));
        CityDTO cityDTO= cityMapper.toDto(city);
        cityDTO=setOtherValues(city,cityDTO);
        return cityDTO;
    }

    @Override
    public List<CityDTO> getAll() {
        List<CityDTO> cityDTOList=new ArrayList<>();
        for (City city: cityRepo.findAll()){
            CityDTO cityDTO=cityMapper.toDto(city);
            cityDTO=setOtherValues(city,cityDTO);
            cityDTOList.add(cityDTO);
        }
        return cityDTOList;
    }

    @Override
    public CityDTO updateCity(CityDTO cityDTO) {
        if (cityDTO.getName() == null) throw new RuntimeException("Id must not be null");
        City city = cityRepo.findById(cityDTO.getName()).orElseThrow(() -> new RuntimeException("City with this id does not exists"));
        cityDTO=setOtherValues(city,cityDTO);
        cityRepo.save(cityMapper.toEntity(cityDTO));
        return cityDTO;
    }

    @Override
    public CityDTO createCity(CityDTO cityDTO) {
        if (cityDTO.getName() != null) throw new RuntimeException("ID must be null");
        if (cityRepo.existsCityByNameIgnoreCase(cityDTO.getName())) throw new RuntimeException("City with this id already exists");
        City city = cityMapper.toEntity(cityDTO);
        cityRepo.save(city);
        return cityMapper.toDto(city);

    }


    @Override
    public String deleteCity(String id) {
        try {
            if (cityRepo.findById(id).isEmpty())
                throw new RuntimeException("City with this id" + id + "does not exists");
            cityRepo.deleteById(id);
            return "City with this id " + id + "has been deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private CityDTO setOtherValues(City city, CityDTO cityDTO) {
        if (!city.getBranches().isEmpty()) {
            if (city.getBranches() != null)
                cityDTO.setBranches(branchMapper.toDtoList(city.getBranches()));
        }
        return cityDTO;
    }
}