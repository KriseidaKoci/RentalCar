package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.ReservationDTO;
import com.roi.rental_car.rental_car.entities.Car;
import com.roi.rental_car.rental_car.entities.Refund;
import com.roi.rental_car.rental_car.entities.Reservation;
import com.roi.rental_car.rental_car.mappers.CarMapper;
import com.roi.rental_car.rental_car.mappers.ReservationMapper;
import com.roi.rental_car.rental_car.repositories.CarRepo;
import com.roi.rental_car.rental_car.repositories.ReservationRepo;
import com.roi.rental_car.rental_car.repositories.RrefundRepo;
import com.roi.rental_car.rental_car.services.RefundService;
import com.roi.rental_car.rental_car.services.ReservationService;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private RrefundRepo rrefundRepo;

    @Override
    public ReservationDTO getById(Long id) {
        Reservation reservation = reservationRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Resevation with this id " + id + "does not exists"));
        return reservationMapper.toDto(reservation);
    }

    @Override
    public List<ReservationDTO> getAll() {
        return reservationMapper.toDtoList(reservationRepo.findAll());
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        if(reservationDTO.getReservationId()!=null) throw new RuntimeException("Id must be null");
        Reservation reservation= reservationMapper.toEntity(reservationDTO);
        List<Car> carList=carMapper.toEntitity(reservationDTO.getCars());
        reservation= reservationRepo.save(reservation);
        for(Car car:carList){
            car.setReservation(reservation);
            carRepo.save(car);
        }
        ReservationDTO reservationDTO1=reservationMapper.toDto(reservation);
        return reservationMapper.toDto(reservation);
    }

    @Override
    public ReservationDTO updateReservation(ReservationDTO reservationDTO) {
        return null;
    }

    @Override
    public String deleteReservation(Long id) {
        try {
            if (reservationRepo.findById(id).isEmpty())
                throw new RuntimeException("Reservation with this id" + id + "does not exists");
            reservationRepo.deleteById(id);
            return "Reservation wit id" + id + "has been deleted";
        } catch (Exception e) {
            return e.getMessage();

        }

    }
    private void cancleReservation(Long id){
        Optional<Reservation> reservationOptional= reservationRepo.findById(id);
        Reservation reservation=reservationOptional.get();
        if(reservation!=null){
            if(reservation.getReservationStart().isAfter(LocalDate.now().plusDays(2)))
            {
                Refund refund= new Refund();
                refund.setReservation(reservation);
                refund.setRefund(reservation.getAmount()*0.8);
                refund.setSurcharge(reservation.getAmount()*0.2);
                rrefundRepo.save(refund);
               List <Car> carList=carRepo.findAll();
               for(Car car: carList){
                   car.setCarStatus(CarStatus.AVAILABLE);
                   car.setReservation(null);
                   carRepo.save(car);
               }
            }
        }
    }

}
