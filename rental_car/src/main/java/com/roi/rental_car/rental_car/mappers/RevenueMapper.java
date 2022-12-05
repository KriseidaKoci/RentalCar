package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.RevenueDTO;
import com.roi.rental_car.rental_car.entities.Revenue;

import java.util.ArrayList;
import java.util.List;

public class RevenueMapper implements  BaseMapper <Revenue, RevenueDTO>{
    @Override
    public RevenueDTO toDto(Revenue revenue) {

        if (revenue == null) return null;
        RevenueDTO revenueDTO = new RevenueDTO();
        revenueDTO.setRevenueId(revenue.getRevenueId());
        revenueDTO.setAmount(revenue.getAmount());
        revenueDTO.setMonth(revenue.getMonth());
        return revenueDTO;
    }

    @Override
    public Revenue toEntity(RevenueDTO revenueDTO) {
        if (revenueDTO == null) return null;
        Revenue revenue = new Revenue();
        revenue.setRevenueId(revenueDTO.getRevenueId());
        revenue.setAmount(revenueDTO.getAmount());
        revenue.setMonth(revenueDTO.getMonth());
        return revenue;
    }

    @Override
    public List<RevenueDTO> toDtoList(List<Revenue> e) {
        if (e == null) return null;
        List<RevenueDTO> revenueDTOList=new ArrayList<>();
        for(Revenue revenue:e){
            RevenueDTO revenueDTO=toDto(revenue);
            revenueDTOList.add(revenueDTO);}
        return revenueDTOList;
        }
    @Override
    public List<Revenue> toEntitity(List<RevenueDTO> d) {
        if (d == null) return null;
        List<Revenue> revenueList= new ArrayList<>();
        for(RevenueDTO revenueDTO:d){
            Revenue revenue=toEntity(revenueDTO);
            revenueList.add(revenue);
        }
        return revenueList;
    }
}
