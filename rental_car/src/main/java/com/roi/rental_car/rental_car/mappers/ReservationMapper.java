package com.roi.rental_car.rental_car.mappers;

import com.roi.rental_car.rental_car.dto.ReservationDTO;
import com.roi.rental_car.rental_car.entities.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ReservationMapper implements BaseMapper <Reservation, ReservationDTO>{
    @Override
    public ReservationDTO toDto(Reservation reservation) {

        if (reservation == null) return null;
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setAmount(reservation.getAmount());
        reservationDTO.setReservationBooking(reservation.getReservationBooking());
        reservationDTO.setReservationEnd(reservation.getReservationEnd());
        return reservationDTO;
    }

    @Override
    public Reservation toEntity(ReservationDTO reservationDTO)
    {
        if (reservationDTO == null) return null;
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationDTO.getReservationId());
        reservation.setAmount(reservationDTO.getAmount());
        reservation.setReservationBooking(reservationDTO.getReservationBooking());
        reservation.setReservationEnd(reservationDTO.getReservationEnd());
        return reservation;
    }

    @Override
    public List<ReservationDTO> toDtoList(List<Reservation> e) {
        if(e==null)return null;
        List<ReservationDTO> reservationDTOList= new ArrayList<>();
        for (Reservation reservation:e){
            ReservationDTO reservationDTO=toDto(reservation);
            reservationDTOList.add(reservationDTO);
        }
        return reservationDTOList;
    }

    @Override
    public List<Reservation> toEntitity(List<ReservationDTO> d) {
        if(d==null)return null;
        List<Reservation> reservationList=new ArrayList<>();
        for(ReservationDTO reservationDTO:d){
            Reservation reservation=toEntity(reservationDTO);
            reservationList.add(reservation);
        }
        return reservationList;
    }
}
