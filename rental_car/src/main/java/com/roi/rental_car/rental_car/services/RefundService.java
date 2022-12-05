package com.roi.rental_car.rental_car.services;

import com.roi.rental_car.rental_car.dto.RefundDTO;

import java.sql.Ref;
import java.util.List;

public interface RefundService {
    RefundDTO getByid(Long id);
    List<RefundDTO> getAll();
    RefundDTO createRefund(RefundDTO refundDTO);
    RefundDTO updateRefund(RefundDTO refundDTO);
    String deleteRefund(Long id);

}
