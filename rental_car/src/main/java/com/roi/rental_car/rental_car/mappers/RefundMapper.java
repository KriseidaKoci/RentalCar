package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.RefundDTO;
import com.roi.rental_car.rental_car.entities.Refund;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component

public class RefundMapper implements BaseMapper <Refund, RefundDTO>{
    @Override
    public RefundDTO toDto(Refund refund) {
        if (refund == null) return null;
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setRefundId(refund.getRefundId());
        refundDTO.setRefund(refund.getRefund());
        refundDTO.setRefundCommens(refund.getRefundCommens());
        refundDTO.setSurcharge(refund.getSurcharge());
        refundDTO.setReturnDate(refund.getReturnDate());
        return refundDTO;
    }

    @Override
    public Refund toEntity(RefundDTO refundDTO) {

        if (refundDTO == null) return null;
        Refund refund = new Refund();
        refund.setRefundId(refundDTO.getRefundId());
        refund.setRefund(refundDTO.getRefund());
        refund.setRefundCommens(refundDTO.getRefundCommens());
        refund.setSurcharge(refundDTO.getSurcharge());
        refund.setReturnDate(refundDTO.getReturnDate());
        return refund;
    }

    @Override
    public List<RefundDTO> toDtoList(List<Refund> e) {

        if (e==null)return null;
        List<RefundDTO> refundDTOList= new ArrayList<>();
        for(Refund refund:e){
            RefundDTO refundDTO=toDto(refund);
            refundDTOList.add(refundDTO);
        }
        return refundDTOList;
    }

    @Override
    public List<Refund> toEntitity(List<RefundDTO> d) {
        if(d==null)return null;
        List<Refund> refundList= new ArrayList<>();
        for(RefundDTO refundDTO:d){
            Refund refund=toEntity(refundDTO);
            refundList.add(refund);
        }
        return refundList;
    }
}
