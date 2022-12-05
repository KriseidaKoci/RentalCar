package com.roi.rental_car.rental_car.entities;
import com.roi.rental_car.rental_car.static_data.CarStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String brand;
    private String bodyType;
    private Integer year;
    private String color;
    private Double milage;
    private Double amount;
    @Enumerated(value = EnumType.STRING)
    private CarStatus carStatus;
    @OneToOne
    private UnavailableStatus status;
    @ManyToOne
  @JoinColumn(name = "branch")
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;
}
