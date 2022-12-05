package com.roi.rental_car.rental_car.dto;

import com.roi.rental_car.rental_car.entities.Branch;
import com.roi.rental_car.rental_car.entities.Reservation;
import com.roi.rental_car.rental_car.entities.UnavailableStatus;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import lombok.Data;

import javax.persistence.*;

@Data
public class CarDTO {
    private Long carId;
    private String brand;
    private String bodyType;
    private Integer year;
    private String color;
    private Double milage;
    private Double amount;
    private CarStatus carStatus;
    private UnavailableStatusDTO status;
    private BranchDTO branch;
    private ReservationDTO reservation;
}
