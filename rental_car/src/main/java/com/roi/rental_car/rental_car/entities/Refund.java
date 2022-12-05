package com.roi.rental_car.rental_car.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;
    private LocalDate returnDate;
    private String refundCommens;
    private Double surcharge;
    private Double refund;
    @OneToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;

}
