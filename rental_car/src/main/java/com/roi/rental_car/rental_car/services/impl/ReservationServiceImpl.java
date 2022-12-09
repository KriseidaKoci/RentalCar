package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.ReservationDTO;
import com.roi.rental_car.rental_car.entities.*;
import com.roi.rental_car.rental_car.mappers.*;
import com.roi.rental_car.rental_car.repositories.*;
import com.roi.rental_car.rental_car.services.RefundService;
import com.roi.rental_car.rental_car.services.ReservationService;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public ReservationDTO getById(Long id) {
        Reservation reservation = reservationRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Resevation with this id " + id + "does not exists"));
        ReservationDTO reservationDTO=reservationMapper.toDto(reservation);
        reservationDTO=setAllValues(reservation,reservationDTO);

        return reservationDTO;
    }

    @Override
    public List<ReservationDTO> getAll() {
        List <ReservationDTO> reservationDTOList=new ArrayList<>();
        for (Reservation reservation: reservationRepo.findAll()){
            ReservationDTO reservationDTO= reservationMapper.toDto(reservation);
            reservationDTO=setAllValues(reservation,reservationDTO);
            reservationDTOList.add(reservationDTO);
        }
        return reservationDTOList;
    }


    @Override
    public ReservationDTO create(ReservationDTO dto) {
        if (dto.getReservationId() != null)
            throw new RuntimeException("Id must be null");
        else {
            Reservation reservation = reservationMapper.toEntity(dto);
            reservation.setReservationBooking(LocalDate.now());
            List<Car> cars = carMapper.toEntitity(dto.getCars());
            reservation.setAmount(dto.getAmount());
            reservation = reservationRepo.save(reservation);
            for (Car car: cars) {
                car.setReservation(reservation);
                carRepo.save(car);
            }
            ReservationDTO reservationDTO = reservationMapper.toDto(reservation);
            reservationDTO.setCars(dto.getCars());
            return reservationDTO;
        }
    }


    @Override
    public ReservationDTO updateReservation(ReservationDTO reservationDTO) {
        if (reservationDTO.getReservationId()==null) throw  new RuntimeException("ID must not be null");
        Reservation reservation=reservationRepo.findById(reservationDTO.getReservationId()).orElseThrow(()->new RuntimeException("This reservation doesnt exists"));
        reservation.setReservationBooking(reservationDTO.getReservationBooking());
        reservation.setReservationStart(reservationDTO.getReservationStart());
        reservation.setReservationEnd(reservationDTO.getReservationEnd());
        reservation.setAmount(reservationDTO.getAmount());
        if (reservationDTO.getBookingbranch()!=null){
            Branch branch=branchRepo.findById(reservationDTO.getBookingbranch().getBranchId()).orElseThrow(()->new RuntimeException("This branch doesnt exists"));
            reservation.setBookingbranch(branch);
        }
        if (reservation.getReturnbrench()!=null){
            Branch branch=branchRepo.findById(reservationDTO.getReturnbrench().getBranchId()).orElseThrow(()->new RuntimeException("This branch doesnt exists"));
        reservation.setReturnbrench(branch);
        }
        if (reservation.getCustomer()!=null){
            Customer customer=customerRepo.findById(reservationDTO.getCustomer().getCustomerId()).orElseThrow(()->new RuntimeException("This customer doesnt exists"));
       reservation.setCustomer(customer);
        }
        if (reservation.getRefund()!=null){
            Refund refund=rrefundRepo.findById(reservation.getRefund().getRefundId()).orElseThrow(()->new RuntimeException("This refund doesnt exists"));
            reservation.setRefund(refund);
        }
      reservationRepo.save(reservation);
        return reservationMapper.toDto(reservation);
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

    @Override
    public String returnCar(Long id, LocalDate returndate, String status, Long carId) {
        Double refund = null;
        Double sourchange = null;
        Double moneytopay = null;

        Reservation reservation = reservationRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Rezervation is not found"));
        if (reservation.getReservationEnd().isAfter(returndate)) {
            Long days = ChronoUnit.DAYS.between(returndate, reservation.getReservationEnd());
            refund = 0.05 * reservation.getAmount() * days;
        } else if (returndate.isBefore(reservation.getReservationEnd())) {
            Long days = ChronoUnit.DAYS.between(returndate, reservation.getReservationEnd());
            sourchange = 0.05 * reservation.getAmount() * days;
        }
        if (status != null && CarStatus.valueOf(status).equals(CarStatus.DEMAGED)){
            for (Car car : reservation.getCars()) {
                if (car.getCarId() == carId) {
                    car.setCarStatus(CarStatus.DEMAGED);
                    moneytopay = 0.5 * car.getAmount();
                }
            }
        }
        if (refund != null && moneytopay != null) {
            refund = refund - moneytopay;
        }
        String response = null;
        if (sourchange != null && moneytopay != null)
            sourchange = sourchange - moneytopay;
        if (refund != null)
            response = "You have to get back".concat(refund.toString());
        else if (refund != null && refund < 0)
            response = "You have to pay ".concat(refund.toString());
        else if (sourchange != null)
            response = "You have to pay ".concat(sourchange.toString());
        else if (moneytopay != null)
            response = "You have to pay".concat(moneytopay.toString());
        return response;
    }


    private Double calculateAmount(LocalDate reservationStart, LocalDate reservationEnd, Long id) {
        Double amount = 0.0;
        Optional<Car> carOptional = carRepo.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            if (car.getReservation() != null) {
                long totalDays = ChronoUnit.DAYS.between(reservationStart, reservationEnd);
                amount = totalDays * car.getAmount();
                if (car.getReservation().getBookingbranch() != car.getReservation().getReturnbrench()) {
                    amount = amount + (0.1 * amount);
                }

            }
        }
        return amount;
    }
    public String cancelReservation(Long id) {
        Reservation reservation = reservationRepo.findById(id).orElseThrow(()->
                new RuntimeException("Id has not been found"));
        if (reservation.getReservationStart().isBefore(LocalDate.now().plusDays(2))){
            Refund refund = new Refund();
            refund.setReservation(reservation);
            refund.setRefund(reservation.getAmount() * 0.8);
            refund.setSurcharge(reservation.getAmount() * 0.2);
            rrefundRepo.save(refund);
            List<Car> cars = reservation.getCars();
            for (Car car: cars) {
                car.setCarStatus(CarStatus.AVAILABLE);
                car.setReservation(null);
                carRepo.save(car);
            }
            return "Reservation has been canceled";

        } else {
            return "Reservation must be canceled only 2 days before starting";
        }
    }
    private ReservationDTO setAllValues(Reservation reservation, ReservationDTO reservationDTO){
        if (reservation.getBookingbranch()!=null)
            reservationDTO.setBookingbranch(branchMapper.toDto(reservation.getBookingbranch()));
        if (reservation.getReturnbrench()!=null)
            reservationDTO.setReturnbrench(branchMapper.toDto(reservation.getReturnbrench()));
        if (!reservation.getCars().isEmpty())
            reservationDTO.setCars(carMapper.toDtoList(reservation.getCars()));
        if (reservation.getCustomer()!=null)
            reservationDTO.setCustomer( customerMapper.toDto(reservation.getCustomer()));
        if (reservation.getRefund()!=null)
            reservationDTO.setRefund(refundMapper.toDto(reservation.getRefund()));
        return reservationDTO;
    }
}
