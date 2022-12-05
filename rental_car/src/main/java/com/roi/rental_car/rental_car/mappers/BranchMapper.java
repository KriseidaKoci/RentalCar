package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.BranchDTO;
import com.roi.rental_car.rental_car.entities.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BranchMapper implements  BaseMapper <Branch, BranchDTO>{

    @Override
    public BranchDTO toDto(Branch branch) {
        if (branch == null) return null;
        BranchDTO branchDTO= new BranchDTO();
        branchDTO.setBranchId(branch.getBranchId());
        branchDTO.setName(branchDTO.getName());
        return  branchDTO;
    }

    @Override
    public Branch toEntity(BranchDTO branchDTO) {
        if (branchDTO == null)return null;
        Branch branch=new Branch();
        branch.setBranchId(branchDTO.getBranchId());
        branch.setName(branchDTO.getName());
        return  branch;
    }

    @Override
    public List<BranchDTO> toDtoList(List<Branch> e) {
        if (e== null) return null;
        List<BranchDTO> branchDTO=new ArrayList<>();
        for (Branch branch: e) {
            BranchDTO branchDTO1=toDto(branch);
            branchDTO.add(branchDTO1);
        }
        return branchDTO;
    }

    @Override
    public List<Branch> toEntitity(List<BranchDTO> d) {
        if (d== null) return null;
        List <Branch> branches=new ArrayList<>();
        for (BranchDTO branchDTO: d){
            Branch branch=toEntity(branchDTO);
            branches.add(branch);
        }
        return  branches;
    }


}
