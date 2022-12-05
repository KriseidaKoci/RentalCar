package com.roi.rental_car.rental_car.entities;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;
    private String name;
    private String internetDomain;
    private String contactAddress;
    @OneToMany (mappedBy="rental")
    private List<Branch> branches;
    @OneToMany(mappedBy = "rental")
    private List <Customer> customers;

}
