package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.RefundDTO;
import com.roi.rental_car.rental_car.entities.Refund;
import com.roi.rental_car.rental_car.mappers.RefundMapper;
import com.roi.rental_car.rental_car.repositories.RrefundRepo;
import com.roi.rental_car.rental_car.services.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private RrefundRepo rrefundRepo;

    @Override
    public RefundDTO getByid(Long id) {
        Refund refund = rrefundRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Refund with this id" + id + "does not exists"));
        return refundMapper.toDto(refund);
    }

    @Override
    public List<RefundDTO> getAll() {

        return refundMapper.toDtoList(rrefundRepo.findAll());
    }

    @Override
    public RefundDTO createRefund(RefundDTO refundDTO) {
        if(refundDTO.getRefundId()!= null) throw new RuntimeException("Id must be null");
        Refund refund=refundMapper.toEntity(refundDTO);
        rrefundRepo.save(refund);
        return refundMapper.toDto(refund);

    }

    @Override
    public RefundDTO updateRefund(RefundDTO refundDTO) {
        if(refundDTO.getRefundId()==null) throw new RuntimeException("Id must not be null");
        Refund refund=rrefundRepo.findById(refundDTO.getRefundId()).orElseThrow(
                ()-> new RuntimeException("Refund with this is" + refundDTO.getRefundId()+ "does not exists"));
        refund.setRefundCommens(refundDTO.getRefundCommens());
        refund.setRefund(refundDTO.getRefund());
        refund.setSurcharge(refundDTO.getSurcharge());
        refund.setReturnDate(refundDTO.getReturnDate());
        rrefundRepo.save(refund);
        return refundMapper.toDto(refund);
    }

    @Override
    public String deleteRefund(Long id) {
        try {
            if (rrefundRepo.findById(id).isEmpty())
                throw new RuntimeException("Refund with this id " + id + "does not exists");
            rrefundRepo.deleteById(id);
            return "Refund with this id" + id + "has been deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
