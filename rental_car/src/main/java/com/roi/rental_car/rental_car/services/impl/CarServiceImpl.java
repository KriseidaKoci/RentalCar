package com.roi.rental_car.rental_car.services.impl;

import com.roi.rental_car.rental_car.dto.CarDTO;
import com.roi.rental_car.rental_car.entities.Branch;
import com.roi.rental_car.rental_car.entities.Car;
import com.roi.rental_car.rental_car.entities.Reservation;
import com.roi.rental_car.rental_car.mappers.BranchMapper;
import com.roi.rental_car.rental_car.mappers.CarMapper;
import com.roi.rental_car.rental_car.mappers.ReservationMapper;
import com.roi.rental_car.rental_car.mappers.UnavailableStatusMapper;
import com.roi.rental_car.rental_car.repositories.BranchRepo;
import com.roi.rental_car.rental_car.repositories.CarRepo;
import com.roi.rental_car.rental_car.repositories.ReservationRepo;
import com.roi.rental_car.rental_car.repositories.UnavailableRepo;
import com.roi.rental_car.rental_car.services.CarService;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private BranchRepo branchRepo;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private UnavailableStatusMapper unavailableStatusMapper;
    @Autowired
    private UnavailableRepo unavailableRepo;

    @Override

    public CarDTO getById(Long id) {
        Car car = carRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Car with this id" + id + "does not exists"));
        CarDTO carDTO = carMapper.toDto(car);
        carDTO = setOtherValues(car, carDTO);
        return carMapper.toDto(car);
    }

    @Override
    public List<CarDTO> getAll() {
        List<CarDTO> carDTOS = new ArrayList<>();
        for (Car car : carRepo.findAll()) {
            CarDTO carDTO = carMapper.toDto(car);
            carDTO = setOtherValues(car, carDTO);
            carDTOS.add(carDTO);
        }
        return carDTOS;
    }

    @Override
    public CarDTO createCar(CarDTO carDTO) {
        if (carDTO.getCarId() != null) throw new RuntimeException("Id must be null");
        Car car = carMapper.toEntity(carDTO);
        if (carDTO.getBranch() != null) {
            Branch branch = branchRepo.findById(carDTO.getBranch().getBranchId()).orElseThrow(
                    () -> new RuntimeException("Id has not been found"));
            car.setBranch(branch);
        }
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
        CarDTO carDTO1 = carMapper.toDto(car);
        carDTO1 = setOtherValues(car, carDTO1);
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

            List<Car> cars = carRepo.findAll();
            for (Car car : cars) {
                if (car.getReservation() != null) {
                    Reservation reservation = car.getReservation();
                    if (reservation.getReservationEnd().isBefore(LocalDate.now()) &&
                            car.getCarStatus().equals(CarStatus.BOOKED)){
                        car.setCarStatus(CarStatus.AVAILABLE);
                        car.setBranch(reservation.getReturnbrench() );
                        carRepo.save(car);
                    }
                }
            }
        }
    private CarDTO setOtherValues(Car car, CarDTO carDTO) {
        if (car.getStatus() != null)
            carDTO.setStatus(unavailableStatusMapper.toDto(car.getStatus()));
        if (car.getBranch() != null)
            carDTO.setBranch(branchMapper.toDto(car.getBranch()));
        if (car.getReservation() != null)
            carDTO.setReservation(reservationMapper.toDto(car.getReservation()));
        return carDTO;
    }

    public List<CarDTO> getAllAvailableCars(LocalDate start, LocalDate end) {
        List<Car> carList = carRepo.getAllByStatusNot(CarStatus.UNAVAILABLE);
        List<CarDTO> availableCars = new ArrayList<>();
        for (Car car : carList) {
            if (car.getReservation() == null
                    || car.getReservation().getReservationStart().isAfter(end)
                    || car.getReservation().getReservationEnd().isBefore(start)) {
                availableCars.add(carMapper.toDto(car));
            }

        }

        return availableCars;
    }


}
