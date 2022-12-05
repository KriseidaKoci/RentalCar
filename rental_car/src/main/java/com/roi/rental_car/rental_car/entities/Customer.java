package com.roi.rental_car.rental_car.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    @Column(nullable = false)
    private String email;
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations;
    @ManyToOne
    @JoinColumn(name = "rental")
    private Rental rental;
}
