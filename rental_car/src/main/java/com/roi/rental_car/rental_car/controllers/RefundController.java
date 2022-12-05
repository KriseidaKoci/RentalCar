package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.RefundDTO;
import com.roi.rental_car.rental_car.services.RefundService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refund")
public class RefundController {
    private final RefundService refundService;
    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }
    @GetMapping
    public RefundDTO getByid(@RequestParam Long id){return  refundService.getByid(id);}
    @GetMapping("/all")
    public List<RefundDTO> getAll() {return  refundService.getAll();}
    @PostMapping
    public RefundDTO createRefund(@RequestBody RefundDTO refundDTO){
        return refundService.createRefund(refundDTO);
    }
    @PutMapping
    public RefundDTO updateRefund(@RequestParam RefundDTO refundDTO){
        return refundService.updateRefund(refundDTO);
    }
    @DeleteMapping("/refund/{id}")
    public  String deleteRefund(@PathVariable Long id){
        return refundService.deleteRefund(id);
    }
}
