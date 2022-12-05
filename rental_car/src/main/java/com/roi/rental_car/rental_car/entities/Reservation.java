package com.roi.rental_car.rental_car.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDate reservationBooking;
    private LocalDate reservationStart;
    private LocalDate reservationEnd;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "booking_branch")
    private Branch bookingbranch;

    @ManyToOne
    @JoinColumn(name = "return_branch")
    private Branch returnbrench;

    @OneToMany(mappedBy = "reservation")
    private List<Car> cars;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;
    @OneToOne(mappedBy = "reservation")
    private Refund refund;
}
