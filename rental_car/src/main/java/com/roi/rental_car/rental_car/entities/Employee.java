package com.roi.rental_car.rental_car.entities;

import com.roi.rental_car.rental_car.static_data.Position;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String name;
    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private Position posittion;
    @ManyToOne
    @JoinColumn(name="branch")
    private Branch branch;

}
