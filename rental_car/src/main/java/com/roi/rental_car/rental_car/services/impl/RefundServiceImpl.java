package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.RefundDTO;
import com.roi.rental_car.rental_car.dto.ReservationDTO;
import com.roi.rental_car.rental_car.entities.Refund;
import com.roi.rental_car.rental_car.entities.Reservation;
import com.roi.rental_car.rental_car.mappers.RefundMapper;
import com.roi.rental_car.rental_car.mappers.ReservationMapper;
import com.roi.rental_car.rental_car.repositories.ReservationRepo;
import com.roi.rental_car.rental_car.repositories.RrefundRepo;
import com.roi.rental_car.rental_car.services.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private RrefundRepo rrefundRepo;
    @Autowired
    private ReservationMapper reservationMapper ;
    @Autowired
    private ReservationRepo reservationRepo;
    @Override
    public RefundDTO getByid(Long id) {
        Refund refund = rrefundRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Refund with this id" + id + "does not exists"));
        RefundDTO refundDTO=refundMapper.toDto(refund);
        refundDTO=setAllValues(refund,refundDTO);
        return refundDTO;
    }

    @Override
    public List<RefundDTO> getAll() {
     List <RefundDTO> refundDTOList= new ArrayList<>();
     for (Refund refund: rrefundRepo.findAll()){
         RefundDTO refundDTO=refundMapper.toDto(refund);
         refundDTO=setAllValues(refund,refundDTO);
         refundDTOList.add(refundDTO);
     }

        return refundDTOList;
    }

    @Override
    public RefundDTO createRefund(RefundDTO refundDTO) {
        if(refundDTO.getRefundId()!= null) throw new RuntimeException("Id must be null");
        Refund refund=refundMapper.toEntity(refundDTO);
        if (refund.getReservation()!=null){
            Reservation reservation=reservationRepo.findById(refundDTO.getRefundId()).orElseThrow(()-> new RuntimeException("This reservation doesnt exista"));
            refund.setReservation(reservation);
        }
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
        RefundDTO refundDTO1=refundMapper.toDto(refund);
        refundDTO1=setAllValues(refund,refundDTO1);
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
    public RefundDTO setAllValues(Refund refund,RefundDTO refundDTO){
     if (refund.getReservation()!=null){
         refundDTO.setReservation(reservationMapper.toDto(refund.getReservation()));
     }
        return refundDTO;
    }
}
