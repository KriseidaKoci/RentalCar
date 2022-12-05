package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.CarDTO;
import com.roi.rental_car.rental_car.entities.Car;
import com.roi.rental_car.rental_car.entities.Reservation;
import com.roi.rental_car.rental_car.mappers.CarMapper;
import com.roi.rental_car.rental_car.repositories.CarRepo;
import com.roi.rental_car.rental_car.repositories.ReservationRepo;
import com.roi.rental_car.rental_car.services.CarService;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {


    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private ReservationRepo reservationRepo;

    @Override

    public CarDTO getById(Long id) {
        Car car = carRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Car with this id" + id + "does not exists"));
        return carMapper.toDto(car);
    }

    @Override
    public List<CarDTO> getAll() {
        return carMapper.toDtoList(carRepo.findAll());
    }

    @Override
    public CarDTO createCar(CarDTO carDTO) {
        if (carRepo.existsCarByCarId(carDTO.getCarId())) {
            throw new RuntimeException("Car with this id" + carDTO.getCarId() + "already exists");
        }
        Car car = carMapper.toEntity(carDTO);
        carRepo.save(car);
        return carMapper.toDto(car);

    }

    @Override
    public CarDTO updateCar(CarDTO carDTO) {
        if (carDTO.getCarId() == null) throw new RuntimeException("Id must not be null");
        Car car = carRepo.findById(carDTO.getCarId()).orElseThrow(
                () -> new RuntimeException("Car with this is" + carDTO.getCarId() + " does not exist"));
        car.setCarId(carDTO.getCarId());
        car.setBrand(carDTO.getBrand());
        car.setColor(carDTO.getColor());
        car.setMilage(carDTO.getMilage());
        car.setAmount(carDTO.getAmount());
        carRepo.save(car);
        return carMapper.toDto(car);
    }

    @Override
    public String deleteCar(Long id) {
        try {
            if (carRepo.findById(id).isEmpty())
                throw new RuntimeException("Car with this id does not exists");
            carRepo.deleteById(id);
            return "The car has been deleted";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public void changeCarStatus() {
        List<Car> carList = carRepo.findAll();
        for (Car car : carList) {
            if (car.getReservation() != null) {
                Reservation reservation = car.getReservation();
                {
                    if (reservation.getReservationEnd().isBefore(LocalDate.now()) && car.getCarStatus().equals(CarStatus.BOOKED))
                        ;
                    {
                        car.setCarStatus(CarStatus.AVAILABLE);
                        car.setBranch(reservation.getReturnbrench());
                        carRepo.save(car);
                    }
                }
            }
        }
    }

    private List<CarDTO> getAvailableCar(LocalDate dateStart, LocalDate dateEnd) {
        List<Car> availableCars = carRepo.getAllByStatusNot(CarStatus.UNAVAILABLE);
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : availableCars) {
            if (car.getReservation() == null || car.getReservation().getReservationEnd().isBefore(dateStart) || car.getReservation().getReservationStart().isAfter(dateEnd)) {
                carDTOList.add(carMapper.toDto(car));
            }
        }
        return carDTOList;
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

    private void cancleReservation(LocalDate today, Long id){
        Optional <Reservation> reservationOptional= reservationRepo.findById(id);
        Reservation reservation=reservationOptional.get();
            if(reservation!=null){
              long returnDays= ChronoUnit.DAYS.between(today,reservation.getReservationStart());
              if (returnDays >=2){
                  double returnMoney= 0.8 * reservation.getAmount();
                  reservationRepo.deleteById(id);
              }
            }
        }




}
