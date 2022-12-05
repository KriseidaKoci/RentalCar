package com.roi.rental_car.rental_car.controllers;

import com.roi.rental_car.rental_car.dto.ReservationDTO;
import com.roi.rental_car.rental_car.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController{
 private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping
    public ReservationDTO getById(Long id){return reservationService.getById(id);}
    @GetMapping("/all")
    public List<ReservationDTO> getAll(){return reservationService.getAll();}
     @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO){
        return reservationService.createReservation(reservationDTO);
     }
     @PutMapping
    public  ReservationDTO updateReservation(@RequestBody ReservationDTO reservationDTO){
        return reservationService.updateReservation(reservationDTO);
     }
    @DeleteMapping("/reservation/{id}")
    public String deleteReservation(@PathVariable Long id){
        return reservationService.deleteReservation(id);
    }



}
