package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.CityDTO;
import com.roi.rental_car.rental_car.entities.City;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CityMapper implements  BaseMapper <City, CityDTO>{
    @Override
    public CityDTO toDto(City city) {
        if (city==null )return null;
        CityDTO cityDTO=new CityDTO();
        cityDTO.setName(city.getName());
        return  cityDTO;
    }

    @Override
    public City toEntity(CityDTO cityDTO) {
        if (cityDTO==null)return null;
        City city= new City();
        city.setName(cityDTO.getName());
        return city;
    }

    @Override
    public List<CityDTO> toDtoList(List<City> e) {
       if(e==null) return null;
        List<CityDTO> cityDTOList=new ArrayList<>();
        for(City city:e){
            CityDTO cityDTO=toDto(city);
            cityDTOList.add(cityDTO);
        }
        return  cityDTOList;
    }

    @Override
    public List<City> toEntitity(List<CityDTO> d) {
        if (d==null)return null;
        List<City>cities=new ArrayList<>();
        for(CityDTO cityDTO:d){
            City city=toEntity(cityDTO);
            cities.add(city);
        }
        return cities;
    }
}
